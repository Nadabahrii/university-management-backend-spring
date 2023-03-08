package com.example.pidev1.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table( name = "Event")
public class Event  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idEvent")
    private Long idEvent;
    private String nameEvent;
    private String Type;
    private String description;
    private String place;
    @Temporal(TemporalType.DATE)
    private Date date;
    private Long nbr_of_places;
    /*@Lob
    private byte[] image;*/


    @ManyToMany(mappedBy="events", cascade = CascadeType.ALL)
    private Set<Student> students;

    public Set<Student> getStudents() {
        return students;
    }


    @ManyToMany(mappedBy="events", cascade = CascadeType.ALL)
    private Set<Participation> participations;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "photo_id", referencedColumnName = "id")
    private Photo photo;

    /*@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id", referencedColumnName = "idImage")
    private File image;*/


}
