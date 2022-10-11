package com.prueba.test.Servicio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.prueba.test.model.Estudiantes;
import com.prueba.test.repository.EstudiantesRepository;
import org.springframework.http.MediaType;

@Service
public class EstudianteService {
    @Autowired
    EstudiantesRepository estudiantesRepository;

    public List<Estudiantes> consultarEstudiantes() {
        List<Estudiantes> est = new ArrayList<>();
        List<Estudiantes> rest = new ArrayList<>();
        est = estudiantesRepository.findAll();
        for (Estudiantes re : est) {
            Estudiantes listaEst = new Estudiantes();
            listaEst.setIdEst(re.getIdEst());
            listaEst.setNombre(re.getNombre());
            listaEst.setRol(re.getRol());
            listaEst.setActivo(re.getActivo());
            rest.add(listaEst);
        }
        return rest;
    }

    @RequestMapping(value = "/crear/{id}/{nombre}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveUserP(@PathVariable("id") final Long id, @PathVariable("nombre") final String nombre)
            throws Exception {

        Estudiantes estcreate = new Estudiantes();

        estcreate.setIdEst(id);
        estcreate.setNombre(nombre);
        estcreate.setActivo("S");

        System.out.println("Ver cretate2: " + estcreate);

        estudiantesRepository.saveAndFlush(estcreate);
        return ResponseEntity.ok("Usuario actualizado");

    }

}
