package com.disney.alkemy.mapeadores;

import com.disney.alkemy.entidades.Personaje;
import com.disney.alkemy.dto.PersonajeDTO;
import com.disney.alkemy.dto.PersonajeDetalleDTO;
import org.mapstruct.Mapper;
import com.disney.alkemy.dto.PersonajeEntradaDTO;
import com.disney.alkemy.dto.PersonajeSalidaDTO;
import org.mapstruct.Mappings;
import org.mapstruct.Mapping;

/**
 *
 * @author Leandro Deferrari
 */

@Mapper(componentModel = "spring")
public interface PersonajeMapeador {

    public PersonajeDTO personaToPersonajeDTO(Personaje personaje);
    
    @Mappings({
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "peliculasSeries", ignore = true)
    })
    public Personaje personajeEntradaDTOToPersonaje(PersonajeEntradaDTO personajeEntradaDto);
    
    public PersonajeEntradaDTO personajeToPersonajeEntradaDTO(Personaje personaje);

    @Mappings({
        @Mapping(target = "peliculasSeries", ignore = true)
    })
    public Personaje personajeSalidaDTOToPersonaje(PersonajeSalidaDTO personajeSalidaDto);
    
    public PersonajeSalidaDTO personajeToPersonajeSalidaDTO(Personaje personaje);
    
    @Mappings({
        @Mapping(target = "peliculasSeries", ignore = true)
    })
    public Personaje personajeDetalleDTOToPersonaje(PersonajeDetalleDTO personajeDetalleDto);
    
    @Mappings({
        @Mapping(target = "peliculasSeries", ignore = true)
    })
    public PersonajeDetalleDTO personajeToPersonajeDetalleDTO(Personaje personaje);
    
}
