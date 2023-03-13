package com.example.pidev1.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table( name = "Rdv")
public class Rdv implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idRdv")
    private Long idRdv;
    private String startTime;
    private String endTime;
    @Enumerated(value = EnumType.STRING)
    private CandidateType candidateType;
    @ManyToOne
    @JoinColumn(name = "idClass")
    private Class classe;
    @ManyToOne
    Student studentt;
    @ManyToOne
    Professeur professeur;
    @ManyToOne
    Employers employer;


    public Rdv(String startTime, String endTime) {
    }


}
