package com.disney.alkemy.validaciones;

import com.disney.alkemy.dto.PersonajeEntradaDTO;
import com.disney.alkemy.excepciones.PersonajeExcepcion;

/**
 *
 * @author Leandro Deferrari
 */

public class PersonajeValidacion {

    public static void validarId(Integer id) {

        if (id == null || id <= 0) {

            throw new PersonajeExcepcion("Id inválido o incorrecto");

        }

    }

    public static void validarPersonajeEntradaDTO(PersonajeEntradaDTO personajeEntradaDto) {

        validarEdad(personajeEntradaDto.getEdad());
        validarImagen(personajeEntradaDto.getImagen());
        validarNombre(personajeEntradaDto.getNombre());
        validarHistoria(personajeEntradaDto.getHistoria());
        validarPeso(personajeEntradaDto.getPeso());

    }

    public static void validarEdad(byte edad) {

        if (edad < 0 || edad > 150) {

            throw new PersonajeExcepcion("Edad fuera de rango");

        }

    }

    public static void validarImagen(String imagen) {

        if (imagen == null || imagen.isEmpty() || imagen.length() > 30) {

            throw new PersonajeExcepcion("Imagen inválida, demasiada larga o vacía");

        }

    }

    public static void validarNombre(String nombre) {

        if (nombre == null || nombre.isEmpty() || nombre.length() > 30) {

            throw new PersonajeExcepcion("Nombre inválido, demasiado largo o vacío");

        }

    }

    public static void validarPeso(float peso) {

        if (peso < 0 || peso > 600) {

            throw new PersonajeExcepcion("Edad fuera de rango");

        }

    }

    public static void validarHistoria(String historia) {

        if (historia == null || historia.isEmpty() || historia.length() > 255) {

            throw new PersonajeExcepcion("Historia inválida, demasiado larga o vacía");

        }

    }

}
