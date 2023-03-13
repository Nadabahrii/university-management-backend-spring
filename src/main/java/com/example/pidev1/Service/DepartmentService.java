package com.example.pidev1.Service;

import com.example.pidev1.Entity.Department;
import com.example.pidev1.Entity.Employers;
import com.example.pidev1.Repository.DepartmentRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DepartmentService implements IDepartment{

    @Autowired
    DepartmentRepo departmentRepo;


    @Override
    public List<Department> retrieveAllDepartments() {
        return departmentRepo.findAll();
    }

    @Override
    public Department addDepartment(Department d) {
        return departmentRepo.save(d);
    }

    @Override
    public Department updateDepartment(Long idDepartment, Department updatedDepartment) {
        Optional<Department> optionalDepartment = departmentRepo.findById(idDepartment);
        if (optionalDepartment.isPresent()) {
            Department department = optionalDepartment.get();
            department.setName(updatedDepartment.getName());
            return departmentRepo.save(department);
        } else {
            return null;
        }
    }

    @Override
    public Department retrieveDepartment(Long idDepartment) {
        return departmentRepo.findById(idDepartment).get();
    }

    @Override
    public void removeDepartment(Long idDepartment) {
        departmentRepo.deleteById(idDepartment);

    }
}
