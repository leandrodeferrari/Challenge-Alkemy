package com.disney.alkemy.servicios;

import com.disney.alkemy.dto.PeliculaSerieDTO;
import com.disney.alkemy.dto.PeliculaSerieDetalleDTO;
import com.disney.alkemy.dto.PeliculaSerieEntradaDTO;
import com.disney.alkemy.dto.PeliculaSerieSalidaDTO;
import com.disney.alkemy.dto.PersonajeSalidaDTO;
import com.disney.alkemy.entidades.Genero;
import com.disney.alkemy.entidades.PeliculaSerie;
import com.disney.alkemy.entidades.Personaje;
import com.disney.alkemy.excepciones.GeneroExcepcion;
import com.disney.alkemy.excepciones.PeliculaSerieExcepcion;
import com.disney.alkemy.excepciones.PersonajeExcepcion;
import com.disney.alkemy.mapeadores.PeliculaSerieMapeador;
import com.disney.alkemy.mapeadores.PersonajeMapeador;
import com.disney.alkemy.repositorios.GeneroRepositorio;
import com.disney.alkemy.repositorios.PeliculaSerieRepositorio;
import com.disney.alkemy.repositorios.PersonajeRepositorio;
import com.disney.alkemy.validaciones.PeliculaSerieValidacion;
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
        
        if (!peliculasSeries.isEmpty()) {
            
            for (PeliculaSerie peliculaSerie : peliculasSeries) {
                
                PeliculaSerieDTO peliculaSerieDto = peliculaSerieMapeador.
                        peliculaSerieToPeliculaSerieDTO(peliculaSerie);
                
                peliculasSeriesDto.add(peliculaSerieDto);
                
            }
            
        }

        return peliculasSeriesDto;

    }

    @Transactional
    @Override
    public boolean crearPeliculaSerie(PeliculaSerieEntradaDTO peliculaSerieEntradaDto) {

        PeliculaSerieValidacion.validarPeliculaSerieEntradaDto(peliculaSerieEntradaDto);
        
        Optional<Genero> opcionalGenero = generoRepositorio.
                findByNombre(peliculaSerieEntradaDto.getNombreGenero());

        if (opcionalGenero.isPresent()) {

            Genero genero = opcionalGenero.get();

            LocalDateTime fechaDeCreacion = LocalDateTime.now();

            PeliculaSerie peliculaSerie = peliculaSerieMapeador.
                    peliculaSerieEntradaDTOToPeliculaSerie(peliculaSerieEntradaDto);

            peliculaSerie.setGenero(genero);

            peliculaSerie.setFechaDeCreacion(fechaDeCreacion);

            peliculaSerieRepositorio.save(peliculaSerie);

            return true;

        } else {

            throw new GeneroExcepcion("No existe ese género");

        }

    }

    @Transactional
    @Override
    public boolean modificarPeliculaSerie(PeliculaSerieEntradaDTO peliculaSerieEntradaDto, Integer id) {

        PeliculaSerieValidacion.validarPeliculaSerieEntradaDto(peliculaSerieEntradaDto);  
        PeliculaSerieValidacion.validarId(id);
        
        Optional<PeliculaSerie> opcionalPeliculaSerie = peliculaSerieRepositorio.findById(id);

        if (opcionalPeliculaSerie.isPresent()) {

            PeliculaSerie peliculaSerie = opcionalPeliculaSerie.get();

            PeliculaSerie peliculaSerieAuxiliar = peliculaSerieMapeador.
                    peliculaSerieEntradaDTOToPeliculaSerie(peliculaSerieEntradaDto);

            peliculaSerieAuxiliar.setId(peliculaSerie.getId());
            peliculaSerieAuxiliar.setPersonajes(peliculaSerie.getPersonajes());
            peliculaSerieAuxiliar.setFechaDeCreacion(peliculaSerie.getFechaDeCreacion());

            Optional<Genero> opcionalGenero = generoRepositorio.
                    findByNombre(peliculaSerieEntradaDto.getNombreGenero());

            if (opcionalGenero.isPresent()) {

                peliculaSerieAuxiliar.setGenero(opcionalGenero.get());

            } else {

                throw new GeneroExcepcion("No existe ese género");

            }

            peliculaSerieRepositorio.save(peliculaSerieAuxiliar);

            return true;

        } else {

            throw new PeliculaSerieExcepcion("No existe esa pelicula/serie");

        }

    }

    @Transactional
    @Override
    public boolean eliminarPeliculaSerie(Integer id) {

        PeliculaSerieValidacion.validarId(id);
        
        Optional<PeliculaSerie> opcionalPeliculaSerie = peliculaSerieRepositorio.findById(id);

        if (opcionalPeliculaSerie.isPresent()) {

            peliculaSerieRepositorio.deleteById(id);

            return true;

        } else {

            throw new PeliculaSerieExcepcion("No existe esa pelicula/serie");

        }

    }

    @Override
    public PeliculaSerieDetalleDTO detallarPeliculaSerieConSusPersonajes(Integer id) {

        PeliculaSerieValidacion.validarId(id);
        
        Optional<PeliculaSerie> opcionalPeliculaSerie = peliculaSerieRepositorio.findById(id);

        if (opcionalPeliculaSerie.isPresent()) {

            PeliculaSerieDetalleDTO peliculaSerieDetalle = peliculaSerieMapeador.
                    peliculaSerieToPeliculaSerieDetalleDTO(opcionalPeliculaSerie.get());

            List<Personaje> personajes = personajeRepositorio.
                    findByPeliculasSeries(opcionalPeliculaSerie.get());

            if (!personajes.isEmpty()) {

                List<PersonajeSalidaDTO> personajesSalidaDto = new ArrayList<>();

                for (Personaje personaje : personajes) {

                    PersonajeSalidaDTO personajeSalida = personajeMapeador.
                            personajeToPersonajeSalidaDTO(personaje);

                    personajesSalidaDto.add(personajeSalida);

                }

                peliculaSerieDetalle.setPersonajes(personajesSalidaDto);

            }

            return peliculaSerieDetalle;

        } else {

            throw new PeliculaSerieExcepcion("No existe esa pelicula/serie");

        }

    }

    @Override
    public List<PeliculaSerieSalidaDTO> buscarPeliculasSeriesPorTitulo(String titulo) {

        PeliculaSerieValidacion.validarTitulo(titulo);
        
        List<PeliculaSerieSalidaDTO> peliculasSeriesSalidaDto = new ArrayList<>();

        List<PeliculaSerie> peliculasSeries = peliculaSerieRepositorio.findByTitulo(titulo);

        if (!peliculasSeries.isEmpty()) {

            for (PeliculaSerie peliculaSerie : peliculasSeries) {

                PeliculaSerieSalidaDTO peliculaSalida = peliculaSerieMapeador.
                        peliculaSerieToPeliculaSerieSalidaDTO(peliculaSerie);

                peliculasSeriesSalidaDto.add(peliculaSalida);

            }

        }

        return peliculasSeriesSalidaDto;

    }

    @Override
    public List<PeliculaSerieSalidaDTO> buscarPeliculasSeriesPorGenero(Integer idGenero) {

        PeliculaSerieValidacion.validarId(idGenero);
        
        Optional<Genero> opcionalGenero = generoRepositorio.findById(idGenero);

        if (opcionalGenero.isPresent()) {

            List<PeliculaSerie> peliculasSeries = peliculaSerieRepositorio.
                    findByGenero(opcionalGenero.get());

            List<PeliculaSerieSalidaDTO> peliculasaSeriesSalida = new ArrayList<>();

            if (!peliculasSeries.isEmpty()) {

                for (PeliculaSerie peliculaSerie : peliculasSeries) {

                    PeliculaSerieSalidaDTO peliculaSerieSalida = peliculaSerieMapeador.
                            peliculaSerieToPeliculaSerieSalidaDTO(peliculaSerie);

                    peliculasaSeriesSalida.add(peliculaSerieSalida);

                }

            }

            return peliculasaSeriesSalida;

        } else {

            throw new GeneroExcepcion("No existe ese género");

        }

    }

    @Override
    public List<PeliculaSerieSalidaDTO> ordenarPeliculasSeriesPorFechaDeCreacionAsc() {

        List<PeliculaSerieSalidaDTO> peliculasSeriesSalida = new ArrayList<>();

        List<PeliculaSerie> peliculasSeries = peliculaSerieRepositorio.
                ordenarPeliculasSeriesPorFechaDeCreacionAsc();

        if (!peliculasSeries.isEmpty()) {

            for (PeliculaSerie peliculaSerie : peliculasSeries) {

                PeliculaSerieSalidaDTO peliculaSerieSalida = peliculaSerieMapeador.
                        peliculaSerieToPeliculaSerieSalidaDTO(peliculaSerie);

                peliculasSeriesSalida.add(peliculaSerieSalida);

            }

        }

        return peliculasSeriesSalida;

    }

    @Override
    public List<PeliculaSerieSalidaDTO> ordenarPeliculasSeriesPorFechaDeCreacionDesc() {

        List<PeliculaSerieSalidaDTO> peliculasSeriesSalida = new ArrayList<>();

        List<PeliculaSerie> peliculasSeries = peliculaSerieRepositorio.
                ordenarPeliculasSeriesPorFechaDeCreacionDesc();

        if (!peliculasSeries.isEmpty()) {

            for (PeliculaSerie peliculaSerie : peliculasSeries) {

                PeliculaSerieSalidaDTO peliculaSerieSalida = peliculaSerieMapeador.
                        peliculaSerieToPeliculaSerieSalidaDTO(peliculaSerie);

                peliculasSeriesSalida.add(peliculaSerieSalida);

            }

        }

        return peliculasSeriesSalida;

    }

    @Override
    public List<PeliculaSerie> buscarPeliculasSeriesPorPersonaje(Integer idPersonaje) {

        PeliculaSerieValidacion.validarId(idPersonaje);
        
        Optional<Personaje> opcionalPersonaje = personajeRepositorio.findById(idPersonaje);

        if (opcionalPersonaje.isPresent()) {

            List<PeliculaSerie> peliculasSeries = peliculaSerieRepositorio.
                    findByPersonajes(opcionalPersonaje.get());

            return peliculasSeries;

        } else {

            throw new PersonajeExcepcion("No existe ese personaje");

        }

    }
    
}
