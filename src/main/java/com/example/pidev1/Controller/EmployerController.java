package com.example.pidev1.Controller;

import com.example.pidev1.Entity.Employers;
import com.example.pidev1.Service.EmployerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class EmployerController {
    EmployerService employerService;

    //http://localhost:8089/pidev/add-employer
    @PostMapping("/add-employer")
    public Employers addEmployers(@RequestBody Employers e) {
        Employers employer = employerService.addEmployers(e);
        return employer;
    }

    //http://localhost:8089/pidev/retrieve-all-Employers
    @GetMapping("/retrieve-all-Employers")
    public List<Employers> getEmployers() {
        List<Employers> listEmployers = employerService.retrieveAllEmployers();
        return listEmployers;
    }

    //http://localhost:8089/pidev/retrieve-employers/{{employers-id}}
    @GetMapping("/retrieve-employers/{employers-id}")
    public Employers retrieveEmployers(@PathVariable("employers-id") Long employersId) {
        return employerService.retrieveEmployers(employersId);
    }

    //http://localhost:8089/pidev/remove-employer/{{employer-id}}
    @DeleteMapping("/remove-employer/{employer-id}")
    public void removeOperateur(@PathVariable("employer-id") Long employerId) {
        employerService.removeEmployers(employerId);
    }

    //http://localhost:8089/pidev/employers/{{idEmployer}}
    @PutMapping("/employers/{idEmployer}")
    public Employers updateEmployer(@PathVariable Long idEmployer, @RequestBody Employers updatedEmployer) {
        return employerService.updateEmployer(idEmployer, updatedEmployer);
    }
}
