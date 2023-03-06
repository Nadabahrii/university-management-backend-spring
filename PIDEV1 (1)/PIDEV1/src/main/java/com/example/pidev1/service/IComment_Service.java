package com.example.pidev1.service;

import com.example.pidev1.entity.Comment;

import java.util.List;

public interface IComment_Service {
    List<Comment> retrieveAllcomments();

    Comment addComment(Comment comment);


    Comment updateComment(Long idComm, Comment updatedComment);

    Comment retrieveComment(Long idComm);

    void removeComment(Long idComm);
    Comment addCommentToPublication(Long publicationId, Comment comment, Long studentId);
}
