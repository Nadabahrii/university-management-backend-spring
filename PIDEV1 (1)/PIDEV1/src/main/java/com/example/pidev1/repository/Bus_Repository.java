package com.example.pidev1.repository;

import com.example.pidev1.entity.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Bus_Repository extends JpaRepository<Bus, Long> {
}
