package com.prueba.test.model;

import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
//import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
@Entity
@Table(name = "Rol", uniqueConstraints = @UniqueConstraint(columnNames = { "nombre" }))
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ROL_SEQ")
    @SequenceGenerator(sequenceName = "rol_seq", initialValue = 1, allocationSize = 1, name = "ROL_SEQ")
    @Column(name = "id_rol")
    private Long idRol;

    @NotEmpty(message = "No debe ser vacio")
    @Column(nullable = false, unique = true)
    private String nombre;
}
