package com.disney.alkemy.servicios;

import com.disney.alkemy.dto.PersonajeDTO;
import com.disney.alkemy.dto.PersonajeDetalleDTO;
import java.util.List;
import com.disney.alkemy.dto.PersonajeEntradaDTO;
import com.disney.alkemy.dto.PersonajeSalidaDTO;

/**
 *
 * @author Leandro Deferrari
 */

public interface IPersonajeServicio {

    public List<PersonajeDTO> listarPersonajesPorNombreImagen();
    
    public boolean crearPersonaje(PersonajeEntradaDTO personajeEntradaDto);
    
    public boolean modificarPersonaje(PersonajeEntradaDTO personajeEntradaDto, Integer id);
    
    public boolean eliminarPersonaje(Integer id);
    
    public List<PersonajeSalidaDTO> buscarPersonajesPorNombre(String nombre);
    
    public List<PersonajeSalidaDTO> buscarPersonajesPorEdad(byte edad);
    
    public List<PersonajeSalidaDTO> buscarPersonajesPorPeso(float peso);
    
    public List<PersonajeSalidaDTO> buscarPersonajesPorPeliculaSerie(Integer idPeliculaSerie);
    
    public PersonajeDetalleDTO detallarPersonajeConSusPeliculasSeries(Integer id);
    
}
