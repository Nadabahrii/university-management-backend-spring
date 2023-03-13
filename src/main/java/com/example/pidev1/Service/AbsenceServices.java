package com.example.pidev1.Service;

import com.example.pidev1.Entity.Absence;
import com.example.pidev1.Entity.Lesson;
import com.example.pidev1.Entity.Student;
import com.example.pidev1.Repository.AbsenceRepo;
import com.example.pidev1.Repository.LessonRepo;
import com.example.pidev1.Repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AbsenceServices {
    @Autowired
    AbsenceRepo absenceRepo;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    LessonService lessonService;

    public Absence createAbsence(Absence absence) {
        absence.setValidee(false);
        return absenceRepo.save(absence);
    }

    public Absence validerAbsence(Long id) {
        Absence absence = absenceRepo.findById(id).get();
        absence.setValidee(true);
        return absenceRepo.save(absence);
    }

    public void supprimerAbsence(Long id) {
        absenceRepo.deleteById(id);
    }

    public List<Absence> getAbsencesByEtudiant(Long etudiantId) {
        Student student = new Student();
        student.setIdStudent(etudiantId);
        return absenceRepo.findByStudent(student);
    }

    public List<Absence> getAbsencesByCours(Long coursId) {
        Lesson lesson = new Lesson();
        lesson.setIdLesson(coursId);
        return absenceRepo.findByLesson(lesson);
    }

    public List<Absence> getAbsencesNonValidees() {
        return absenceRepo.findByValidee(false);
    }

    public void sendEmail(String toEmail,
                          String subject,
                          String body
    ) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("mohamed.dhokkar@esprit.tn");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);
        mailSender.send(message);
        System.out.println("Mail Send...");


    }

    // Vérifie si un étudiant a dépassé le seuil d'absence maximum

    public Student verifierSeuilAbsence(Student student) {
        //double seuilAbsenceMax = 0.2; // Seuil d'absence maximum de 20%
        int nombreAbsences = absenceRepo.countByStudent(student);
        //int s = lessonService.getall().size();

            if (nombreAbsences >= 2 && nombreAbsences < 5) {
                // Appliquer une sanction de perte de 2 points
                student.setCredie(student.getCredie() - 2);
                studentRepository.save(student);

            } else if (nombreAbsences >= 5 && nombreAbsences < 10) {
                // Appliquer une sanction de perte de 4 points
                student.setCredie(student.getCredie() - 4);
                studentRepository.save(student);
            } else if (nombreAbsences >= 10) {
                // Appliquer une sanction de perte de 6 points
                student.setCredie(student.getCredie() - 10);
                studentRepository.save(student);
            }
            if(student.getCredie()==0)
            {
                String subject = "alert";
                String body = "alert";
                sendEmail(student.getEmail(), subject, body);
            }
       // absenceRepo.deleteAbsenceByStudent(student);

        List<Absence> L = absenceRepo.findByStudent(student);
            for(Absence a : L){
                absenceRepo.deleteById(a.getIdAbsence());
            }



            return student;
        }






}
