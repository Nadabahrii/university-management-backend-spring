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
@Table( name = "Host")
public class Host implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idH")
    private Long idH;
    private String name;
    private Long NBEtage;
    private Long NBChambre;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="host")
    private Set<Student> students;
}
