--cmabiosclean
--v

--Usuario 

create sequence user_seq
  start with 1
  increment by 1
  maxvalue 99999
  minvalue 1;
  
create table usuario(
 id_usuario number not null,
 nombre varchar2(100),
 primary key (id_usuario)
);

select * from system.usuario;

insert INTO USUARIO (ID_USUARIO, NOMBRE, ID_ROL, activo) 
VALUES (user_seq.nextval, 'nombre933', 1,'2');

delete from usuario
where ID_USUARIO = 2;

update  usuario
set nombre ='1', ID_ROL =1, activo =1 
where  ID_USUARIO = 21;


select id_usuario,nombre,id_rol,activo from system.usuario 
where activo is not null
order by id_usuario asc;

select id_usuario,nombre,id_rol, activo from system.usuario 
where nombre like '%nombre933%' and activo is not null;


--Rol


create table rol(
 id_rol number not null,
 nombre varchar2(100),
 primary key (id_rol)
);

select * from system.rol;

create sequence rol_seq
  start with 1
  increment by 1
  maxvalue 99999
  minvalue 1;
  
  
INSERT INTO SYSTEM.rol (ID_ROL, NOMBRE) 
VALUES (rol_seq.nextval, 'ADMINISTRADOR');

INSERT INTO SYSTEM.rol (ID_ROL, NOMBRE) 
VALUES (rol_seq.nextval, 'AUDITOR');

INSERT INTO SYSTEM.rol (ID_ROL, NOMBRE) 
VALUES (rol_seq.nextval, 'AUXILIAR');


commit
rollback

--conexión base de datos:
--localhost:8083

--conexión  angular:
--localhost:4200

archivo properties, poner en update para generar estructura de base de datos.
ruta: \pruebaapi\src\main\resources
spring.jpa.hibernate.ddl-auto=none
