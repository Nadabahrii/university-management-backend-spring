package com.example.pidev1.Service;

import com.example.pidev1.Entity.Professeur;

import java.util.List;

public interface IProfessorServices {
    public List<Professeur> retreiveAllProfs();
    public Professeur addProf (Professeur professeur );
    public Professeur updateProf (Professeur professeur  ,Long idProfesseur);
    public void  removeProf(Long idProfesseur );
}
