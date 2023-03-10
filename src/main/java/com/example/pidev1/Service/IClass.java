package com.example.pidev1.Service;

import com.example.pidev1.Entity.Class;

import java.util.List;

public interface IClass {
    List<Class> retrieveAllClass();

    Class addClass (Class c);

    Class updateClass(Long idClass, Class updatedClass);

    Class retrieveClass(Long idClass);

    void removeClass(Long idClass);
}
