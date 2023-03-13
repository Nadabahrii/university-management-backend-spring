package com.example.pidev1.Repository;

import com.example.pidev1.Entity.Host;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HostRepository extends JpaRepository<Host,Long> {

    @Query("SELECT p FROM Host p WHERE " +
            "p.name LIKE CONCAT('%',:query, '%')" )
    List<Host> searchHost(String query);

}
