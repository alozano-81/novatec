package com.prueba.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prueba.test.model.Estudiantes;

@Repository
public interface EstudiantesRepository extends JpaRepository<Estudiantes, Long> {

}
