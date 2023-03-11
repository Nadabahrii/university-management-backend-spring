package com.example.pidev1.Service;

import com.example.pidev1.Entity.Lesson;
import com.example.pidev1.Repository.LessonRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class LessonService {

    @Autowired
    LessonRepo lessonRepo;

    public List<Lesson> saveAllCours(List<Lesson> coursPlanifies) {
        List<Lesson> coursEnregistres = new ArrayList<>();
        for (Lesson cours : coursPlanifies) {
            coursEnregistres.add(lessonRepo.save(cours));
        }
        return coursEnregistres;
    }

    public List<Lesson> getall(){
        return lessonRepo.findAll();
    }
}
