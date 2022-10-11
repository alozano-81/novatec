package com.prueba.test.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;

import com.prueba.test.model.Usuario;

@Repository
public interface UserRepository extends JpaRepository<Usuario, Long> {

        @Query(value = " select u.id_usuario,u.nombre,u.id_rol, u.activo,r.nombre as rol  from system.usuario u inner join "
                        + " rol r  on r.id_rol = u.id_rol "
                        + " where activo is not null order by id_usuario asc ", nativeQuery = true)
        List<Object[]> buscar();

        // inserrt
        @Transactional
        @Modifying
        @Query(value = " insert into system.usuario " + " (ID_USUARIO, nombre, ID_ROL, ACTIVO ) "
                        + " values(user_seq.nextval,:nombre,:idRol, :activo)", nativeQuery = true)
        public void insertar2(@Param("nombre") String nombre, @Param("idRol") Long idRol, @Param("activo") char activo);

        // actualizar
        @Transactional
        @Modifying
        @Query(value = " update system.usuario  "
                        + " set nombre =:nombre, ID_ROL =:idRol, activo =:activo where  ID_USUARIO =:id ", nativeQuery = true)
        public void actualizarUsuario(@Param("id") Long id, @Param("nombre") String nombre, @Param("idRol") Long idRol,
                        @Param("activo") char activo);

        // eliminar
        @Transactional
        @Modifying
        @Query(value = " delete from  system.usuario  where  ID_USUARIO =:id ", nativeQuery = true)
        public void deleteUsuario(@Param("id") Long id);

        // Validar por nombre de usuario
        @Query(value = " select nombre from system.usuario where nombre =:nombre and activo is not null ", nativeQuery = true)
        List<Object[]> validarNombre(@Param("nombre") String nombre);

        // consultar por nombre de usuario
        @Query(value = " select id_usuario,nombre,id_rol, activo from system.usuario where nombre like %:nombreTxt% and activo is not null ", nativeQuery = true)
        List<Object[]> buscarNombre(@Param("nombreTxt") String nombreTxt);

        // buscar roles
        @Query(value = " select id_rol,nombre from system.rol " + " order by nombre asc ", nativeQuery = true)
        List<Object[]> buscarRoles();

}
