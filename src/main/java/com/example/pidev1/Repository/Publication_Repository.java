package com.example.pidev1.Repository;

import com.example.pidev1.Entity.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Publication_Repository extends JpaRepository<Publication,Long> {

}
