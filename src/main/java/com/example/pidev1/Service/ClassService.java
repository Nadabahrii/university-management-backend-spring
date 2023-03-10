package com.example.pidev1.Service;

import com.example.pidev1.Entity.Class;
import com.example.pidev1.Entity.Department;
import com.example.pidev1.Repository.ClassRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClassService implements IClass{

    ClassRepo classRepo;
    @Override
    public List<Class> retrieveAllClass() {
        return classRepo.findAll();
    }

    @Override
    public Class addClass(Class c) {
        return classRepo.save(c);
    }

    @Override
    public Class updateClass(Long idClass, Class updatedClass) {
        Optional<Class> optionalClass = classRepo.findById(idClass);
        if (optionalClass.isPresent()) {
            Class c = optionalClass.get();
            c.setName(updatedClass.getName());
            return classRepo.save(c);
        } else {
            return null;}
    }

    @Override
    public Class retrieveClass(Long idClass) {
        return classRepo.findById(idClass).get();
    }

    @Override
    public void removeClass(Long idClass) {
        classRepo.deleteById(idClass);

    }
}
