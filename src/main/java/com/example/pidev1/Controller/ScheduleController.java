package com.example.pidev1.Controller;

import com.example.pidev1.Entity.*;
import com.example.pidev1.Service.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;

    @Autowired
    EmployerService employerService;

    @Autowired
    ClassroomService classroomService;

    @Autowired
    SubjectService subjectService;

    @GetMapping("/planifier")
    public Schedule planifierEmploiDuTemps() {
        List<Employers> enseignants = employerService.retrieveEmployersByDispo();
        List<classroom> salles = classroomService.retrieveclassroomsByDispo();
        List<Subject> matieres = subjectService.retrieveAllSubjects();

        //List<Lesson> L =scheduleService.planifier(enseignants, salles, matieres);
        Schedule emploiDuTemps = scheduleService.planifier(enseignants, salles, matieres);
        return emploiDuTemps;
    }

}
