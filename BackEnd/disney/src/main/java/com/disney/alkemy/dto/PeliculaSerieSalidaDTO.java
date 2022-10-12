package com.disney.alkemy.dto;

import lombok.Data;

/**
 *
 * @author Leandro Deferrari
 */

@Data
public class PeliculaSerieSalidaDTO {

    private Integer id;
    private String titulo;
    private String imagen;
    private String fechaDeCreacion;
    private byte calificacion;
    private String nombreGenero;
    
}
