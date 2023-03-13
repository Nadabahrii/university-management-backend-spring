package com.example.pidev1.Repository;

import com.example.pidev1.Entity.Absence;
import com.example.pidev1.Entity.Lesson;
import com.example.pidev1.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AbsenceRepo extends JpaRepository<Absence,Long> {

    List<Absence> findByStudent(Student student);

    List<Absence> findByLesson(Lesson lesson);

    List<Absence> findByValidee(boolean validee);

    int countByStudent(Student student);




}
