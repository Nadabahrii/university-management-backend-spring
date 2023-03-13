package com.example.pidev1.Service;

import com.example.pidev1.Entity.Event;
import com.example.pidev1.Entity.ImageData;
import com.example.pidev1.Entity.Participation;
import com.example.pidev1.Entity.Student;

import com.example.pidev1.Repository.EventRepository;
import com.example.pidev1.Repository.FileDataRepository;
import com.example.pidev1.Repository.StorageRepository;
import com.example.pidev1.Repository.StudentRepository;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.mail.Session;
import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EventService implements IEvent {


        @Autowired
        private EventRepository eventRepository;

        @Autowired
        private JavaMailSender mailSender;


        @Autowired
        private StudentRepository studentRepository;

        @Autowired
        private StorageRepository storageRepository;

        public List<Event> listAll() {
                return eventRepository.findAll(Sort.by("Name Event").ascending());
        }

        @Override
        public List<Event> retrieveAllevents() {
                return eventRepository.findAll();
        }

        @Override
        public Event addEvent(Event event) throws Exception {
                Date currentDate = new Date();
                if (event.getDate().before(currentDate)) {
                        throw new Exception("Event date cannot be in the past");
                }
                Event savedEvent = eventRepository.save(event);
                //sendMail(savedEvent);
                return savedEvent;
        }//sendMail(savedEvent);
        //System.out.println("Event saved and email sent successfully.");
        @Autowired
        public EventService(EventRepository eventRepository, StudentRepository studentRepository) {
                this.eventRepository = eventRepository;
                this.studentRepository = studentRepository;
        }


        //Launching the APP0 event every year automatically
        @Scheduled(cron = "0 0 0 10 9 ?") // run at midnight on 10th September every year
        public void addApp0Event() {
                Event app0 = new Event();
                app0.setNameEvent("APP0");
                app0.setType("Annual Event");
                app0.setDescription("This is an annual event that takes place on 10th September every year.");
                app0.setPlace("University Campus");
                app0.setDate(Date.from(LocalDate.of(LocalDate.now().getYear(), 9, 10).atStartOfDay(ZoneId.systemDefault()).toInstant())); // set the date to 10th September of the current year
                app0.setNbr_of_places(1000); // set the maximum number of places available

                // Save the event to the database
                Event savedEvent = eventRepository.save(app0);
        }


        @Override
        public Event retrieveEvent(Long idEvent) {
                Optional<Event> event = eventRepository.findById(idEvent);
                return event.orElse(null);

                //return eventRepository.findById(idEvent)..orElse(null);
        }

        @Override
        public void removeEvent(Long idEvent) {
                eventRepository.deleteById(idEvent);
        }

        @Override
        public Event updateEvent(Long idEvent, Event updatedEvent) {
                Optional<Event> optionalEvent = eventRepository.findById(idEvent);
                if (optionalEvent.isPresent()) {
                        Event event = optionalEvent.get();
                        event.setNameEvent(updatedEvent.getNameEvent());
                        event.setType(updatedEvent.getType());
                        event.setDescription(updatedEvent.getDescription());
                        event.setPlace(updatedEvent.getPlace());
                        event.setDate(updatedEvent.getDate());
                        event.setNbr_of_places(updatedEvent.getNbr_of_places());
                        return eventRepository.save(event);
                } else {
                        return null;
                }
        }

        @Override
        public List<Event> searchEvent(String query) {
                List<Event> events = eventRepository.searchEvent(query);
                return events;
        }

        @Override
        public boolean hasAvailablePlaces(Event event) {
                return event.getNbr_of_places() > event.getStudents().size();
        }


        public void sendEmail(String toEmail,
                              String subject,
                              String body
        ) {
                SimpleMailMessage message = new SimpleMailMessage();
                message.setFrom("iheb.benslama1999@gmail.com");
                message.setTo(toEmail);
                message.setText(body);
                message.setSubject(subject);
                mailSender.send(message);
                System.out.println("Mail Send...");


        }

        public void sendMail(Event event) {
                // Check if the event type is defined
                String eventType = event.getType();

                if (eventType == null) {
                        System.out.println("No email will be sent since the event type is not defined");
                        return;
                }

                // Find students with matching hobbies
                /*List<Student> matchingStudents = event.getStudents().stream()
                        .filter(student -> eventType.equalsIgnoreCase(student.getHobby()))
                        .collect(Collectors.toList());
                System.out.println("Number of matching students found: " + matchingStudents.size());*/
                List<Student> students = studentRepository.findAll();
                List<Student> matchingStudents=null;
                for (Student s :students){
                        if(s.getHobby().equals(eventType))
                                matchingStudents.add(s);
                }



                // Check if any matching students were found
                if (matchingStudents.isEmpty()) {
                        System.out.println("No email will be sent since there are no students with a matching hobby");
                        return;
                }

                // Send an email to each matching student
                for (Student student : matchingStudents) {
                        String toEmail = student.getEmail();
                        String subject = String.format("New %s event added", eventType);
                        String body = String.format("Dear %s,\n\nA new %s event named \"%s\" has been added. Don't miss it!\n\nBest regards,\nThe event team",
                                student.getFirstname(), eventType, event.getNameEvent());
                        sendEmail(toEmail, subject, body);
                }
        }



                public double estimateBudget(String eventType, int nbr_of_places, boolean needMaterials, String place) {
                        double baseCost = 0.0;

                        switch (eventType) {
                                case "Conference":
                                        baseCost = 500.0;
                                        break;
                                case "Workshop":
                                        baseCost = 200.0;
                                        break;
                                case "Seminar":
                                        baseCost = 300.0;
                                        break;
                                case "Networking":
                                        baseCost = 50.0;
                                        break;
                                case "Social":
                                        baseCost = 100.0;
                                        break;
                                default:
                                        // Unknown event type
                                        return 0.0;
                        }

                        double costPerAttendee = baseCost / 50.0;  // Assumes 50 attendees per event
                        double totalCost = costPerAttendee * nbr_of_places;

                        if (needMaterials) {
                                // Add material cost based on number of attendees
                                double materialCost = 5.0 * nbr_of_places;
                                totalCost += materialCost;
                        }

                        if (place.equals("On-Campus")) {
                                // Add on-campus location cost
                                double campusLocationCost = 50.0;
                                totalCost += campusLocationCost;
                        } else if (place.equals("Off-Campus")) {
                                // Add off-campus location cost
                                double offCampusLocationCost = 200.0;
                                totalCost += offCampusLocationCost;
                        }

                        return totalCost;
                }

                public Event affecterimagetoevent(Long Idevent,Long Idimage){
                Event e = eventRepository.findById(Idevent).get();
                ImageData imageData = storageRepository.findById(Idimage).get();
                e.setImage(imageData);
                eventRepository.save(e);

                return e;
                }


                public Student participertoevent(Long idevent,Long idstudent){
                Event e = eventRepository.findById(idevent).get();
                Student s = studentRepository.findById(idstudent).get();
                //e.getStudents().add(s);
                s.getEvents().add(e);
                //eventRepository.save(e);
                        return  s;
                }
        }


        /*public void participateInEvent(Long eventId) {
                // Retrieve the event object from the database
                Event event = retrieveEvent(eventId);
                if (event == null) {
                        // Handle case where event with given ID does not exist
                }
                if (events == null) {
                        events = new HashSet<>();
                }
                events.add(event);
                if (event.getStudents() == null) {
                        event.setStudents(new HashSet<>());
                }
                event.getStudents().add(this);
                Participation participation = new Participation(this.getNameP() + " " + this.getLastname(), this.getEmail(), event);
                if (event.getParticipations() == null) {
                        event.setParticipations(new HashSet<>());
                }
                event.getParticipations().add(participation);
        }*/













