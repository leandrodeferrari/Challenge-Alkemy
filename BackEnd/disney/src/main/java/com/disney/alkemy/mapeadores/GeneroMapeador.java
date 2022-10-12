package com.disney.alkemy.mapeadores;

import com.disney.alkemy.dto.GeneroDTO;
import com.disney.alkemy.entidades.Genero;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 *
 * @author Leandro Deferrari
 */

@Mapper(componentModel = "spring")
public interface GeneroMapeador {

    @Mappings({
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "imagen", ignore = true)
    })
    public Genero generoDTOToGenero(GeneroDTO generoDto);

    public GeneroDTO generoToGeneroDTO(Genero genero);
    
}