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

        validarPersonajeEntradaDTO(personajeEntradaDto);
        
        Personaje personaje = personajeMapeador.personajeEntradaDTOToPersonaje(personajeEntradaDto);

        personajeRepositorio.save(personaje);

        return true;

    }

    @Transactional
    @Override
    public boolean modificarPersonaje(PersonajeEntradaDTO personajeEntradaDto, Integer id) {

        validarPersonajeEntradaDTO(personajeEntradaDto);
        validarId(id);

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

        validarId(id);

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

        validarId(id);

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

        validarNombre(nombre);
        
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

        validarEdad(edad);
        
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

        validarPeso(peso);
        
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

        validarId(idPeliculaSerie);

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

    private void validarId(Integer id) {

        if (id == null || id <= 0) {

            throw new RuntimeException("Id inválido o incorrecto");

        }

    }

    private void validarPersonajeEntradaDTO(PersonajeEntradaDTO personajeEntradaDto) {

        validarEdad(personajeEntradaDto.getEdad());
        validarImagen(personajeEntradaDto.getImagen());
        validarNombre(personajeEntradaDto.getNombre());
        validarHistoria(personajeEntradaDto.getHistoria());
        validarPeso(personajeEntradaDto.getPeso());
        
    }

    private void validarEdad(byte edad) {

        if(edad < 0 || edad > 150){
            
            throw new PersonajeExcepcion("Edad fuera de rango");
            
        }
        
    }

    private void validarImagen(String imagen) {

        if(imagen == null || imagen.isEmpty() || imagen.length() > 30){
            
            throw new PersonajeExcepcion("Imagen inválida, demasiada larga o vacía");
            
        }
        
    }

    private void validarNombre(String nombre) {

        if(nombre == null || nombre.isEmpty() || nombre.length() > 30){
            
            throw new PersonajeExcepcion("Nombre inválido, demasiado largo o vacío");
            
        }
        
    }

    private void validarPeso(float peso) {

        if(peso < 0 || peso > 600){
            
            throw new PersonajeExcepcion("Edad fuera de rango");
            
        }
        
    }

    private void validarHistoria(String historia) {

        if(historia == null || historia.isEmpty() || historia.length() > 255){
            
            throw new PersonajeExcepcion("Historia inválida, demasiado larga o vacía");
            
        }
        
    }
    
}
