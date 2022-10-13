package com.disney.alkemy.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Leandro Deferrari
 */

@Getter
@Setter
@AllArgsConstructor
public class JwtDTO {

    private String token;
    private final String tipo = "Bearer";
    
}
