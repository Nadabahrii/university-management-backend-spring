package com.example.pidev1.Controller;

import com.example.pidev1.Entity.Professeur;
import com.example.pidev1.Service.IProfessorServices;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor()
@RequestMapping("/prof")

public class ProfessorController {
    private final IProfessorServices iProfessorServices;

    @GetMapping("/prof/getall")
    List<Professeur> retreiveAllProfs() {
        return iProfessorServices.retreiveAllProfs();}

    @PostMapping("/prof/addProf")
    Professeur addProf(@RequestBody Professeur professeur) {
        return iProfessorServices.addProf(professeur);
    }
    @PutMapping("/prof/update/{id}")
    Professeur update(@RequestBody Professeur professeur ,@PathVariable("id") Long id) {
        return iProfessorServices.updateProf(professeur, id);
    }

    @DeleteMapping("/prof/removeProf/{id}")
    void removeProf(@PathVariable("id") Long id) {
        iProfessorServices.removeProf(id);
    }
}
