package com.example.pidev1.Repository;

import com.example.pidev1.Entity.Employers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployersRepository extends JpaRepository<Employers,Long> {

    List<Employers> findAll();
}
