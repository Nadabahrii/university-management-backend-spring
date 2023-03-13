package com.example.pidev1.Service;

import com.example.pidev1.Entity.Student;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface IStudentServices {

    void retreiveAllStudents();
    public  List<Student> retrieveAllStudents();
    public Student addStudent (Student s  );
    public Student update (Student s  ,Long idStudent);
    public void  removeStudent(Long idStudent);
    public Student findStudentById(long id);

    public String Suggestion( Long idStudent) ;


}
