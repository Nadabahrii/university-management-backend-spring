package com.example.pidev1.Service;
import com.example.pidev1.Entity.Employers;
import com.example.pidev1.Entity.Professeur;
import com.example.pidev1.Entity.Rdv;
import com.example.pidev1.Repository.IStudentRepository;
import com.example.pidev1.Repository.ProfesseurRepository;
import com.example.pidev1.Repository.RdvRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
@Service
@Primary
@AllArgsConstructor
public  class RdvServiceImp implements IRdvServices{
    /*@Autowired
    private ClassRepository roomRepository;*/
    @Autowired
    RdvRepository iRdvRepository;
    @Autowired
    ProfesseurRepository professeurRepository;
    EmailService emailService;
    TwilioService twilioService;

    @Autowired
    IStudentRepository studentRepository;

    @Override
    public String getDay(String date) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime dateTime = LocalDateTime.parse(date, inputFormatter);
        String formattedDate = dateTime.format(outputFormatter);
        return formattedDate;
    }

    @Override
    public List<String> getAvailableStartTimesForClasseInDay(String day, Class classe) {
        //trouver les rdvs dans cette salle durant ce jour
        List<Rdv> rdvsByDay = iRdvRepository.findAllByStartTimeLike("%" + day + "%");
        List<Rdv> rdvsByClasse = iRdvRepository.findAllByClasse(classe);
        List<Rdv> rdvs = new ArrayList<>(rdvsByClasse);
        rdvs.retainAll(rdvsByDay);
        // trouver  les temps deja pris
        List<String> takenStartTimes = new ArrayList<>();
        for (Rdv rdv : rdvs){
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalDateTime dateTime = LocalDateTime.parse(rdv.getStartTime(), inputFormatter);
            String formattedDate = dateTime.format(outputFormatter);
            takenStartTimes.add(formattedDate);
        }
        //list de tous les temps dispo
        List<String> workingHours = new ArrayList<>(Arrays.asList("08:00", "08:30", "09:00", "09:30", "10:00", "10:30",
                "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00",
                "15:30", "16:00", "16:30"));
        //supprimer les temps non disponibles
        workingHours.removeAll(takenStartTimes);
        return workingHours;
    }

    @Override
    public List<String> getAvailableStartTimesForEmployerInDay(String day, Employers employer) {
        //trouver les rdvs de cet employer durant ce jour
        List<Rdv> rdvsByDay = iRdvRepository.findAllByStartTimeLike("%"+ day + "%");
        List<Rdv> rdvsByEmployers = iRdvRepository.findAllByEmployer(employer);
        List<Rdv> rdvs = new ArrayList<>(rdvsByEmployers);
        rdvs.retainAll(rdvsByDay);
        //list de tous les temps dispo
        List<String> workingHours = Arrays.asList("08:00", "08:30", "09:00", "09:30", "10:00", "10:30",
                "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00",
                "15:30", "16:00", "16:30");

        // trouver  les temps deja pris
        List<String> takenStartTimes = new ArrayList<>();
        for (Rdv rdv : rdvs){
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalDateTime dateTime = LocalDateTime.parse(rdv.getStartTime(), inputFormatter);
            String formattedDate = dateTime.format(outputFormatter);
            takenStartTimes.add(formattedDate);
        }

        //supprimer les temps non disponibles
        workingHours.removeAll(takenStartTimes);
        return workingHours;
    }

    @Override
    public  List<String> getNext30Days(String startDateStr) {
        List<String> dates = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate startDate = LocalDate.parse(startDateStr, formatter);

        for (int i = 1; i < 31; i++) {
            LocalDate date = startDate.plusDays(i);
            String formattedDate = date.format(formatter);
            dates.add(formattedDate);
        }
        return dates;
    }

    @Override
    public String incrementTime(String timeString) {
        LocalTime time = LocalTime.parse(timeString, DateTimeFormatter.ofPattern("HH:mm"));
        LocalTime incrementedTime = time.plusMinutes(30);
        String resultString = incrementedTime.format(DateTimeFormatter.ofPattern("HH:mm"));
        return resultString;
    }

    @Override
    public List<Rdv> retreiveAllRdvs() {
        return iRdvRepository.findAll();
    }

    @Override
    public void addRdvbyStoRTe(Rdv rdv, Long id) {

        Professeur professeur = professeurRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Post not found"));
{
        rdv.setProfesseur(professeur);
        iRdvRepository.save(rdv);
        emailService.sendEmail(professeur.getEmailAddress(),"hello you rdv " , "test");
}
     /*   @Override
        public void addRdvbyStoRTe(Rdv rdv, Long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Post not fou  nd"));
        rdv.setStudentt(student);
        iRdvRepository.save(rdv);
        twilioService.sendSMS(student.getContact());*/

    }

    @Override
    public Rdv addRdv (Rdv rdv ) {return iRdvRepository.save(rdv);}
    @Override
    public Rdv update (Rdv rdv  ,Long idRdv) {return iRdvRepository.save(rdv);}
    @Override
    public void  removeRdv(Long idRdv) {iRdvRepository.deleteById(idRdv);}


}
