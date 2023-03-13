package com.example.pidev1.Service;

import com.example.pidev1.Entity.Department;

import java.util.List;

public interface IDepartment {
    List<Department> retrieveAllDepartments();

    Department addDepartment (Department d);

    Department updateDepartment(Long idDepartment, Department updatedDepartment);

    Department retrieveDepartment(Long idDepartment);

    void removeDepartment(Long idDepartment);
}
