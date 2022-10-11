package com.disney.alkemy.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *
 * @author Leandro Deferrari
 */

@Data
public class PersonajeSalidaDTO {

    @ApiModelProperty(position = 0)
    private Integer id;
    @ApiModelProperty(position = 1)
    private byte edad;
    @ApiModelProperty(position = 2)
    private float peso;
    @ApiModelProperty(position = 3)
    private String imagen;
    @ApiModelProperty(position = 4)
    private String nombre;
    @ApiModelProperty(position = 5)
    private String historia;
    
}
