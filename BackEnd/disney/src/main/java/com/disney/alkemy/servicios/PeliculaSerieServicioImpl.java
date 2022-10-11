package com.disney.alkemy.servicios;

import com.disney.alkemy.entidades.PeliculaSerie;
import com.disney.alkemy.entidades.Personaje;
import com.disney.alkemy.repositorios.PeliculaSerieRepositorio;
import com.disney.alkemy.repositorios.PersonajeRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Leandro Deferrari
 */

@Service
public class PeliculaSerieServicioImpl implements IPeliculaSerieServicio {

    @Autowired
    private PersonajeRepositorio personajeRepositorio;
    
    @Autowired
    private PeliculaSerieRepositorio peliculaSerieRepositorio;
    
    @Override
    public List<PeliculaSerie> listarPeliculasSeriesPorTituloImagenFechaDeCreacion() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean crearPeliculaSerie(PeliculaSerie peliculaSerie) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean modificarPeliculaSerie(PeliculaSerie peliculaSerie) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean eliminarPeliculaSerie(Integer id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<PeliculaSerie> detallarPeliculaSerieConSusPersonajes(Integer id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<PeliculaSerie> buscarPeliculasSeriesPorNombre(String nombre) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<PeliculaSerie> buscarPeliculasSeriesPorGenero(Integer idGenero) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<PeliculaSerie> ordenarPeliculasSeriesPorFechaDeCreacionAsc() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<PeliculaSerie> ordenarPeliculasSeriesPorFechaDeCreacionDesc() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
    @Override
     public List<PeliculaSerie> buscarPeliculasSeriesPorPersonaje(Integer idPersonaje){
         
        Optional<Personaje> respuestaPersonaje = personajeRepositorio.findById(idPersonaje);
        
        if(respuestaPersonaje.isPresent()){
            
            List<PeliculaSerie> peliculasSeries = peliculaSerieRepositorio.findByPersonajes(respuestaPersonaje.get());
            
            return peliculasSeries;
            
        } else {
            
            return new ArrayList<>();
            
        }
         
     }
    
}
