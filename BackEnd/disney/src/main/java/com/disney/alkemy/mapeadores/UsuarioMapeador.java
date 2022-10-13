package com.disney.alkemy.mapeadores;

import com.disney.alkemy.dto.UsuarioIngresoDTO;
import com.disney.alkemy.dto.UsuarioRegistroDTO;
import com.disney.alkemy.entidades.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 *
 * @author Leandro Deferrari
 */

@Mapper(componentModel = "spring")
public interface UsuarioMapeador {

    @Mappings({
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "nombre", ignore = true),
        @Mapping(target = "fechaDeAlta", ignore = true),
        @Mapping(target = "rol", ignore = true)
    })
    public Usuario usuarioIngresoDTOToUsuario(UsuarioIngresoDTO usuarioIngresoDto);
    
    public UsuarioIngresoDTO usuarioToUsuarioIngresoDTO(Usuario usuario);

    @Mappings({
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "fechaDeAlta", ignore = true)
    })
    public Usuario usuarioRegistroDTOToUsuario(UsuarioRegistroDTO usuarioRegistroDto);
    
    public UsuarioRegistroDTO usuarioToUsuarioRegistroDTO(Usuario usuario);
    
}
