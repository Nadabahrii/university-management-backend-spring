package com.example.pidev1.Contoller;

import com.example.pidev1.Entity.Comment;
import com.example.pidev1.Service.Comment_Service;
import com.example.pidev1.Service.IComment_Service;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/comment")
public class CommentController {


    Comment_Service Comment_service;
    //http://localhost:8089/pidev/comment/add-comment
    @PostMapping("/add-comment")
    public Comment addComment(@RequestBody Comment c) {
        Comment comment = Comment_service.addComment(c);
        return comment;
    }
    //http://localhost:8089/pidev/comment/retrieve-all-comments
    @GetMapping("/retrieve-all-comments")
    public List<Comment> getComments() {
        List<Comment> listComments =Comment_service.retrieveAllcomments();
        return listComments;
    }
    //http://localhost:8089/pidev/comment/retrieve-comment/{{comment-id}}
    @GetMapping("/retrieve-comment/{comment-id}")
    public Comment retrieveComment(@PathVariable("comment-id") Long CommentId) {
        return Comment_service.retrieveComment(CommentId);
    }
    //http://localhost:8089/pidev/comment/comments/{{idComm}}
    @PutMapping("/comments/{idComm}")
    public Comment updateComment(@PathVariable Long idComm, @RequestBody Comment updatedComment) {
        return Comment_service.updateComment(idComm, updatedComment);
    }
    //http://localhost:8089/pidev/comment/remove-comment/{{comment-id}}
    @DeleteMapping("/remove-comment/{comment-id}")
    public void removeComment(@PathVariable("comment-id") Long commentId) {
        Comment_service.removeComment(commentId);
    }


    //http://localhost:8089/pidev/comment/publications/{{publicationId}}/comments
    @PostMapping("/publications/{publicationId}/comments/{studentId}")
    public ResponseEntity<Comment> addCommentToPublication(@PathVariable Long publicationId,
                                                           @RequestBody Comment comment,
                                                           @PathVariable Long studentId) {
        Comment createdComment = Comment_service.addCommentToPublication(publicationId, comment, studentId);
        if (createdComment != null) {
            return ResponseEntity.ok(createdComment);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



}
