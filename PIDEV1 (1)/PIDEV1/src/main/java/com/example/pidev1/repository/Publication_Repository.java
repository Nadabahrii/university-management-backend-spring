package com.example.pidev1.repository;

import com.example.pidev1.entity.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Publication_Repository extends JpaRepository<Publication,Long> {

}
