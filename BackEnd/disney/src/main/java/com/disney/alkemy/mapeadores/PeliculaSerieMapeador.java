package com.disney.alkemy.mapeadores;

import com.disney.alkemy.dto.PeliculaSeriePersonajeDTO;
import com.disney.alkemy.entidades.PeliculaSerie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 *
 * @author Leandro Deferrari
 */

@Mapper(componentModel = "spring")
public interface PeliculaSerieMapeador {

    @Mappings({
        @Mapping(target = "genero", ignore = true),
        @Mapping(target = "personajes", ignore = true)
    })
    public PeliculaSerie peliculaSeriePersonajeDTOToPeliculaSerie(PeliculaSeriePersonajeDTO PeliculaSeriePersonajeDTO);

    @Mappings({
        @Mapping(source = "peliculaSerie.genero.nombre", target = "nombreGenero"),
        @Mapping(target="fechaDeCreacion", dateFormat = "dd.MM.yyyy")
    })
    public PeliculaSeriePersonajeDTO peliculaSerieToPeliculaSeriePersonajeDTO(PeliculaSerie peliculaSerie);
    
}
