package com.example.pidev1.entity;

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

    @OneToOne
    private Employers employers;

    @OneToOne
    private Student student;
}
