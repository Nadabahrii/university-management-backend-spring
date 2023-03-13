package com.example.pidev1.Controller;


import com.example.pidev1.Entity.Event;
import com.example.pidev1.Entity.Student;
import com.example.pidev1.Repository.EventRepository;
import com.example.pidev1.Repository.StudentRepository;
import com.example.pidev1.Service.EventPDFExporter;
import com.example.pidev1.Service.EventService;
import com.example.pidev1.Service.IEvent;
import com.example.pidev1.Service.QRCodeGenerator;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.lowagie.text.DocumentException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    private EventRepository eventRepository;
    //private final EventRepository eventRepository;

    @Autowired
    private QRCodeGenerator qrCodeGenerator;

    @Autowired
    public EventController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    IEvent ievent;

    //http://localhost:8089/pidev/add-event
    /*@PostMapping("/add-event")
    public Event addEvent(@RequestBody Event event) {
        Event e = eventService.addEvent(event);
        return e;
    }*/
    //http://localhost:8089/pidev/addEvent
    @PostMapping("/addEvent")
    public ResponseEntity<Event> addEvent(@RequestBody Event event) {
        try {
            Event savedEvent = eventService.addEvent(event);
           // sendMail(savedEvent);
            return new ResponseEntity<>(savedEvent, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("estimate-budget/{idevent}/budget")
    public ResponseEntity<Double> getEventBudget(@PathVariable("idevent") Long idevent, @RequestParam boolean needMaterials) {
        Event event = eventService.retrieveEvent(idevent);
        if (event == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        String eventType = event.getType();
        int nbr_of_places = event.getNbr_of_places();
        String place = event.getPlace();
        double estimatedBudget = eventService.estimateBudget(eventType, nbr_of_places, needMaterials, place);
        return new ResponseEntity<>(estimatedBudget, HttpStatus.OK);
    }



    /*@PostMapping("/add-e")
    public ResponseEntity<Object> addEvent(@RequestBody Event event) {
        try {
            Event savedEvent = eventService.addEvent(event);
            return new ResponseEntity<>("Event added successfully with id: " + savedEvent.getIdEvent(), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/


    @GetMapping("/retrieve-all-events")
    public List<Event> getevent () {
        List<Event> listevent = eventService.retrieveAllevents();
        return listevent;
    }

    //http://localhost:8089/pidev/retrieve-event/{{event-id}}
    @GetMapping("/retrieve-event/{idevent}")
    public Event retrieveEvent(@PathVariable("idevent") Long eventId) {
        return eventService.retrieveEvent(eventId);
    }


    //http://localhost:8089/pidev/remove-event/{{event-id}}
    @DeleteMapping("/remove-event/{event-id}")
    public void removeevent(@PathVariable("event-id") Long eventId) {
        eventService.removeEvent(eventId);
    }


    //http://localhost:8089/pidev/events/{{idevent}}
    @PutMapping("/events/{idevent}")
    public Event updateEvent(@PathVariable Long idevent, @RequestBody Event updatedEvent) {
        return eventService.updateEvent(idevent, updatedEvent);
    }


    @GetMapping("/search-event")
    public ResponseEntity<List<Event>> searchEvent(@RequestParam("query") String query){
        return ResponseEntity.ok(ievent.searchEvent(query));
    }

    @GetMapping("/{idEvent}/hasAvailablePlaces")
    public ResponseEntity<Boolean> hasAvailablePlaces(@PathVariable Long idEvent) {
        Event event = eventService.retrieveEvent(idEvent);
        if (event == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        boolean hasAvailablePlaces = eventService.hasAvailablePlaces(event);
        return ResponseEntity.ok(hasAvailablePlaces);
    }




    /*@PostMapping("/sendMail")
    public ResponseEntity<String> sendMail(@RequestBody Event event) {
        try {
            eventService.sendMail(event);
            return ResponseEntity.ok("Email sent successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send email.");
        }
    }*/

    /*@GetMapping("/events")
    public String listEvents(Model model) {
        List<Event> events = eventService.retrieveAllevents();
        model.addAttribute("events", events);
        return "events";
    }*/

    /*---------------------------------------------PDF list of events ------------------------------------------*/
    @RequestMapping(value = "/events/export/pdf", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_PDF_VALUE)
    @ResponseBody
    public ResponseEntity<byte[]> exportToPdf() {
        try {
            List<Event> events = eventService.retrieveAllevents();
            byte[] pdf = EventPDFExporter.export(events);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "events.pdf");
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
            ResponseEntity<byte[]> response = new ResponseEntity<>(pdf, headers, HttpStatus.OK);
            return response;
        } catch (IOException | DocumentException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    };

    @PutMapping("/affecterimagetoevent/{event-id}/{image-id}")
        public void affecterimagetoevent(@PathVariable("event-id") Long idevent,@PathVariable("image-id") Long idimage)
        {
            eventService.affecterimagetoevent(idevent,idimage);
        }
    /*@PostMapping("/{eventId}/students/{studentId}")
    public ResponseEntity<Student> participateToEvent(@PathVariable Long eventId, @PathVariable Long studentId) {
        Student student = eventService.participertoevent(eventId, studentId);
        return ResponseEntity.ok(student);
    }*/

    @PostMapping("/events/{eventId}/participants/{studentId}")
    public ResponseEntity<byte[]> addParticipantToEvent(@PathVariable Long eventId, @PathVariable Long studentId) throws WriterException {
        Event event = eventRepository.findById(eventId).get();
        Student student = studentRepository.findById(studentId).get();

        event.getStudents().add(student);
        student.getEvents().add(event);

        eventRepository.save(event);
        studentRepository.save(student);

        String message = String.format("Student %s has successfully been participated to the event with IdEvent: %d", student.getFirstname() + " " + student.getLastname(), eventId);

        // Generate QR code
        String content = "Participation: " + message;
        BitMatrix bitMatrix = qrCodeGenerator.generateQRCode(content);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        byte[] bytes = outputStream.toByteArray();
        String base64Encoded = Base64.getEncoder().encodeToString(bytes);

        return ResponseEntity.ok().body(bytes);
    }


}
