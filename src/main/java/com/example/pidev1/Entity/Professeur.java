package com.example.pidev1.Entity;

import com.example.pidev1.Entity.Rdv;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table( name = "Professeur")
public class Professeur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;
    @Column(nullable = false)
    private String emailAddress;


    @Column(nullable = false)
    private String prenom;

    @Column(nullable = false)
    private String cv;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy="professeur")
    private Set<Rdv> rdvs;

    /*@Column(nullable = false)
    private String departement;
*/
}

