package com.disney.alkemy.servicios;

import com.disney.alkemy.entidades.Personaje;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Leandro Deferrari
 */

@Service
public class PersonajeServicioImpl implements IPersonajeServicio {

    @Override
    public List<Personaje> listarPersonajesPorNombreImagen() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean crearPersonaje(Personaje personaje) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean modificarPersonaje(Personaje personaje) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean eliminarPersonaje(Integer id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Personaje> detallarPersonajeConSusPeliculasSeries(Integer id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Personaje> buscarPersonajesPorNombre(String nombre) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Personaje> buscarPersonajesPorEdad(byte edad) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Personaje> buscarPersonajesPorPeso(float peso) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Personaje> buscarPersonajesPorPeliculaSerie(Integer idPeliculaSerie) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
