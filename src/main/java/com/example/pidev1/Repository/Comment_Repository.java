package com.example.pidev1.Repository;

import com.example.pidev1.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Comment_Repository extends JpaRepository<Comment, Long> {
}
