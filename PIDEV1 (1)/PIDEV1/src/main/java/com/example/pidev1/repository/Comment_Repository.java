package com.example.pidev1.repository;

import com.example.pidev1.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Comment_Repository extends JpaRepository<Comment, Long> {
}
