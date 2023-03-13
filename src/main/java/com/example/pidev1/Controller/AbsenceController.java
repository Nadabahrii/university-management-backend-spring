package com.example.pidev1.Controller;


import com.example.pidev1.Entity.Absence;
import com.example.pidev1.Entity.Student;
import com.example.pidev1.Repository.StudentRepository;
import com.example.pidev1.Service.AbsenceServices;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class AbsenceController {

    @Autowired
    AbsenceServices absenceServices;

    @Autowired
    StudentRepository studentRepository;

    @PostMapping("/addAbsence")
    public Absence createAbsence(@RequestBody Absence absence) {
        return absenceServices.createAbsence(absence);
    }

    @PutMapping("/{id}/valider")
    public Absence validerAbsence(@PathVariable Long id) {
        return absenceServices.validerAbsence(id);
    }

    @DeleteMapping("/{id}")
    public void supprimerAbsence(@PathVariable Long id) {
        absenceServices.supprimerAbsence(id);
    }

    @GetMapping("/etudiant/{etudiantId}")
    public List<Absence> getAbsencesByEtudiant(@PathVariable Long etudiantId) {
        return absenceServices.getAbsencesByEtudiant(etudiantId);
    }

    @GetMapping("/etudiant/absence/{etudiantId}")
    public Student verifseuilabsences(@PathVariable Long etudiantId){
        Student s = studentRepository.findById(etudiantId).get();
        return absenceServices.verifierSeuilAbsence(s);
    }

    @GetMapping("/cours/{coursId}")
    public List<Absence> getAbsencesByCours(@PathVariable Long coursId) {
        return absenceServices.getAbsencesByCours(coursId);
    }

    @GetMapping("/nonValidees")
    public List<Absence> getAbsencesNonValidees() {
        return absenceServices.getAbsencesNonValidees();
    }
}
