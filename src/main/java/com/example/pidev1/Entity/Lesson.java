package com.example.pidev1.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table( name = "Lesson")
public class Lesson implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idLesson")
    private Long idLesson;
    private LocalTime start;
    private LocalTime end;

    @ManyToOne
    //@JoinColumn(name="Firstname",referencedColumnName="firstname", insertable=false, updatable=false)
    private Employers employer;

    @ManyToOne
    //@JoinColumn(name="name",referencedColumnName="name", insertable=false, updatable=false)
    private classroom classroom;

    @ManyToOne
    //@JoinColumn(name="Subjectname",referencedColumnName="subjectname", insertable=false, updatable=false)
    private Subject subject;

    @ManyToOne
    Schedule schedule;



}
