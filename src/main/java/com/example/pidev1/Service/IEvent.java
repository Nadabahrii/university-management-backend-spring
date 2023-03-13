package com.example.pidev1.Service;

import com.example.pidev1.Entity.Event;
import com.example.pidev1.Entity.Student;
import com.example.pidev1.Repository.EventRepository;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfPTable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface IEvent {

    List<Event> retrieveAllevents();

    Event addEvent (Event event) throws Exception;

    Event retrieveEvent(Long idEvent);

    void removeEvent(Long idEvent);

    Event updateEvent(Long idEvent, Event updatedEvent);

    List<Event> searchEvent(String query);


     boolean hasAvailablePlaces(Event event);

     void sendEmail(String toEmail,
                   String subject,
                   String body
    );

     void sendMail(Event event);


    //void decrementPlaces();

    double estimateBudget(String eventType, int numAttendees, boolean needMaterials, String location);

    Event affecterimagetoevent(Long Idevent,Long Idimage);

    Student participertoevent(Long idevent, Long idstudent);





}

