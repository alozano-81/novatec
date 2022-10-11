package com.prueba.test.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.test.Servicio.EstudianteService;
import com.prueba.test.model.Estudiantes;
import com.prueba.test.repository.EstudiantesRepository;

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

    @RequestMapping(value = "/crear/{id}/{nombre}/{idRol}/{activo}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveUserP(@PathVariable("id") final Long id, @PathVariable("nombre") final String nombre,
            @PathVariable("idRol") final Long idRol, @PathVariable("activo") final char activo)
            throws Exception {
        System.out.println("POST:" + id);

        Estudiantes estcreate = new Estudiantes();
        estcreate = estudianteService.crearEstudiantes(id, nombre, String.valueOf(idRol), activo);

        System.out.println("Ver cretate2: " + estcreate);

        return ResponseEntity.ok("Usuario creado");

    }

    @RequestMapping(value = "/update/{id}/{nombre}/{idRol}/{activo}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveUserUpdate(@PathVariable("id") final Long id,
            @PathVariable("nombre") final String nombre,
            @PathVariable("idRol") final Long idRol, @PathVariable("activo") final char activo)
            throws Exception {
        System.out.println("PUT:" + id);
        Estudiantes estcreate = new Estudiantes();
        estcreate = estudianteService.updateEstudiantes(id, nombre, String.valueOf(idRol), activo);
        return ResponseEntity.ok("Usuario creado");

    }

}
