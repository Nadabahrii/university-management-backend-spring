package com.example.pidev1.Service;

import com.example.pidev1.Entity.Host;
import com.example.pidev1.Entity.Participation;

import java.util.List;

public interface IParticipation {

    List<Participation> retrieveAllParticipations();

    Participation addParticipation (Participation p);

    Participation retrieveParticipation(Long idParticipation);

    void removeParticipation(Long idParticipation);

    Participation updateParticipation(Long idParticipation, Participation updatedParticipation);
}
