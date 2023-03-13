package com.example.pidev1.Repository;

import com.example.pidev1.Entity.Employers;
import com.example.pidev1.Entity.Rdv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository

public interface RdvRepository extends JpaRepository<Rdv, Long > {
    List<Rdv> findAllByClasse(Class classe);

    List<Rdv> findAllByEmployer(Employers employer);

    List<Rdv> findAllByStartTimeLike(String pattern);

}

