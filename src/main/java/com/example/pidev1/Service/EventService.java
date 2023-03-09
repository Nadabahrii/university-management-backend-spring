package com.example.pidev1.Service;

import com.example.pidev1.Entity.Event;
import com.example.pidev1.Entity.Student;
import com.example.pidev1.Repository.EventRepository;
import com.example.pidev1.Repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

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

    @Override
    public List<Event> retrieveAllevents() {
        return eventRepository.findAll();
    }

    @Override
    public Event addEvent(Event event) {
        Event savedEvent = eventRepository.save(event);
        sendMail(savedEvent);
        System.out.println("Event saved and email sent successfully.");
        return savedEvent;
    }

    @Override
    public Event retrieveEvent(Long idEvent) { Optional<Event> event = eventRepository.findById(idEvent);
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
        List<Student> matchingStudents = event.getStudents().stream()
                .filter(student -> eventType.equalsIgnoreCase(student.getHobby()))
                .collect(Collectors.toList());
        System.out.println("Number of matching students found: " + matchingStudents.size());


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
}
