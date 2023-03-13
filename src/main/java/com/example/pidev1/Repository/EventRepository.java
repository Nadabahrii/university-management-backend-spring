package com.example.pidev1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.pidev1.Entity.Event;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {


    @Query("SELECT p FROM Event p WHERE " +
            "p.nameEvent LIKE CONCAT('%',:query, '%')" )
    List<Event> searchEvent(String query);
}
