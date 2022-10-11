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
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;

import lombok.Data;

@Data
@Entity
@Table(name = "Usuario", uniqueConstraints = @UniqueConstraint(columnNames = { "nombre" }))
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ")
    @SequenceGenerator(sequenceName = "user_seq", initialValue = 1, allocationSize = 1, name = "USER_SEQ")
    @Column(name = "id_usuario")
    private Long usuaId;

    @NotEmpty(message = "No debe ser vacio")
    @Column(nullable = false, unique = true)
    // @Length(max = 50, message = "{label.longitudmax}")
    private String nombre;

    @Column(name = "activo", nullable = false, unique = true)
    private int activo;

    // Relacion en usuario y roles one to one.
    @OneToOne()
    @JoinColumn(name = "id_rol", insertable = false, updatable = false)
    private Rol role;

}
