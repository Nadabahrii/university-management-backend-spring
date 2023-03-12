package com.example.pidev1.Service;

import com.example.pidev1.Entity.Publication;
import com.example.pidev1.Entity.Student;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

public interface IPublication_Service {
    void sendSMSNotification(String toPhoneNumber, String message);

    Publication getPublicationById(Long id);

    Publication addPublication(Publication publication) throws IOException;

    List<Publication> retrieveAllPosts();

    Publication updatePublication(long idPub, Publication updatedPublication);

    Publication retrievePublication(Long idPub);

    void removePost(Long idPub);

    List<Publication> searchSimilarPublications(Publication pub, int limit);

    double calculateRating(Publication publication);

    List<Publication> getTopRatedPublications();

    List<Publication> getPublicationsInteractedByStudent(Long studentId);

    Student getMostActiveStudent();

    void sendSuperFanBadge(Student student) throws MessagingException;

    List<Publication> getUninteractedPublications(Long studentId);
}
