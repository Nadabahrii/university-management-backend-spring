package com.example.pidev1.Repository;

import com.example.pidev1.Entity.Employers;
import com.example.pidev1.Entity.classroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassroomRepo extends JpaRepository<classroom,Long> {

    @Query("SELECT c FROM classroom c WHERE c.isDispo=true ")
    List<classroom> retrieveclassroomsByDispo();
}
