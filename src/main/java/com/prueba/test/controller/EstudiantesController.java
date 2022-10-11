package com.prueba.test.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.test.Servicio.EstudianteService;
import com.prueba.test.model.Estudiantes;
import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("estudiantes")
public class EstudiantesController {
    @Autowired
    EstudianteService estudianteService;

    @GetMapping(value = "/lista", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Estudiantes>> consultarEstudiantes() {
        List<Estudiantes> est = new ArrayList<>();
        est = estudianteService.consultarEstudiantes();
        System.out.println("EStudi:" + est);

        try {
            return new ResponseEntity<>(est, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(est, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
