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
@Table( name = "Class")
public class Class implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idClass")
    private Long idClass;
    private String Name;

    @ManyToMany(mappedBy="classes", cascade = CascadeType.ALL)
    private Set<Employers> employers;

    @ManyToMany(mappedBy="classSet", cascade = CascadeType.ALL)
    private Set<Student> students;
}
