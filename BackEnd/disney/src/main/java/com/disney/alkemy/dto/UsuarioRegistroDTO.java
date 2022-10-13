package com.disney.alkemy.dto;

import lombok.Data;

/**
 *
 * @author Leandro Deferrari
 */

@Data
public class UsuarioRegistroDTO {

    private String nombre;
    private String email;
    private String contrasenia;
    private String rol;
    
}
