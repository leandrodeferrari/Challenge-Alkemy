package com.disney.alkemy.controladores;

import com.disney.alkemy.dto.PersonajeDTO;
import com.disney.alkemy.dto.PersonajeDetalleDTO;
import com.disney.alkemy.dto.PersonajeEntradaDTO;
import com.disney.alkemy.dto.PersonajeSalidaDTO;
import com.disney.alkemy.excepciones.PersonajeExcepcion;
import com.disney.alkemy.servicios.PersonajeServicioImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Leandro Deferrari
 */

@RestController
@RequestMapping("/characters")
public class PersonajeControlador {

    @Autowired
    private PersonajeServicioImpl personajeServicio;

    @ApiOperation(value = "Listado de personajes", notes = "Atributos de los personajes a mostrar: Nombre e Imagen")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = PersonajeDTO.class),
        @ApiResponse(code = 400, message = "BAD REQUEST. Algo falló en el cliente", response = String.class),
        @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR. Error inesperado del sistema")
    })
    @GetMapping
    public ResponseEntity<List<PersonajeDTO>> mostrarListadoDePersonajes() {

        List<PersonajeDTO> listaPersonajes = personajeServicio.listarPersonajesPorNombreImagen();

        if (listaPersonajes != null) {

            return new ResponseEntity<>(listaPersonajes, HttpStatus.OK);

        } else {

            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    @ApiOperation(value = "Listado de personajes, filtrados por nombre", notes = "Recibe, como parámetro, un nombre (String, longitud máx: 30)")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = PersonajeSalidaDTO.class),
        @ApiResponse(code = 400, message = "BAD REQUEST. Algo falló en el cliente", response = String.class),
        @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR. Error inesperado del sistema")
    })
    @GetMapping("/search")
    public ResponseEntity<List<PersonajeSalidaDTO>> buscarPorNombre(@RequestParam("name") String name) {

        try {
            
            validarNombre(name);
            
        } catch (PersonajeExcepcion ex) {
            
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            
        }
        
        List<PersonajeSalidaDTO> personajes = personajeServicio.buscarPersonajesPorNombre(name);

        if (personajes != null) {

            return new ResponseEntity<>(personajes, HttpStatus.OK);

        } else {

            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    @ApiOperation(value = "Listado de personajes, filtrados por edad", notes = "Recibe, como parámetro, una edad (byte)")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = PersonajeSalidaDTO.class),
        @ApiResponse(code = 400, message = "BAD REQUEST. Algo falló en el cliente", response = String.class),
        @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR. Error inesperado del sistema")
    })
    @GetMapping("/filter-age")
    public ResponseEntity<List<PersonajeSalidaDTO>> filtrarPorEdad(@RequestParam("age") byte age) {

        try {
            
            validarEdad(age);
            
        } catch (PersonajeExcepcion ex) {
            
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            
        }
        
        List<PersonajeSalidaDTO> personajes = personajeServicio.buscarPersonajesPorEdad(age);

        if (personajes != null) {

            return new ResponseEntity<>(personajes, HttpStatus.OK);

        } else {

            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    @ApiOperation(value = "Listado de personajes, filtrados por Pelicula/Serie", notes = "Recibe, como parámetro, un ID de Pelicula/Serie (Integer)")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = PersonajeSalidaDTO.class),
        @ApiResponse(code = 400, message = "BAD REQUEST. Algo falló en el cliente", response = String.class),
        @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR. Error inesperado del sistema")
    })
    @GetMapping("/filter-movie")
    public ResponseEntity<List<PersonajeSalidaDTO>> filtrarPorPeliculaSerie(@RequestParam("idMovie") Integer idMovie) {

        try {
            
            validarId(idMovie);
            
        } catch (PersonajeExcepcion ex) {
            
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            
        }
        
        List<PersonajeSalidaDTO> personajes = personajeServicio.buscarPersonajesPorPeliculaSerie(idMovie);

        if (personajes != null) {

            return new ResponseEntity<>(personajes, HttpStatus.OK);

        } else {

            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    @ApiOperation(value = "Detalle del personaje, con sus Peliculas/Series", notes = "Recibe por path, el ID del personaje")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = PersonajeDetalleDTO.class),
        @ApiResponse(code = 400, message = "BAD REQUEST. Algo falló en el cliente", response = String.class),
        @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR. Error inesperado del sistema")
    })
    @GetMapping("/detail/{id}")
    public ResponseEntity<PersonajeDetalleDTO> detallarPersonaje(@PathVariable("id") Integer id) {

        try {
            
            validarId(id);
            
        } catch (PersonajeExcepcion ex) {
            
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            
        }
        
        PersonajeDetalleDTO personaje = personajeServicio.detallarPersonajeConSusPeliculasSeries(id);

        return new ResponseEntity<>(personaje, HttpStatus.OK);

    }
    
    @ApiOperation(value = "Creación de personaje", notes = "Atributos que se necesita para la creación: Edad, Peso, Imagen, Nombre e Historia")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "CREATED. El recurso se creó correctamente", response = PersonajeEntradaDTO.class),
        @ApiResponse(code = 400, message = "BAD REQUEST. No se pudo crear el recurso, debido a una falla en el cliente", response = String.class),
        @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR. Error inesperado del sistema")
    })
    @PostMapping("/create-character")
    public ResponseEntity<PersonajeEntradaDTO> crearPersonaje(@RequestBody PersonajeEntradaDTO personajeEntradaDto) {

        try {
            
            validarPersonajeEntradaDTO(personajeEntradaDto);
            
        } catch (PersonajeExcepcion ex) {
            
            return new ResponseEntity<>(personajeEntradaDto, HttpStatus.BAD_REQUEST);
            
        }
        
        if (personajeServicio.crearPersonaje(personajeEntradaDto)) {

            return new ResponseEntity<>(personajeEntradaDto, HttpStatus.CREATED);

        } else {

            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

        }

    }

    @ApiOperation(value = "Edición de personaje", notes = "Pasar ID por el path y los atributos que se necesitan para la edición, son: Edad, Peso, Imagen, Nombre e Historia")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "CREATED. El recurso se editó correctamente", response = PersonajeEntradaDTO.class),
        @ApiResponse(code = 400, message = "BAD REQUEST. No se pudo editar el recurso, debido a una falla en el cliente", response = String.class),
        @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR. Error inesperado del sistema")
    })
    @PutMapping("/{id}")
    public ResponseEntity<PersonajeEntradaDTO> editarPersonaje(@PathVariable("id") Integer id, @RequestBody PersonajeEntradaDTO personajeEntradaDto) {

        try {
            
            validarId(id);
            validarPersonajeEntradaDTO(personajeEntradaDto);
            
        } catch (PersonajeExcepcion ex) {
            
            return new ResponseEntity<>(personajeEntradaDto, HttpStatus.BAD_REQUEST);
            
        }
        
        if (personajeServicio.modificarPersonaje(personajeEntradaDto, id)) {

            return new ResponseEntity<>(personajeEntradaDto, HttpStatus.CREATED);

        } else {

            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    @ApiOperation(value = "Eliminación de personajes", notes = "Pasar ID por el path")
    @ApiResponses(value = {
        @ApiResponse(code = 204, message = "NO CONTENT. El recurso se borró correctamente", response = String.class),
        @ApiResponse(code = 400, message = "BAD REQUEST. No se pudo eliminar el recurso, debido a una falla en el cliente", response = String.class),
        @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR. Error inesperado del sistema")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPersonaje(@PathVariable("id") Integer id) {

        try {
            
            validarId(id);
            
        } catch (PersonajeExcepcion ex) {
            
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
            
        }
        
        if (personajeServicio.eliminarPersonaje(id)) {

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } else {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    private void validarId(Integer id){
        
        if(id == null || id <= 0){
            
            throw new PersonajeExcepcion("Id inválido o nulo");
            
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
