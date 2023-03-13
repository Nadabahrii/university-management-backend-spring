package com.example.pidev1.Repository;

import com.example.pidev1.Entity.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Bus_Repository extends JpaRepository<Bus, Long> {
}
