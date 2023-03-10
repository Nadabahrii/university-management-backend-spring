package com.example.pidev1.Service;

import com.example.pidev1.Entity.*;
import com.example.pidev1.Repository.LessonRepo;
import com.example.pidev1.Repository.ScheduleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleService {

    @Autowired
    EmployerService employerService;
    @Autowired
    ClassroomService classroomService;

    @Autowired
    SubjectService subjectService;

    @Autowired
    LessonRepo lessonRepo;

    @Autowired
    LessonService lessonService;
    @Autowired
    ScheduleRepo scheduleRepo;

    public Schedule planifier(List<Employers> employerDisponibles, List<classroom> classroomDisponibles, List<Subject> subjects) {
        Schedule schedule = new Schedule();

        // Initialiser la liste des cours planifiés
        List<Lesson> LessonPlanifies = new ArrayList<>();

        for (Subject subject : subjects) {

            // Trouver l'enseignant disponible qui préfère cette matière
            Employers enseignantDisponible = null;
            for (Employers enseignant : employerDisponibles) {
                if (enseignant.getSubject().getIdSubject().equals(subject.getIdSubject())) {
                    enseignantDisponible = enseignant;
                    break;
                }
            }
            // Trouver la salle de classe disponible pour cette matière
            classroom salleDisponible = null;
            for (classroom salle : classroomDisponibles) {
                if (salle.getCapacity() >= 20) {
                    salleDisponible = salle;
                    break;
                }
            }

            // Créer le cours et l'ajouter à la liste des cours planifiés
            //Lesson coursPlanifie = new Lesson(enseignantDisponible, salleDisponible, subject);
            Lesson coursPlanifie = new Lesson();
            coursPlanifie.setEmployer(enseignantDisponible);
            coursPlanifie.setClassroom(salleDisponible);
            coursPlanifie.setSubject(subject);
           // lessonRepo.save(coursPlanifie);

            LessonPlanifies.add(coursPlanifie);





        }
        // Enregistrer tous les cours planifiés dans la base de données
        lessonService.saveAllCours(LessonPlanifies);


        schedule.setLessons(LessonPlanifies);
        scheduleRepo.save(schedule);

        return schedule;
    }
}