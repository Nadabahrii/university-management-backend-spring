package com.example.pidev1.Service;


import com.example.pidev1.Entity.Comment;
import com.example.pidev1.Entity.ProfanityFilter;
import com.example.pidev1.Entity.Publication;

import com.example.pidev1.Entity.Student;
import com.example.pidev1.Repository.Publication_Repository;
import com.example.pidev1.Repository.StudentRepository;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import com.twilio.Twilio;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class Publication_Service implements IPublication_Service {
    @Autowired
    private Publication_Repository publication_repository;
    @Autowired
    private StudentRepository student_repository;
    @Autowired
    private JavaMailSender javaMailSender;


    private String ACCOUNT_SID;
    private String AUTH_TOKEN;
    private String TWILIO_PHONE_NUMBER;

    @Autowired
    public Publication_Service(@Value("${twilio.accountSid}") String accountSid,
                               @Value("${twilio.authToken}") String authToken,
                               @Value("${twilio.phoneNumber}") String twilioPhoneNumber) {
        this.ACCOUNT_SID = accountSid;
        this.AUTH_TOKEN = authToken;
        this.TWILIO_PHONE_NUMBER = twilioPhoneNumber;
    }
    @Override
    public void sendSMSNotification(String toPhoneNumber, String message) {
        // Set up the Twilio client
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        // Send the SMS message
        Message.creator(new PhoneNumber(toPhoneNumber), new PhoneNumber(TWILIO_PHONE_NUMBER), message).create();
    }



    @Override
    public Publication getPublicationById(Long id) {
        return publication_repository.findById(id).orElse(null);
    }

    @Override
    public Publication addPublication(Publication publication) throws IOException {
        String content = publication.getContext();
        ProfanityFilter profanityFilter = new ProfanityFilter("C:/Users/Mohamed/Desktop/bad_words.txt");
        if (profanityFilter.containsProfanity(content)) {
            throw new IllegalArgumentException("The publication contains profanity");
        } else {
            return publication_repository.save(publication);
        }
    }

    @Override
    public List<Publication> retrieveAllPosts() {
        return publication_repository.findAll();
    }





    @Override
    public Publication updatePublication( long idPub, Publication updatedPublication) {
        Optional<Publication> optionalPublication = publication_repository.findById(idPub);
        if (optionalPublication.isPresent()) {
            Publication publication = optionalPublication.get();
            publication.setAuthor(updatedPublication.getAuthor());
            publication.setContext(updatedPublication.getContext());
            publication.setTopic(updatedPublication.getTopic());
            publication.setDate_of_publication(updatedPublication.getDate_of_publication());
            publication.setNbr_likes_Pub(updatedPublication.getNbr_likes_Pub());
            publication.setNbr_dislikes_Pub(updatedPublication.getNbr_dislikes_Pub());
            return publication_repository.save(publication);
        } else {
            return null;
        }
    }
    @Override
    public Publication retrievePublication(Long idPub) {
        Optional<Publication> publication = publication_repository.findById(idPub);
        if (publication.isPresent()) {
            return publication.get();
        } else {
            // handle the case when the publication is not found
            return null;
        }
    }



    @Override
    public void removePost(Long idPub) {
        publication_repository.deleteById(idPub);
    }

    @Override
    public List<Publication> searchSimilarPublications(Publication pub, int limit) {
        List<Publication> allPublications = publication_repository.findAll();
        List<Publication> similarPublications = new ArrayList<>();

        for (Publication publication : allPublications) {
            int distance = levenshteinDistance(pub.getContext(), publication.getContext());
            if (distance <= 6 && !publication.equals(pub)) {
                similarPublications.add(publication);
            }
            if (similarPublications.size() == limit) {
                break;
            }
        }

        return similarPublications;
    }
    public static int levenshteinDistance(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }

        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                int cost = str1.charAt(i - 1) == str2.charAt(j - 1) ? 0 : 1;
                dp[i][j] = Math.min(Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1), dp[i - 1][j - 1] + cost);
            }
        }

        return dp[m][n];
    }

    @Transactional
    public Publication likePublication(Long idPub, Long studentId) {
        Publication publication = publication_repository.findById(idPub).get();
        if (publication != null) {
            // Save the list of students who liked the publication before the update
            Set<Student> oldLikedByStudents = new HashSet<>(publication.getLikedByStudents());
            publication.setNbr_likes_Pub(publication.getNbr_likes_Pub() + 1);
            publication.setRating(calculateRating(publication));
            // Add the student's information to the publication
            Student student = student_repository.findById(studentId).get();
            if (student != null) {
                publication.getLikedByStudents().add(student);
                publication_repository.save(publication);
                // Send SMS notifications to old likers
                for (Student oldLiker : oldLikedByStudents) {
                    if (!oldLiker.equals(student)) {
                        sendSMSNotification(oldLiker.getContact(), "New like on publication " + publication.getIdPub() + " by " + student.getFirstname());
                    }
                }

            }
        }
        return publication;
    }



    @Transactional
    public Publication dislikePublication(Long idPub, Long studentId) {
        Publication publication = publication_repository.findById(idPub).get();
        if (publication != null) {
            publication.setNbr_dislikes_Pub(publication.getNbr_dislikes_Pub() + 1);
            publication.setRating(calculateRating(publication));
            Student student = student_repository.findById(studentId).orElse(null);
            if (student != null) {
                publication.getDislikedByStudents().add(student);
            }
            publication_repository.save(publication);
        }
        return publication;
    }






    @Override
    public double calculateRating(Publication publication) {
        long likes = publication.getNbr_likes_Pub();
        long dislikes = publication.getNbr_dislikes_Pub();

        double rating = likes / (double) (likes + dislikes);
        publication.setRating(rating);

        return rating;
    }
    @Override
    public List<Publication> getTopRatedPublications() {
        List<Publication> publications = publication_repository.findAll();
        // trier les publications par ordre décroissant de rating
        publications.sort(Comparator.comparingDouble(Publication::getRating).reversed());
        // retourner les 10 premières publications de la liste triée
        return publications.stream().limit(10).collect(Collectors.toList());
    }
    @Override
    public List<Publication> getPublicationsInteractedByStudent(Long studentId) {
        Student student = student_repository.findById(studentId).get();
        List<Publication> interactedPublications = new ArrayList<>();
        List<Publication> publications = publication_repository.findAll();
        if (student == null) {
            return null;
        }
        else {

            for (Publication publication : publications) {
                Set<Comment> comments = publication.getComments();
                for (Comment comment : comments) {
                    if (comment.getStudentss().getIdStudent().equals(studentId)) {
                        interactedPublications.add(publication);
                        break;
                    }
                }
                if (publication.getLikedByStudents().contains(student)) {
                    interactedPublications.add(publication);
                    break;
                }
                if (publication.getDislikedByStudents().contains(student)) {
                    interactedPublications.add(publication);
                    break;
                }
            }

            return interactedPublications;
        }


    }
    @Override
    public Student getMostActiveStudent() {
        List<Student> etudiants = student_repository.findAll();
        Student mostActiveStudent = null;
        int maxInteractions = 0;

        for (Student student : etudiants) {
            List<Publication> interactedPublications = getPublicationsInteractedByStudent(student.getIdStudent());
            int numInteractions = interactedPublications.size();

            if (numInteractions > maxInteractions) {
                mostActiveStudent = student;
                maxInteractions = numInteractions;
            }

        }

        return mostActiveStudent;

    }
    @Override
    public void sendSuperFanBadge(Student student) throws MessagingException {

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(student.getEmail());
        helper.setSubject("Félicitations pour votre badge de Super Fan !");
        helper.setText("Bonjour " + student.getFirstname() + ",\n\n"
                + "Félicitations, vous avez été désigné comme notre Super Fan de la semaine en raison de votre activité sur notre plateforme. Nous tenons à vous remercier pour votre engagement et votre soutien.\n\n"
                + "Veuillez trouver ci-joint votre badge de Super Fan.\n\n"
                + "L'équipe DEEPFLOW \n\n"
                + "Cordialement,\n");

        ClassPathResource file = new ClassPathResource("static/images/super_fan_badge.png");
        helper.addAttachment("super_fan_badge.png", file);

        javaMailSender.send(message);
    }






    @Override
    public List<Publication> getUninteractedPublications(Long studentId) {
        Student student = student_repository.findById(studentId).get();
        if (student == null) {
            return null;
        }

        // Retrieve publications interacted with by the student
        List<Publication> interactedPublications = getPublicationsInteractedByStudent(studentId);

        // Find publications that have been interacted with by other students but not the input student
        List<Publication> uninteractedPublications = new ArrayList<>();
        List<Student> allStudents = student_repository.findAll();
        for (Student otherStudent : allStudents) {
            if (otherStudent.getIdStudent() != studentId) {
                List<Publication> otherInteractedPublications = getPublicationsInteractedByStudent(otherStudent.getIdStudent());
                for (Publication publication : otherInteractedPublications) {
                    if (!interactedPublications.contains(publication) && !uninteractedPublications.contains(publication)) {
                        uninteractedPublications.add(publication);
                    }
                }
            }
        }

        return uninteractedPublications;
    }
























}

