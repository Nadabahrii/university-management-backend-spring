package com.example.pidev1.service;

import com.example.pidev1.entity.Publication;
import com.example.pidev1.entity.Student;
import com.example.pidev1.repository.Comment_Repository;
import com.example.pidev1.entity.Comment;
import com.example.pidev1.repository.Publication_Repository;
import com.example.pidev1.repository.Student_Repository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class Comment_Service implements IComment_Service{
    @Autowired
    private Comment_Repository comment_repository;
    @Autowired
    private Publication_Repository publication_repository;
    @Autowired
    private Student_Repository student_repository;
    @Override
    public List<Comment> retrieveAllcomments() {
        return comment_repository.findAll();
    }
    @Override
    public Comment addComment(Comment comment) {return comment_repository.save(comment);}
    @Override
    public Comment updateComment(Long idComm, Comment updatedComment) {
        Optional<Comment> optionalComment = comment_repository.findById(idComm);
        if (optionalComment.isPresent()) {
            Comment comment = optionalComment.get();
            comment.setComment_text(updatedComment.getComment_text());
            comment.setAuthor(updatedComment.getAuthor());
            comment.setNbr_likes_com(updatedComment.getNbr_likes_com());
            comment.setNbr_dislikes_com(updatedComment.getNbr_dislikes_com());
            comment.setDate_of_comm_post(updatedComment.getDate_of_comm_post());
            return comment_repository.save(comment);
        } else {
            return null;
        }
    }
    @Override
    public Comment retrieveComment(Long idComm) {return comment_repository.findById(idComm).get();}
    @Override
    public void removeComment(Long idComm) {
        comment_repository.deleteById(idComm);
    }

    @Transactional
    public Comment addCommentToPublication(Long publicationId, Comment comment, Long studentId) {
        Publication publication = publication_repository.findById(publicationId).get();
        Student student = student_repository.findById(studentId).get();
        if (publication != null && student != null) {

            comment.setDate_of_comm_post(new Date());
            comment.setAuthor(student.getFirstname());
            comment.setStudentss(student);
            comment.setPublication(publication);
            comment_repository.save(comment);
            publication.getComments().add(comment);
            publication_repository.save(publication);
            return comment;
        }
        return null;
    }


}
