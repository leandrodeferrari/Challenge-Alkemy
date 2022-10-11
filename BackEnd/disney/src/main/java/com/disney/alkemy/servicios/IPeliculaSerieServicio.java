package com.disney.alkemy.servicios;

import com.disney.alkemy.entidades.PeliculaSerie;
import java.util.List;

/**
 *
 * @author Leandro Deferrari
 */

public interface IPeliculaSerieServicio {

    public List<PeliculaSerie> listarPeliculasSeriesPorTituloImagenFechaDeCreacion();
    
    public boolean crearPeliculaSerie(PeliculaSerie peliculaSerie);
    
    public boolean modificarPeliculaSerie(PeliculaSerie peliculaSerie);
    
    public boolean eliminarPeliculaSerie(Integer id);
    
    public List<PeliculaSerie> detallarPeliculaSerieConSusPersonajes(Integer id);
    
    public List<PeliculaSerie> buscarPeliculasSeriesPorNombre(String nombre);
    
    public List<PeliculaSerie> buscarPeliculasSeriesPorGenero(Integer idGenero);

    public List<PeliculaSerie> ordenarPeliculasSeriesPorFechaDeCreacionAsc();
    
    public List<PeliculaSerie> ordenarPeliculasSeriesPorFechaDeCreacionDesc();
    
    public List<PeliculaSerie> buscarPeliculasSeriesPorPersonaje(Integer idPersonaje);
    
}
