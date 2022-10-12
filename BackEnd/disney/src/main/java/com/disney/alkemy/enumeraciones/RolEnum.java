
package com.disney.alkemy.enumeraciones;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 * @author Leandro Deferrari
 */

@Getter
@AllArgsConstructor
public enum RolEnum {

    ADMIN("Administrador"), USUARIO("Usuario");
    
    private final String nombre;

}
