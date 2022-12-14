package com.disney.alkemy.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *
 * @author Leandro Deferrari
 */

@Data
public class PersonajeDTO {

    @ApiModelProperty(position = 0)
    private String imagen;
    @ApiModelProperty(position = 1)
    private String nombre;

}
