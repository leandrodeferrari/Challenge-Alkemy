package com.disney.alkemy.validaciones;

import com.disney.alkemy.dto.UsuarioIngresoDTO;
import com.disney.alkemy.dto.UsuarioRegistroDTO;
import com.disney.alkemy.excepciones.UsuarioExcepcion;

/**
 *
 * @author Leandro Deferrari
 */

public class UsuarioValidacion {

    public static void validarUsuarioIngresoDTO(UsuarioIngresoDTO usuarioIngresoDto) {

        validarEmail(usuarioIngresoDto.getEmail());
        validarContrasenia(usuarioIngresoDto.getContrasenia());

    }

    public static void validarUsuarioRegistroDTO(UsuarioRegistroDTO usuarioRegistroDto) {

        validarNombre(usuarioRegistroDto.getNombre());
        validarEmail(usuarioRegistroDto.getEmail());
        validarContrasenia(usuarioRegistroDto.getContrasenia());
        validarRol(usuarioRegistroDto.getRol());

    }

    public static void validarNombre(String nombre) {

        if (nombre == null || nombre.isEmpty() || nombre.length() > 30) {

            throw new UsuarioExcepcion("Nombre inválido, demasiado largo o vacío");

        }

    }

    public static void validarEmail(String email) {

        if (email == null || email.isEmpty() || email.length() > 50) {

            throw new UsuarioExcepcion("Email inválido, demasiado largo o vacío");

        }

    }

    public static void validarContrasenia(String contrasenia) {

        if (contrasenia == null || contrasenia.isEmpty() || contrasenia.length() > 255) {

            throw new UsuarioExcepcion("Contraseña inválida, demasiada larga o vacía");

        }

    }

    public static void validarRol(String rol) {

        if (rol == null || rol.isEmpty() || rol.length() > 20) {

            throw new UsuarioExcepcion("Rol inválido, demasiado largo o vacío");

        }

    }

}
