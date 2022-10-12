package com.disney.alkemy.servicios;

import com.disney.alkemy.dto.PeliculaSerieDTO;
import com.disney.alkemy.dto.PeliculaSerieEntradaDTO;
import com.disney.alkemy.dto.PeliculaSerieSalidaDTO;
import com.disney.alkemy.dto.PeliculaSerieDetalleDTO;
import com.disney.alkemy.entidades.PeliculaSerie;
import java.util.List;

/**
 *
 * @author Leandro Deferrari
 */

public interface IPeliculaSerieServicio {

    public List<PeliculaSerieDTO> listarPeliculasSeriesPorTituloImagenFechaDeCreacion();
    
    public boolean crearPeliculaSerie(PeliculaSerieEntradaDTO peliculaSerieEntradaDto);
    
    public boolean modificarPeliculaSerie(PeliculaSerieEntradaDTO peliculaSerieEntradaDto, Integer id);
    
    public boolean eliminarPeliculaSerie(Integer id);
    
    public PeliculaSerieDetalleDTO detallarPeliculaSerieConSusPersonajes(Integer id);
    
    public List<PeliculaSerieSalidaDTO> buscarPeliculasSeriesPorTitulo(String titulo);
    
    public List<PeliculaSerieSalidaDTO> buscarPeliculasSeriesPorGenero(Integer idGenero);

    public List<PeliculaSerieSalidaDTO> ordenarPeliculasSeriesPorFechaDeCreacionAsc();
    
    public List<PeliculaSerieSalidaDTO> ordenarPeliculasSeriesPorFechaDeCreacionDesc();
    
    public List<PeliculaSerie> buscarPeliculasSeriesPorPersonaje(Integer idPersonaje);
    
}
