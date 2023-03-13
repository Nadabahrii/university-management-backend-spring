package com.example.pidev1.Controller;

import com.example.pidev1.Entity.Class;
import com.example.pidev1.Entity.Employers;
import com.example.pidev1.Entity.Rdv;
import com.example.pidev1.Entity.Student;
import com.example.pidev1.Repository.EmployersRepository;
import com.example.pidev1.Service.ClassServiceImpl;
import com.example.pidev1.Service.ProfessorServicesImp;
import com.example.pidev1.Service.RdvServiceImp;
import com.example.pidev1.Service.StudentServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/Rdv")

public class RdvController {

    @Autowired
    RdvServiceImp rdvServiceImp;
    @Autowired
    StudentServiceImp studentServiceImp;
    @Autowired
    ClassServiceImpl classService;
    @Autowired
    EmployersRepository employersRepository;
    @Autowired
    ProfessorServicesImp professeurService;



//    @PostMapping
//    public Rdv bookRoom(@RequestBody Request bookingRequest) {
//        String startTime = bookingRequest.getStartTime();
//        String endTime = bookingRequest.getEndTime();
//        int numAttendees = bookingRequest.getNumAttendees();
//        return rdvServiceImp.bookRoom(startTime, endTime, numAttendees);
//    }

    @GetMapping("/getRdv")
    List<Rdv> getRdv() {
        return rdvServiceImp.retreiveAllRdvs();}

    @PostMapping("/addRdv")
    Rdv addRdv(@RequestBody Rdv rdv) {
        return rdvServiceImp.addRdv(rdv);
    }
    @PostMapping("/addRdvvType/{id}")
    void addRdv(@RequestBody Rdv rdv,@PathVariable("id") Long id) {
        rdvServiceImp.addRdvbyStoRTe(rdv,id);
    }
    @PutMapping("/update/{id}")
    Rdv update(@RequestBody Rdv rdv ,@PathVariable("id") Long idRdv) {
        return rdvServiceImp.update(rdv,  idRdv);
    }

    @DeleteMapping("/delete/{id}")
    void removeRdv(@PathVariable("id") Long idRdv) {
        rdvServiceImp.removeRdv(idRdv);
    }

    @GetMapping("/showAvailableDays")
    Set<String> showAvailableDaysForSelectedTime(@RequestParam("time") String time){

        //find current day
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String currentDay = currentDate.format(formatter);
        //find the list of the next 30 days
        List<String> next30Days = rdvServiceImp.getNext30Days(currentDay);
        //get all classes
        List<Class> allClasses = classService.findAllClasses();
        //get all employers
        List<Employers> allEmployers = employersRepository.findAll();
        //matching algorithm to find available days  (time format = HH:mm)
        Set<String> availableDays = new HashSet<>();

        List<String> availableTimesClasse = new ArrayList<>() ;
        for (String day:next30Days){

            for (Class classe : allClasses){
                //availableTimesClasse = rdvServiceImp.getAvailableStartTimesForClasseInDay(day,classe);

                if (availableTimesClasse.contains(time)){

                    for (Employers employer : allEmployers){
                        List<String> availableTimesEmployer = rdvServiceImp.getAvailableStartTimesForEmployerInDay(day,employer);

                        if (availableTimesEmployer.contains(time)){
                            availableDays.add(day);
                        }
                    }
                }
            }
        }

        return availableDays;
    }

   /* @PostMapping ("/student/createRdv/{id}")
    Rdv createRdvStudent(@PathVariable int id , @RequestParam("time") String time ,@RequestParam("day") String day){
        //find student
        Student student = studentServiceImp.findStudentById(id);
        //get all classes
        List<Class> allClasses = classService.findAllClasses();
        //get all employers
        List<Employers> allEmployers = employersRepository.findAll();
        //matching algorithm   (time format = HH:mm)
        Class foundClasse = null;
        Employers foundEmployer = null;
        for (Class classe : allClasses){
           // List<String> availableTimesClasse = rdvServiceImp.getAvailableStartTimesForClasseInDay(day,classe);
            if (availableTimesClasse.contains(time)){
                foundClasse = classe;
                for (Employers employer : allEmployers){
                    List<String> availableTimesEmployer = rdvServiceImp.getAvailableStartTimesForEmployerInDay(day,employer);
                    if (availableTimesEmployer.contains(time)){
                        foundEmployer = employer;
                        break;
                    }
                }
            }
        }
        if (!( foundClasse==null) || !(foundEmployer == null)) {
            Rdv rdv = new Rdv();
            rdv.setStartTime(day + " " + time);
            rdv.setEndTime(day + " " + rdvServiceImp.incrementTime(time));
            //rdv.setClasse(foundClasse);
            rdv.setStudentt(student);
            rdv.setEmployer(foundEmployer);
            rdvServiceImp.addRdv(rdv);
            return  rdv;
        }
        return  null;
    }*/



}



