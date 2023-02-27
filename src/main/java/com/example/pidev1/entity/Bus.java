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
@Table( name = "Bus")
public class Bus implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idBus")
    private Long idBus;
    private String Traject;
    private String Driver;
    private String Capacity;
    private String Status;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="bus")
    private Set<Student> students;









}
