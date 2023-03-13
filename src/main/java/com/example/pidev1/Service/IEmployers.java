package com.example.pidev1.Service;

import com.example.pidev1.Entity.Employers;

import java.util.List;

public interface IEmployers {
    List<Employers> retrieveAllEmployers();

    Employers addEmployers (Employers e);

    Employers updateEmployer(Long idEmployer, Employers updatedEmployer);

    Employers retrieveEmployers(Long idEmployer);

    void removeEmployers(Long idEmployer);
}
