package com.example.pidev1.Service;

import com.example.pidev1.Entity.Event;

import java.util.List;

public interface IEvent {
    List<Event> retrieveAllevents();

    Event addEvent (Event event);

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
}
