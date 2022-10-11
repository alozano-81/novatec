package com.prueba.test.ViewModels;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class UsuarioViewModel {

    BigDecimal idUsuario;
    String nombre;
    BigDecimal idRol;
    BigDecimal activo;
    BigDecimal activo2;
    String rolNombre;

}
