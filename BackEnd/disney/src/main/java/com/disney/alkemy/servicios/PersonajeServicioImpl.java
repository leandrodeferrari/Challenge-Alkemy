package com.disney.alkemy.servicios;

import com.disney.alkemy.dto.PeliculaSeriePersonajeDTO;
import com.disney.alkemy.dto.PersonajeDTO;
import com.disney.alkemy.dto.PersonajeDetalleDTO;
import com.disney.alkemy.entidades.Personaje;
import com.disney.alkemy.mapeadores.PersonajeMapeador;
import com.disney.alkemy.repositorios.PersonajeRepositorio;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.disney.alkemy.dto.PersonajeEntradaDTO;
import com.disney.alkemy.dto.PersonajeSalidaDTO;
import com.disney.alkemy.entidades.PeliculaSerie;
import com.disney.alkemy.excepciones.PeliculaSerieExcepcion;
import com.disney.alkemy.excepciones.PersonajeExcepcion;
import com.disney.alkemy.mapeadores.PeliculaSerieMapeador;
import com.disney.alkemy.repositorios.PeliculaSerieRepositorio;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;
import com.disney.alkemy.validaciones.PersonajeValidacion;

/**
 *
 * @author Leandro Deferrari
 */

@Service
public class PersonajeServicioImpl implements IPersonajeServicio {

    @Autowired
    private PersonajeRepositorio personajeRepositorio;

    @Autowired
    private PeliculaSerieRepositorio peliculaSerieRepositorio;

    @Autowired
    private PeliculaSerieServicioImpl peliculaSerieServicio;

    @Autowired
    private PersonajeMapeador personajeMapeador;

    @Autowired
    private PeliculaSerieMapeador peliculaSerieMapeador;

    @Override
    public List<PersonajeDTO> listarPersonajesPorNombreImagen() {

        List<PersonajeDTO> personajesDto = new ArrayList<>();

        List<Personaje> personajes = personajeRepositorio.consultarPersonajes();

        if (!personajes.isEmpty()) {

            for (Personaje personaje : personajes) {

                PersonajeDTO personajeDto = personajeMapeador.personaToPersonajeDTO(personaje);

                personajesDto.add(personajeDto);

            }

        }

        return personajesDto;

    }

    @Transactional
    @Override
    public boolean crearPersonaje(PersonajeEntradaDTO personajeEntradaDto) {

        PersonajeValidacion.validarPersonajeEntradaDTO(personajeEntradaDto);
        
        Personaje personaje = personajeMapeador.personajeEntradaDTOToPersonaje(personajeEntradaDto);

        personajeRepositorio.save(personaje);

        return true;

    }

    @Transactional
    @Override
    public boolean modificarPersonaje(PersonajeEntradaDTO personajeEntradaDto, Integer id) {

        PersonajeValidacion.validarPersonajeEntradaDTO(personajeEntradaDto);
        PersonajeValidacion.validarId(id);

        Optional<Personaje> opcionalPersonaje = personajeRepositorio.findById(id);

        if (opcionalPersonaje.isPresent()) {

            Personaje personaje = opcionalPersonaje.get();

            Personaje personajeAuxiliar = personajeMapeador.
                    personajeEntradaDTOToPersonaje(personajeEntradaDto);

            personajeAuxiliar.setId(personaje.getId());
            personajeAuxiliar.setPeliculasSeries(personaje.getPeliculasSeries());

            personajeRepositorio.save(personajeAuxiliar);

            return true;

        } else {

            throw new PersonajeExcepcion("No existe ese personaje");

        }

    }

    @Transactional
    @Override
    public boolean eliminarPersonaje(Integer id) {

        PersonajeValidacion.validarId(id);

        Optional<Personaje> opcionalPersonaje = personajeRepositorio.findById(id);

        if (opcionalPersonaje.isPresent()) {

            personajeRepositorio.deleteById(id);

            return true;

        } else {

            throw new PersonajeExcepcion("No existe ese personaje");

        }

    }

    @Override
    public PersonajeDetalleDTO detallarPersonajeConSusPeliculasSeries(Integer id) {

        PersonajeValidacion.validarId(id);

        Optional<Personaje> opcionalPersonaje = personajeRepositorio.findById(id);

        if (opcionalPersonaje.isPresent()) {

            PersonajeDetalleDTO personajeDetalle = personajeMapeador.
                    personajeToPersonajeDetalleDTO(opcionalPersonaje.get());

            List<PeliculaSerie> peliculasSeries = peliculaSerieServicio.
                    buscarPeliculasSeriesPorPersonaje(id);

            List<PeliculaSeriePersonajeDTO> peliculasSeriesDto = new ArrayList<>();

            for (PeliculaSerie peliculaSerie : peliculasSeries) {

                PeliculaSeriePersonajeDTO peliculaSeriePersonajeDto = peliculaSerieMapeador.
                        peliculaSerieToPeliculaSeriePersonajeDTO(peliculaSerie);

                peliculasSeriesDto.add(peliculaSeriePersonajeDto);

            }

            personajeDetalle.setPeliculasSeries(peliculasSeriesDto);

            return personajeDetalle;

        } else {

            throw new PersonajeExcepcion("No existe ese personaje");

        }

    }

    @Override
    public List<PersonajeSalidaDTO> buscarPersonajesPorNombre(String nombre) {

        PersonajeValidacion.validarNombre(nombre);
        
        List<PersonajeSalidaDTO> personajesSalida = new ArrayList<>();

        List<Personaje> personajes = personajeRepositorio.findByNombre(nombre);

        if (!personajes.isEmpty()) {

            for (Personaje personaje : personajes) {

                PersonajeSalidaDTO personajeSalida = personajeMapeador.
                        personajeToPersonajeSalidaDTO(personaje);

                personajesSalida.add(personajeSalida);

            }

        }

        return personajesSalida;

    }

    @Override
    public List<PersonajeSalidaDTO> buscarPersonajesPorEdad(byte edad) {

        PersonajeValidacion.validarEdad(edad);
        
        List<PersonajeSalidaDTO> personajesSalida = new ArrayList<>();

        List<Personaje> personajes = personajeRepositorio.findByEdad(edad);

        if (!personajes.isEmpty()) {

            for (Personaje personaje : personajes) {

                PersonajeSalidaDTO personajeSalida = personajeMapeador.
                        personajeToPersonajeSalidaDTO(personaje);

                personajesSalida.add(personajeSalida);

            }

        }

        return personajesSalida;

    }

    @Override
    public List<PersonajeSalidaDTO> buscarPersonajesPorPeso(float peso) {

        PersonajeValidacion.validarPeso(peso);
        
        List<PersonajeSalidaDTO> personajesSalida = new ArrayList<>();

        List<Personaje> personajes = personajeRepositorio.findByPeso(peso);

        if (!personajes.isEmpty()) {

            for (Personaje personaje : personajes) {

                PersonajeSalidaDTO personajeSalida = personajeMapeador.
                        personajeToPersonajeSalidaDTO(personaje);

                personajesSalida.add(personajeSalida);

            }

        }

        return personajesSalida;

    }

    @Override
    public List<PersonajeSalidaDTO> buscarPersonajesPorPeliculaSerie(Integer idPeliculaSerie) {

        PersonajeValidacion.validarId(idPeliculaSerie);

        Optional<PeliculaSerie> opcionalPeliculaSerie = peliculaSerieRepositorio.
                findById(idPeliculaSerie);

        if (opcionalPeliculaSerie.isPresent()) {

            List<Personaje> personajes = personajeRepositorio.
                    findByPeliculasSeries(opcionalPeliculaSerie.get());

            List<PersonajeSalidaDTO> personajesSalida = new ArrayList<>();

            if (!personajes.isEmpty()) {

                for (Personaje personaje : personajes) {

                    PersonajeSalidaDTO personajeSalida = personajeMapeador.
                            personajeToPersonajeSalidaDTO(personaje);

                    personajesSalida.add(personajeSalida);

                }

            }

            return personajesSalida;

        } else {

            throw new PeliculaSerieExcepcion("No existe esa pelicula/serie");

        }

    }
    
}
