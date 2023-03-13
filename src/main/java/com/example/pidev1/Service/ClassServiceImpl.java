package com.example.pidev1.Service;

import com.example.pidev1.Entity.Class;
import com.example.pidev1.Repository.ClassRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassServiceImpl implements IClassService {

    @Autowired
    ClassRepo classRepository;
    @Override
    public List<Class> findAllClasses() {
        return classRepository.findAll();
    }
}
