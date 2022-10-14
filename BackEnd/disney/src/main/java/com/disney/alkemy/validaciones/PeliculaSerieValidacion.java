package com.disney.alkemy.validaciones;

import com.disney.alkemy.dto.PeliculaSerieEntradaDTO;
import com.disney.alkemy.excepciones.PeliculaSerieExcepcion;

/**
 *
 * @author Leandro Deferrari
 */
public class PeliculaSerieValidacion {

    public static void validarId(Integer id) {

        if (id == null || id <= 0) {

            throw new PeliculaSerieExcepcion("Id inválido o incorrecto");

        }

    }

    public static void validarPeliculaSerieEntradaDto(PeliculaSerieEntradaDTO peliculaSerieEntrada) {

        validarTitulo(peliculaSerieEntrada.getTitulo());
        validarImagen(peliculaSerieEntrada.getImagen());
        validarCalificacion(peliculaSerieEntrada.getCalificacion());

    }

    public static void validarTitulo(String titulo) {

        if (titulo == null || titulo.isEmpty() || titulo.length() > 30) {

            throw new PeliculaSerieExcepcion("Título inválido, demasiado largo o vacío");

        }

    }

    public static void validarImagen(String imagen) {

        if (imagen == null || imagen.isEmpty() || imagen.length() > 30) {

            throw new PeliculaSerieExcepcion("Imagen inválida, demasiada larga o vacía");

        }

    }

    public static void validarCalificacion(byte calificacion) {

        if (calificacion < 0 || calificacion > 5) {

            throw new PeliculaSerieExcepcion("Calificación fuera de rango");

        }

    }

}
