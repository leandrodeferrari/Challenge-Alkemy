package com.disney.alkemy.servicios;

import com.disney.alkemy.dto.PeliculaSerieDTO;
import com.disney.alkemy.dto.PeliculaSerieDetalleDTO;
import com.disney.alkemy.dto.PeliculaSerieEntradaDTO;
import com.disney.alkemy.dto.PeliculaSerieSalidaDTO;
import com.disney.alkemy.dto.PersonajeSalidaDTO;
import com.disney.alkemy.entidades.Genero;
import com.disney.alkemy.entidades.PeliculaSerie;
import com.disney.alkemy.entidades.Personaje;
import com.disney.alkemy.mapeadores.PeliculaSerieMapeador;
import com.disney.alkemy.mapeadores.PersonajeMapeador;
import com.disney.alkemy.repositorios.GeneroRepositorio;
import com.disney.alkemy.repositorios.PeliculaSerieRepositorio;
import com.disney.alkemy.repositorios.PersonajeRepositorio;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Leandro Deferrari
 */

@Service
public class PeliculaSerieServicioImpl implements IPeliculaSerieServicio {

    @Autowired
    private PersonajeRepositorio personajeRepositorio;
    
    @Autowired
    private GeneroRepositorio generoRepositorio;
    
    @Autowired
    private PeliculaSerieRepositorio peliculaSerieRepositorio;
    
    @Autowired
    private PeliculaSerieMapeador peliculaSerieMapeador;
    
    @Autowired
    private PersonajeMapeador personajeMapeador;
    
    @Override
    public List<PeliculaSerieDTO> listarPeliculasSeriesPorTituloImagenFechaDeCreacion() {
        
        List<PeliculaSerieDTO> peliculasSeriesDto = new ArrayList<>();
        
        List<PeliculaSerie> peliculasSeries = peliculaSerieRepositorio.consultarPeliculasSeries();
        
        if(!peliculasSeries.isEmpty()){
            
            for (PeliculaSerie peliculaSerie : peliculasSeries) {
                
                PeliculaSerieDTO peliculaSerieDto = peliculaSerieMapeador.peliculaSerieToPeliculaSerieDTO(peliculaSerie);
                
                peliculasSeriesDto.add(peliculaSerieDto);
                
            }
            
        }
        
        return peliculasSeriesDto;
        
    }

    @Transactional
    @Override
    public boolean crearPeliculaSerie(PeliculaSerieEntradaDTO peliculaSerieEntradaDto) {
        
        Optional<Genero> respuesta = generoRepositorio.findByNombre(peliculaSerieEntradaDto.getNombreGenero());
        
        if(respuesta.isPresent()){
            
            Genero genero = respuesta.get();
            
            LocalDateTime fechaDeCreacion = LocalDateTime.now();
            
            PeliculaSerie peliculaSerie = peliculaSerieMapeador.peliculaSerieEntradaDTOToPeliculaSerie(peliculaSerieEntradaDto);
            
            peliculaSerie.setGenero(genero);
            
            peliculaSerie.setFechaDeCreacion(fechaDeCreacion);
            
            peliculaSerieRepositorio.save(peliculaSerie);
            
            return true;
            
        } else {
            
            return false;
            
        }
        
    }

    @Transactional
    @Override
    public boolean modificarPeliculaSerie(PeliculaSerieEntradaDTO peliculaSerieEntradaDto, Integer id) {
        
        Optional<PeliculaSerie> respuesta = peliculaSerieRepositorio.findById(id);

        if (respuesta.isPresent()) {

            PeliculaSerie peliculaSerie = respuesta.get();

            PeliculaSerie peliculaSerieAuxiliar = peliculaSerieMapeador.peliculaSerieEntradaDTOToPeliculaSerie(peliculaSerieEntradaDto);

            peliculaSerieAuxiliar.setId(peliculaSerie.getId());
            peliculaSerieAuxiliar.setPersonajes(peliculaSerie.getPersonajes());
            peliculaSerieAuxiliar.setFechaDeCreacion(peliculaSerie.getFechaDeCreacion());
            
            Optional<Genero> respuestaGenero = generoRepositorio.findByNombre(peliculaSerieEntradaDto.getNombreGenero());
            
            if(respuestaGenero.isPresent()){
                
                peliculaSerieAuxiliar.setGenero(respuestaGenero.get());
                
            } else {
                
                return false;
                
            }
            
            peliculaSerieRepositorio.save(peliculaSerieAuxiliar);

            return true;

        } else {

            return false;

        }
        
        
    }

    @Transactional
    @Override
    public boolean eliminarPeliculaSerie(Integer id) {
        
        Optional<PeliculaSerie> respuesta = peliculaSerieRepositorio.findById(id);

        if (respuesta.isPresent()) {

            peliculaSerieRepositorio.deleteById(id);

            return true;

        } else {

            return false;

        }
        
    }

    @Override
    public PeliculaSerieDetalleDTO detallarPeliculaSerieConSusPersonajes(Integer id) {
        
        Optional<PeliculaSerie> respuesta = peliculaSerieRepositorio.findById(id);
        
        if(respuesta.isPresent()){
            
            PeliculaSerieDetalleDTO peliculaSerieDetalle = peliculaSerieMapeador.peliculaSerieToPeliculaSerieDetalleDTO(respuesta.get());
            
            List<Personaje> personajes = personajeRepositorio.findByPeliculasSeries(respuesta.get());
            
            if(!personajes.isEmpty()){
                
                List<PersonajeSalidaDTO> personajesSalidaDto = new ArrayList<>();
                
                for (Personaje personaje : personajes) {
                    
                    PersonajeSalidaDTO personajeSalida = personajeMapeador.personajeToPersonajeSalidaDTO(personaje);
                    
                    personajesSalidaDto.add(personajeSalida);
                    
                }
                
                peliculaSerieDetalle.setPersonajes(personajesSalidaDto);
                
            }
            
            return peliculaSerieDetalle;
            
        } else {
            
            return new PeliculaSerieDetalleDTO();
            
        }
        
    }

    @Override
    public List<PeliculaSerieSalidaDTO> buscarPeliculasSeriesPorTitulo(String titulo) {
        
        List<PeliculaSerieSalidaDTO> peliculasSeriesSalidaDto = new ArrayList<>();
        
        List<PeliculaSerie> peliculasSeries = peliculaSerieRepositorio.findByTitulo(titulo);
        
        if(!peliculasSeries.isEmpty()){
            
            for (PeliculaSerie peliculaSerie : peliculasSeries) {
                
                PeliculaSerieSalidaDTO peliculaSerieDto = peliculaSerieMapeador.peliculaSerieToPeliculaSerieSalidaDTO(peliculaSerie);
                
                peliculasSeriesSalidaDto.add(peliculaSerieDto);
                
            }
            
        }
        
        return peliculasSeriesSalidaDto;
        
    }

    @Override
    public List<PeliculaSerieSalidaDTO> buscarPeliculasSeriesPorGenero(Integer idGenero) {
        
        Optional<Genero> respuesta = generoRepositorio.findById(idGenero);
        
        if(respuesta.isPresent()){
            
            List<PeliculaSerie> peliculasSeries = peliculaSerieRepositorio.findByGenero(respuesta.get());
            
            List<PeliculaSerieSalidaDTO> peliculasaSeriesSalida = new ArrayList<>();
            
            for (PeliculaSerie peliculaSerie : peliculasSeries) {
                
                PeliculaSerieSalidaDTO peliculaSerieSalida = peliculaSerieMapeador.peliculaSerieToPeliculaSerieSalidaDTO(peliculaSerie);
                
                peliculasaSeriesSalida.add(peliculaSerieSalida);
                
            }
            
            return peliculasaSeriesSalida;
            
        } else {
            
            return new ArrayList<>();
            
        }
        
    }

    @Override
    public List<PeliculaSerieSalidaDTO> ordenarPeliculasSeriesPorFechaDeCreacionAsc() {
        
        List<PeliculaSerieSalidaDTO> peliculasSeriesSalida = new ArrayList<>();
        
        List<PeliculaSerie> peliculasSeries = peliculaSerieRepositorio.ordenarPeliculasSeriesPorFechaDeCreacionAsc();
        
        if(!peliculasSeries.isEmpty()){
            
            for (PeliculaSerie peliculaSerie : peliculasSeries) {
                
                PeliculaSerieSalidaDTO peliculaSerieSalida = peliculaSerieMapeador.peliculaSerieToPeliculaSerieSalidaDTO(peliculaSerie);
                
                peliculasSeriesSalida.add(peliculaSerieSalida);
                
            }
            
        }
        
        return peliculasSeriesSalida;
        
    }

    @Override
    public List<PeliculaSerieSalidaDTO> ordenarPeliculasSeriesPorFechaDeCreacionDesc() {
        
        List<PeliculaSerieSalidaDTO> peliculasSeriesSalida = new ArrayList<>();
        
        List<PeliculaSerie> peliculasSeries = peliculaSerieRepositorio.ordenarPeliculasSeriesPorFechaDeCreacionDesc();
        
        if(!peliculasSeries.isEmpty()){
            
            for (PeliculaSerie peliculaSerie : peliculasSeries) {
                
                PeliculaSerieSalidaDTO peliculaSerieSalida = peliculaSerieMapeador.peliculaSerieToPeliculaSerieSalidaDTO(peliculaSerie);
                
                peliculasSeriesSalida.add(peliculaSerieSalida);
                
            }
            
        }
        
        return peliculasSeriesSalida;
        
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
