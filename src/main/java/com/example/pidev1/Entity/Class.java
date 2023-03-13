package com.example.pidev1.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table( name = "Class")
public class Class implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idClass")
    private Long idClass;
    private String Name;
    private Long Capacity;

    @ManyToMany(mappedBy="classes", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Employers> employerss;

    @ManyToMany(mappedBy="classSet", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Student> students;
}
