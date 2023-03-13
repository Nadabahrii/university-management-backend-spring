package com.example.pidev1.Service;

import com.example.pidev1.Entity.Employers;
import com.example.pidev1.Repository.EmployersRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployerService implements IEmployers {

    @Autowired
    EmployersRepo employersRepo;

    @Override
    public List<Employers> retrieveAllEmployers() {
        return employersRepo.findAll();
    }

    @Override
    public Employers addEmployers(Employers e) {
        return employersRepo.save(e);
    }

    @Override
    public Employers updateEmployer(Long idEmployer, Employers updatedEmployer) {
        Optional<Employers> optionalEmployer = employersRepo.findById(idEmployer);
        if (optionalEmployer.isPresent()) {
            Employers employer = optionalEmployer.get();
            employer.setFirstname(updatedEmployer.getFirstname());
            employer.setLastname(updatedEmployer.getLastname());
            employer.setEmail(updatedEmployer.getEmail());
            employer.setAddress(updatedEmployer.getAddress());
            employer.setContact(updatedEmployer.getContact());
            employer.setAge(updatedEmployer.getAge());
            employer.setRole(updatedEmployer.getRole());
            return employersRepo.save(employer);
        } else {
            return null;
        }
    }

    @Override
    public Employers retrieveEmployers(Long idEmployer) {

        return employersRepo.findById(idEmployer).get();
    }

    public List<Employers> retrieveEmployersByDispo(){
        return employersRepo.retrieveEmployersByDispo();
    }

    @Override
    public void removeEmployers(Long idEmployer) {
        employersRepo.deleteById(idEmployer);

    }
}
