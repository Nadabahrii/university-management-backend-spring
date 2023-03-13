package com.example.pidev1.Service;

import com.example.pidev1.Entity.Professeur;
import com.example.pidev1.Repository.ProfesseurRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class ProfessorServicesImp implements  IProfessorServices{
    private final ProfesseurRepository professeurRepository;
    @Override
    public List<Professeur> retreiveAllProfs() {
        return professeurRepository.findAll();
    }

    @Override
    public Professeur addProf(Professeur professeur) {
        return professeurRepository.save(professeur);
    }

    @Override
    public Professeur updateProf(Professeur professeur, Long idProfesseur) {
        Professeur professeur1 = professeurRepository.findById(idProfesseur).get();
        professeur1.setCv(professeur.getCv());
        professeur1.setNom(professeur.getNom());
        professeur1.setPrenom(professeur.getPrenom());
        professeur1.setEmailAddress(professeur.getEmailAddress());
        return professeurRepository.save(professeur1);
    }

    @Override
    public void removeProf(Long idProfesseur) {
        Professeur professeur1 = professeurRepository.findById(idProfesseur).get();
        professeurRepository.delete(professeur1);
    }
}
