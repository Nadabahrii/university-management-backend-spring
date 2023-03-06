package com.example.pidev1.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
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
    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Class> classSet;
    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Department> departments;
    @JsonIgnore
    @OneToOne(mappedBy="student")
    private Request requests;
    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Event> events;
    @JsonIgnore
    @ManyToOne
    Host host;

    @ManyToOne
    Bus bus;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy="students")
    private Set<Publication> publications;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy="studentss")
    private Set<Comment> comments;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy="studentt")
    private Set<Rdv> rdvs;


    public Set<Publication> getLikedPublications() {


        Set<Publication> likedPublications = new HashSet<>();
        for (Publication publication : publications) {
            if (publication.getNbr_likes_Pub() > 0 ) {
                likedPublications.add(publication);
            }
        }
        return likedPublications;
    }
    public Set<Publication> getDislikedPublications() {
        Set<Publication> dislikedPublications = new HashSet<>();
        for (Publication publication : publications) {
            if (publication.getNbr_dislikes_Pub() > 0 ) {
                dislikedPublications.add(publication);
            }
        }
        return dislikedPublications;
    }

    public Long getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(Long idStudent) {
        this.idStudent = idStudent;
    }
}
