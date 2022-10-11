package com.prueba.test.model;

import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

import lombok.Data;

@Data
@Entity
@Table(name = "Estudiantes", uniqueConstraints = @UniqueConstraint(columnNames = { "nombre" }))
public class Estudiantes {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ESTUDIANTES_SEQ")
    @SequenceGenerator(sequenceName = "estudiantes_seq", initialValue = 1, allocationSize = 1, name = "ESTUDIANTES_SEQ")
    @Column(name = "id")
    private Long idEst;

    // @NotEmpty(message = "No debe ser vacio")
    @Column(nullable = false, unique = true)
    // @Length(max = 50, message = "{label.longitudmax}")
    private String nombre;

    @Column(nullable = false, unique = true)
    private String rol;

    @Column(nullable = false, unique = true)
    private String activo;

}
