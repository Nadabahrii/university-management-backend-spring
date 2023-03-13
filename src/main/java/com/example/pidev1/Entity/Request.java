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
@Table( name = "Request")
public class Request implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idRequest")
    private Long idRequest;
    @Enumerated(EnumType.STRING)
    private Follow_upRequest follow_upRequest;
    private String startTime;

    private String endTime;

    private int numAttendees;

    @OneToOne
    private Employers employers;

    @OneToOne
    private Student student;
}
