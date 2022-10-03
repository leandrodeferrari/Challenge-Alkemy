package com.disney.alkemy.servicios;

import com.disney.alkemy.entidades.Personaje;
import java.util.List;

/**
 *
 * @author Leandro Deferrari
 */

public interface IPersonajeServicio {

    public List<Personaje> listarPersonajesPorNombreImagen();
    
    public boolean crearPersonaje(Personaje personaje);
    
    public boolean modificarPersonaje(Personaje personaje);
    
    public boolean eliminarPersonaje(Integer id);

    public List<Personaje> detallarPersonajeConSusPeliculasSeries(Integer id);
    
    public List<Personaje> buscarPersonajesPorNombre(String nombre);
    
    public List<Personaje> buscarPersonajesPorEdad(byte edad);
    
    public List<Personaje> buscarPersonajesPorPeso(float peso);
    
    public List<Personaje> buscarPersonajesPorPeliculaSerie(Integer idPeliculaSerie);
    
}
