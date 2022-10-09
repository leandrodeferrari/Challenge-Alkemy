package com.disney.alkemy.dto;

import java.time.LocalDateTime;
import javax.persistence.Basic;
import javax.persistence.FetchType;
import javax.persistence.Lob;
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
public class PeliculaSerieDTO {

    private String titulo;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] imagen;
    private LocalDateTime fechaDeCreacion;

}
