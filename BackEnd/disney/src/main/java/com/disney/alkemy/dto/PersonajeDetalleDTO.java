package com.disney.alkemy.dto;

import java.util.List;
import lombok.Data;

/**
 *
 * @author Leandro Deferrari
 */

@Data
public class PersonajeDetalleDTO {

    private Integer id;
    private byte edad;
    private float peso;
    private String imagen;
    private String nombre;
    private String historia;
    private List<PeliculaSeriePersonajeDTO> peliculasSeries;
    
}
