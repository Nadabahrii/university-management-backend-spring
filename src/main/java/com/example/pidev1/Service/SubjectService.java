package com.example.pidev1.Service;

import com.example.pidev1.Entity.Subject;
import com.example.pidev1.Repository.SubjectRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SubjectService {
    @Autowired
    SubjectRepo subjectRepo;

    public List<Subject> retrieveAllSubjects(){
        return subjectRepo.findAll();
    }
}
