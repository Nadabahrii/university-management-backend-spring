package com.example.pidev1.Service;

import com.example.pidev1.Entity.classroom;
import com.example.pidev1.Repository.ClassroomRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClassroomService {
    @Autowired
    ClassroomRepo classroomRepo;

    public List<classroom> retrieveclassroomsByDispo(){

        return classroomRepo.retrieveclassroomsByDispo();
    }

    public List<classroom> retrieveAllClassroom(){
        return classroomRepo.findAll();
    }
}
