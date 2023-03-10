package com.example.pidev1.Controller;

import com.example.pidev1.Entity.Department;
import com.example.pidev1.Service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class DepartmentController {

    DepartmentService departmentService;


    //http://localhost:8089/pidev/add-department
    @PostMapping("/add-department")
    public Department addDepartment(@RequestBody Department d) {
        Department department = departmentService.addDepartment(d);
        return department;
    }

    //http://localhost:8089/pidev/retrieve-all-department
    @GetMapping("/retrieve-all-department")
    public List<Department> getdepartment() {
        List<Department> listDepartment = departmentService.retrieveAllDepartments();
        return listDepartment;
    }

    //http://localhost:8089/pidev/retrieve-department/{{department-id}}
    @GetMapping("/retrieve-department/{department-id}")
    public Department retrieveDepartment(@PathVariable("department-id") Long departmentId) {
        return departmentService.retrieveDepartment(departmentId);
    }

    //http://localhost:8089/pidev/remove-department/{{department-id}}
    @DeleteMapping("/remove-department/{department-id}")
    public void removeOperateur(@PathVariable("department-id") Long departmentId) {
        departmentService.removeDepartment(departmentId);
    }

    //http://localhost:8089/pidev/department/{{idDepartment}}
    @PutMapping("/department/{idDepartment}")
    public Department updateDepartment(@PathVariable Long idDepartment, @RequestBody Department updatedDepartment) {
        return departmentService.updateDepartment(idDepartment, updatedDepartment);
    }
}
