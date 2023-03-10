package com.example.pidev1.Repository;

import com.example.pidev1.Entity.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRepo extends JpaRepository<Class,Long> {
}
