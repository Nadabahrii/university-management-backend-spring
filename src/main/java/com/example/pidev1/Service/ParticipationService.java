package com.example.pidev1.Service;

import com.example.pidev1.Entity.Event;
import com.example.pidev1.Entity.Host;
import com.example.pidev1.Entity.Participation;
import com.example.pidev1.Repository.EventRepository;
import com.example.pidev1.Repository.ParticipationRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor

public class ParticipationService implements IParticipation {

    @Autowired
    ParticipationRepository participationRepository;


    @Override
    public List<Participation> retrieveAllParticipations() {
        return participationRepository.findAll();
    }

    @Override
    public Participation addParticipation(Participation p) {
        return participationRepository.save(p);
    }

    @Override
    public Participation retrieveParticipation(Long idParticipation) {
        return participationRepository.findById(idParticipation).get();
    }

    @Override
    public void removeParticipation(Long idParticipation) {
        participationRepository.deleteById(idParticipation);
    }

    @Override
    public Participation  updateParticipation(Long idParticipation, Participation updatedParticipation) {
        Optional<Participation> optionalParticipation = participationRepository.findById(idParticipation);
        if (optionalParticipation.isPresent()) {
            Participation participation = optionalParticipation.get();
            participation.setNameP(updatedParticipation.getNameP());
            participation.setEmailP(updatedParticipation.getEmailP());
            return participationRepository.save(participation);
        } else {
            return null;
        }
    }
}
