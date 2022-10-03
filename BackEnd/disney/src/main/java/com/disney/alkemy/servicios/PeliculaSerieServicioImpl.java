package com.disney.alkemy.servicios;

import com.disney.alkemy.entidades.PeliculaSerie;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Leandro Deferrari
 */

@Service
public class PeliculaSerieServicioImpl implements IPeliculaSerieServicio {

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
    
}
