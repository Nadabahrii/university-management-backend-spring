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
    private Employers employer;

    @ManyToOne
    private classroom classroom;

    @ManyToOne
    private Subject subject;

    @ManyToOne
    Schedule schedule;


}
