package com.example.pidev1.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table( name = "Comment")
public class Comment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idComm")
    private Long idComm;
    private String Comment_text;
    private String Author;
    private long nbr_likes_com;
    private long nbr_dislikes_com;
    @Temporal(TemporalType.DATE)
    private Date date_of_comm_post;
    @JsonIgnore
    @ManyToOne
    Publication publication;
    @JsonIgnore
    @ManyToOne
    Student studentss;









}
