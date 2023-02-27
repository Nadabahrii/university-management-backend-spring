package com.example.pidev1.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table( name = "Student")
public class Student implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idStudent")
    private Long idStudent;
    private String Firstname;
    private String Lastname;
    private String Email;
    private String Address;
    private String Contact;
    private Long Age;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Class> classSet;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Department> departments;

    @OneToOne(mappedBy="student")
    private Request requests;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Event> events;

    @ManyToOne
    Host host;

    @ManyToOne
    Bus bus;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="students")
    private Set<Publication> publications;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="studentss")
    private Set<Comment> comments;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="studentt")
    private Set<Rdv> rdvs;


}
