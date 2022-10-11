package com.prueba.test.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import java.util.Optional;

import javax.validation.Valid;

import java.util.ArrayList;
import java.util.List;

import com.prueba.test.ViewModels.RolViewModel;
import com.prueba.test.ViewModels.UsuarioViewModel;
import com.prueba.test.model.Usuario;
import com.prueba.test.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import java.math.BigDecimal;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("users")
public class UsersController {

    @Autowired
    private UserRepository repository;

    @RequestMapping(value = "/find/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> find(@PathVariable("id") final Long id) {
        // repository.findById(id)
        List<Object[]> ver = repository.buscar();
        System.out.println("Verr; " + ver.get(0)[4].getClass().getSimpleName());
        List<UsuarioViewModel> lista = new ArrayList<UsuarioViewModel>();
        for (int i = 0; i < ver.size(); i++) {
            UsuarioViewModel obj = new UsuarioViewModel();
            obj.setIdUsuario((BigDecimal) ver.get(i)[0]);
            obj.setNombre((String) ver.get(i)[1]);
            obj.setIdRol((BigDecimal) ver.get(i)[2]);
            obj.setActivo2((BigDecimal) ver.get(i)[3]);
            obj.setRolNombre((String) ver.get(i)[4]);
            lista.add(obj);
        }
        return ResponseEntity.ok(lista);
    }

    // Consultar roles
    @RequestMapping(value = "/get-roles", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getRoles() {
        // repository.findById(id)
        List<Object[]> ver = repository.buscarRoles();
        System.out.println("VerrRol; " + ver.get(0)[1].getClass().getSimpleName());
        List<RolViewModel> lista = new ArrayList<RolViewModel>();
        for (int i = 0; i < ver.size(); i++) {
            RolViewModel obj = new RolViewModel();
            obj.setIdRol((BigDecimal) ver.get(i)[0]);
            obj.setNombre((String) ver.get(i)[1]);

            lista.add(obj);
        }
        return ResponseEntity.ok(lista);
    }

    @RequestMapping(value = "/prueba", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> prueba() {
        return ResponseEntity.ok("ok");
    }

    // crear
    @RequestMapping(value = "/crearUserBorrar/{id}/{nombre}/{idrol}/activo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveUser(@PathVariable("id") final Long id, @PathVariable("nombre") final String nombre,
            @PathVariable("idrol") final Long idrol, @PathVariable("activo") final int activo) throws Exception {

        // System.out.println("Ver cretate: " + id + " -- " + model.getUsuaId());
        System.out.println("Ver cretate: " + id + " -- ");
        Usuario usercreate = new Usuario();

        usercreate.setUsuaId(id);
        System.out.println("Ver cretate: " + usercreate);

        repository.save(usercreate);
        return ResponseEntity.ok("Usuario actualizado");

    }

    @RequestMapping(value = "/crearUserP/{id}/{nombre}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveUserP(@PathVariable("id") final Long id, @PathVariable("nombre") final String nombre)
            throws Exception {

        // System.out.println("Ver cretate: " + id + " -- " + model.getUsuaId());
        System.out.println("Ver cretate: " + id + " -- ");
        Usuario usercreate = new Usuario();

        usercreate.setUsuaId(id);
        usercreate.setNombre(nombre);
        usercreate.setActivo(2);

        System.out.println("Ver cretate2: " + usercreate);

        repository.saveAndFlush(usercreate);
        return ResponseEntity.ok("Usuario actualizado");

    }

    @RequestMapping(value = "/crearUser/{id}/{nombre}/{idRol}/{activo}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveUserPp(@PathVariable("id") final Long id, @PathVariable("nombre") final String nombre,
            @PathVariable("idRol") final Long idRol, @PathVariable("activo") final char activo) throws Exception {
        // System.out.println("Ver cretate: " + id + " -- " + model.getUsuaId());
        System.out.println("Ver cretatepp: " + id + " -- " + activo);
        // repository.insertar(nombre);
        repository.insertar2(nombre, idRol, activo);
        return ResponseEntity.ok("Usuario creado correctamente");

    }

    // actualizar
    @RequestMapping(value = "/updateUser/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateUser(@RequestBody @Valid final Usuario model) throws Exception {

        if (repository.findById(model.getUsuaId()).isPresent()) {
            Usuario userupdate = repository.findById(model.getUsuaId()).get();

            /*
             * userupdate.setUsuaId(0);
             * userupdate.setUsuaPassword(bCryptPasswordEncoder.encode(model.getUsuaPassword
             * ())); repository.save(userupdate); return
             * ResponseEntity.ok(responseUtil.responseSuccessBody("Usuario actualizado"));
             */

        }
        return ResponseEntity.badRequest().body("error..");
    }

    // actualizar native
    @RequestMapping(value = "/actualizarUser/{id}/{nombre}/{idRol}/{activo}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> actualizarUser(@PathVariable("id") final Long id,
            @PathVariable("nombre") final String nombre, @PathVariable("idRol") final Long idRol,
            @PathVariable("activo") final char activo) throws Exception {
        // System.out.println("Ver cretate: " + id + " -- " + model.getUsuaId());
        System.out.println("Ver cretatepp: " + id + " -- " + activo);
        // repository.insertar(nombre);
        repository.actualizarUsuario(id, nombre, idRol, activo);
        return ResponseEntity.ok("Usuario actualizado");

    }

    // delete native
    @RequestMapping(value = "/deleteUser/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteUser(@PathVariable("id") final Long id) throws Exception {
        System.out.println("Ver cretatDel: " + id);
        repository.deleteUsuario(id);
        return ResponseEntity.ok("Usuario eliminado");
    }

    @RequestMapping(value = "/findByNombre/{nombre}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findByNombre(@PathVariable("nombre") final String nombre) {
        // repository.findById(id)
        String retorno = "";
        try {
            List<Object[]> ver = repository.buscarNombre(nombre);
            System.out.println("Verr nombre; " + ver.get(0)[0].getClass().getSimpleName());
            retorno = "1";
        } catch (Exception e) {
            retorno = "0";
        }

        return ResponseEntity.ok(retorno);
    }

    @RequestMapping(value = "/consultar-por-nombre/{nombre}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> consultarPorNombre(@PathVariable("nombre") final String nombre) {
        // repository.findById(id)
        String retorno = "";
        try {
            List<Object[]> ver = repository.buscarNombre(nombre);
            List<UsuarioViewModel> lista = new ArrayList<UsuarioViewModel>();
            for (int i = 0; i < ver.size(); i++) {
                UsuarioViewModel obj = new UsuarioViewModel();
                obj.setIdUsuario((BigDecimal) ver.get(i)[0]);
                obj.setNombre((String) ver.get(i)[1]);
                obj.setIdRol((BigDecimal) ver.get(i)[2]);
                obj.setActivo2((BigDecimal) ver.get(i)[3]);
                lista.add(obj);
            }
            return ResponseEntity.ok(lista);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("error");
        }

    }

}
