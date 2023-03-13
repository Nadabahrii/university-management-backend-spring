package com.example.pidev1.Repository;

import com.example.pidev1.Entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRequestRepository extends JpaRepository<Request, Long> {
}
