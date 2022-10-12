package com.disney.alkemy.mapeadores;

import com.disney.alkemy.dto.PeliculaSerieDTO;
import com.disney.alkemy.dto.PeliculaSerieDetalleDTO;
import com.disney.alkemy.dto.PeliculaSerieEntradaDTO;
import com.disney.alkemy.dto.PeliculaSeriePersonajeDTO;
import com.disney.alkemy.dto.PeliculaSerieSalidaDTO;
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
    
    @Mappings({
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "calificacion", ignore = true),
        @Mapping(target = "genero", ignore = true),
        @Mapping(target = "personajes", ignore = true),
    })
    public PeliculaSerie peliculaSerieDTOToPeliculaSerie(PeliculaSerieDTO peliculaSerieDto);
    
    @Mappings({
        @Mapping(target="fechaDeCreacion", dateFormat = "dd.MM.yyyy")
    })
    public PeliculaSerieDTO peliculaSerieToPeliculaSerieDTO(PeliculaSerie peliculaSerie);
    
    @Mappings({
        @Mapping(target = "genero", ignore = true),
        @Mapping(target = "personajes", ignore = true)
    })
    public PeliculaSerie peliculaSerieSalidaDTOToPeliculaSerie(PeliculaSerieSalidaDTO peliculaSerieSalidaDto);
    
    @Mappings({
        @Mapping(source = "peliculaSerie.genero.nombre", target = "nombreGenero"),
        @Mapping(target="fechaDeCreacion", dateFormat = "dd.MM.yyyy")
    })
    public PeliculaSerieSalidaDTO peliculaSerieToPeliculaSerieSalidaDTO(PeliculaSerie peliculaSerie);
    
    @Mappings({
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "fechaDeCreacion", ignore = true),
        @Mapping(target = "genero", ignore = true),
        @Mapping(target = "personajes", ignore = true),
    })
    public PeliculaSerie peliculaSerieEntradaDTOToPeliculaSerie(PeliculaSerieEntradaDTO peliculaSerieEntradaDto);
    
    @Mappings({
        @Mapping(source = "peliculaSerie.genero.nombre", target = "nombreGenero")
    })
    public PeliculaSerieEntradaDTO peliculaSerieToPeliculaSerieEntradaDTO(PeliculaSerie peliculaSerie);
    
    @Mappings({
        @Mapping(target = "fechaDeCreacion", ignore = true),
        @Mapping(target = "genero", ignore = true),
        @Mapping(target = "personajes", ignore = true)
    })
    public PeliculaSerie peliculaSerieDetalleDTOToPeliculaSerie(PeliculaSerieDetalleDTO peliculaSerieDetalleDto);
    
    @Mappings({
        @Mapping(target="fechaDeCreacion", dateFormat = "dd.MM.yyyy"),
        @Mapping(source = "peliculaSerie.genero.nombre", target = "nombreGenero"),
        @Mapping(target = "personajes", ignore = true)
    })
    public PeliculaSerieDetalleDTO peliculaSerieToPeliculaSerieDetalleDTO(PeliculaSerie peliculaSerie);
    
}
