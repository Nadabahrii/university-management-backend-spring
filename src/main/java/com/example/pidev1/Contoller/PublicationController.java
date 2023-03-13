package com.example.pidev1.Contoller;

import com.example.pidev1.Entity.Comment;
import com.example.pidev1.Entity.Publication;
import com.example.pidev1.Entity.Student;
import com.example.pidev1.Repository.Publication_Repository;
import com.example.pidev1.Repository.StudentRepository;
import com.example.pidev1.Service.Publication_Service;
import com.example.pidev1.Service.Publication_Service;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/Publication")
public class PublicationController {
    @Autowired
    Publication_Service publication_service;
    @Autowired
    private Publication_Repository publication_repository;
    @Autowired
    private final StudentRepository student_Repository;


    //http://localhost:8089/pidev/Publication/add-pub
    @PostMapping("/add-pub")
    public ResponseEntity<?> addPublication(@RequestBody Publication publication) throws IOException {
        try {
            Publication addedPublication = publication_service.addPublication(publication);
            return ResponseEntity.ok().body(addedPublication);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    //http://localhost:8089/pidev/Publication/retrieve-all-posts
    @GetMapping("/retrieve-all-posts")
    public List<Publication> getPublications() {
        List<Publication> publicationList =publication_service.retrieveAllPosts();
        return publicationList;
    }
    //http://localhost:8089/pidev/Publication/retrieve-post/{{post-id}}
    @GetMapping("/retrieve-post/{post-id}")
    public Publication retrievePublication(@PathVariable("post-id") Long PostId) {
        return publication_service.retrievePublication(PostId);
    }
    //http://localhost:8089/pidev/Publication/publications/{{idPub}}
    @PutMapping("/publications/{idPub}")
    public Publication updatePublication(@PathVariable long idPub, @RequestBody Publication updatedPublication) {
        return publication_service.updatePublication(idPub, updatedPublication);
    }
    //http://localhost:8089/pidev/Publication/remove-post/{{post-id}}
    @DeleteMapping("/remove-post/{post-id}")
    public void removePost(@PathVariable("post-id") Long PostId) {
        publication_service.removePost(PostId);
    }
    /*//http://localhost:8089/pidev/Publication/{{id}}/similar
    @GetMapping("/{id}/similar")
    public ResponseEntity<List<Publication>> getSimilarPostsById(@PathVariable Long id) {
        Publication post = publication_service.retrievePublication(id);
        if (post == null) {
            return ResponseEntity.notFound().build();
        }
        List<Publication> similarPosts = publication_service.searchSimilarPosts(post);
        return ResponseEntity.ok(similarPosts);
    }*/
    // http://localhost:8089/pidev/Publication/{{id}}/similar
    @GetMapping("/{id}/similar")
    public ResponseEntity<List<Publication>> getSimilarPublicationsById(@PathVariable Long id) {
        Publication publication = publication_service.retrievePublication(id);
        if (publication == null) {
            return ResponseEntity.notFound().build();
        }
        List<Publication> similarPublications = publication_service.searchSimilarPublications(publication, 4);
        return ResponseEntity.ok(similarPublications);
    }
    //http://localhost:8089/pidev/Publication/{{id}}/{{ids}}/like
    @PostMapping("/{id}/{ids}/like")
    public ResponseEntity<Publication> likePublication(@PathVariable Long id,@PathVariable Long ids) {
        Publication publication = publication_service.likePublication(id,ids);
        if (publication == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(publication);
    }
    //http://localhost:8089/pidev/Publication/{{id}}/{{ids}}/dislike
    @PostMapping("/{id}/{ids}/dislike")
    public ResponseEntity<Publication> dislikePublication(@PathVariable Long id,@PathVariable Long ids) {
        Publication publication = publication_service.dislikePublication(id,ids);
        if (publication == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(publication);
    }


    //http://localhost:8089/pidev/Publication/publications/top-rated
    @GetMapping("/publications/top-rated")
    public List<Publication> getTopRatedPublications() {
        List<Publication> topRatedPublications = publication_service.getTopRatedPublications();
        return topRatedPublications;
    }
    //http://localhost:8089/pidev/Publication/{{studentId}}/interacted
    @GetMapping("/{studentId}/interacted")
    public List<Publication> getInteractedPublicationsByStudent(@PathVariable("studentId") Long studentId) {
        return publication_service.getPublicationsInteractedByStudent(studentId);
    }
    //http://localhost:8089/pidev/Publication/MostActiveStudent
    @GetMapping("/MostActiveStudent")
    public Student getMostActiveStudent(){
        Student MostActiveStudent =publication_service.getMostActiveStudent();
        return MostActiveStudent;
    }
    //http://localhost:8089/pidev/Publication/send-super-fan-badge
    @PostMapping("/send-super-fan-badge")
    public ResponseEntity<String> sendSuperFanBadgeToMostActiveStudent() throws MessagingException {
        Student mostActiveStudent = publication_service.getMostActiveStudent();
        if (mostActiveStudent != null) {
            publication_service.sendSuperFanBadge(mostActiveStudent);
            return ResponseEntity.ok("Super fan badge sent to " + mostActiveStudent.getFirstname() + " (" + mostActiveStudent.getEmail() + ")");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //http://localhost:8089/pidev/Publication/{{id}}/uninteracted-publications
    @GetMapping("/{id}/uninteracted-publications")
    public List<Publication> getUninteractedPublications(@PathVariable Long id) {
        return publication_service.getUninteractedPublications(id);
    }












}
