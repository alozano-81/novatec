package com.prueba.test.Servicio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.test.model.Estudiantes;
import com.prueba.test.repository.EstudiantesRepository;

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

    public Estudiantes crearEstudiantes(Long id, String nombre, String rol, char activo) {
        Estudiantes estcreate = new Estudiantes();

        estcreate.setIdEst(id + 2);
        estcreate.setNombre(nombre);
        estcreate.setRol(rol);
        estcreate.setActivo(String.valueOf(activo));
        estudiantesRepository.saveAndFlush(estcreate);
        return estcreate;
    }

    public Estudiantes updateEstudiantes(Long id, String nombre, String rol, char activo) {
        Estudiantes estcreate = new Estudiantes();
        estcreate.setIdEst(id);
        estcreate.setNombre(nombre);
        estcreate.setRol(rol);
        estcreate.setActivo(String.valueOf(activo));
        estudiantesRepository.saveAndFlush(estcreate);
        return estcreate;
    }

}
