package com.example.pidev1.repository;

import com.example.pidev1.entity.Employers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployersRepo extends JpaRepository<Employers,Long> {
}
