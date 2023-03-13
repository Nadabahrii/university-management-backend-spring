package com.example.pidev1.Controller;


import com.example.pidev1.Entity.Host;
import com.example.pidev1.Entity.Participation;
import com.example.pidev1.Service.ParticipationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class ParticipationController {

    ParticipationService participationService;

    @PostMapping("/add-Participation")
    public Participation addParticiation(@RequestBody Participation p) {
        Participation participation = participationService.addParticipation(p);
        return participation;
    }

    @GetMapping("/retrieve-all-participations")
    public List<Participation> getparticipation () {
        List<Participation> listParticipation = participationService.retrieveAllParticipations();
        return listParticipation;
    }

    @GetMapping("/retrieve-participation/{participation-id}")
    public Participation retrieveParticipation(@PathVariable("participation-id") Long participationId) {
        return participationService.retrieveParticipation(participationId);
    }

    @DeleteMapping("/remove-participation/{participation-id}")
    public void removeparticipation(@PathVariable("participation-id") Long participationId) {
        participationService.removeParticipation(participationId);
    }

    @PutMapping("/participations/{idp}")
    public Participation updateParticipation(@PathVariable Long idp, @RequestBody Participation updatedParticipation) {
        return participationService.updateParticipation(idp, updatedParticipation);
    }
}
