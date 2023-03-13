package com.example.pidev1.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table( name = "Employers")

public class Employers implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name="idEmployer")
    private Long idEmployer;
    private String Firstname;
    private String Lastname;
    private String Email;
    private Boolean isDispo;
    private String Address;
    private String Contact;
    private Long Age;
    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Class>classes;

    @OneToOne(mappedBy="employers")
    @JsonIgnore
    private Request request;

    @OneToOne
    private Rdv rdv;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Department> departments;

    @ManyToOne
    Subject subject;
}
