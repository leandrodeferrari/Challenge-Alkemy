package com.disney.alkemy.dto;

import lombok.Data;

/**
 *
 * @author Leandro Deferrari
 */

@Data
public class PersonajeSalidaDTO {

    private Integer id;
    private byte edad;
    private float peso;
    private String imagen;
    private String nombre;
    private String historia;
    
}
