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
@Table( name = "Publication")
public class Publication implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idPub")
    private long idPub;
    private String Author;
    private String Context;
    private String Topic;
    @Temporal(TemporalType.DATE)
    private Date date_of_publication;
    private long nbr_likes_Pub;
    private long nbr_dislikes_Pub;

    @ManyToOne
    Student students;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="publication")
    private Set<Comment> comments;






}
