package com.example.pidev1.service;

import com.example.pidev1.entity.Publication;
import com.example.pidev1.entity.Student;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

public interface IPublication_Service {


    Publication getPublicationById(Long id);

    Publication addPublication(Publication publication) throws IOException;

    List<Publication> retrieveAllPosts();



    Publication updatePublication(long idPub, Publication updatedPublication);


    Publication retrievePublication(Long idPub);

    void removePost(Long idPub);


    List<Publication> searchSimilarPosts(Publication pub);

    List<Publication> searchSimilarPublications(Publication pub, int limit);

    Publication likePublication(Long idPub,Long idStudent);

    Publication dislikePublication(Long idPub,Long studentId);



    double calculateRating(Publication publication);

    List<Publication> getTopRatedPublications();

    List<Publication> getPublicationsInteractedByStudent(Long studentId);

    Student getMostActiveStudent();

    void sendSuperFanBadge(Student student) throws MessagingException;

    List<Publication> getUninteractedPublications(Long studentId);

    ///// fonction que regroupe les publications interacted pour chaque etudiant



}
