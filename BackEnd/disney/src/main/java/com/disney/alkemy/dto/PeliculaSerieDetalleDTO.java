package com.disney.alkemy.dto;

import java.util.List;
import lombok.Data;

/**
 *
 * @author Leandro Deferrari
 */

@Data
public class PeliculaSerieDetalleDTO {

    private Integer id;
    private String titulo;
    private String imagen;
    private String fechaDeCreacion;
    private byte calificacion;
    private String nombreGenero;
    private List<PersonajeSalidaDTO> personajes;
    
}
