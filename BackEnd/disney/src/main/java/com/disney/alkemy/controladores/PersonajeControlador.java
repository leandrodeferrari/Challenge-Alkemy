package com.disney.alkemy.controladores;

import com.disney.alkemy.dto.PersonajeDTO;
import com.disney.alkemy.dto.PersonajeEntradaDTO;
import com.disney.alkemy.dto.PersonajeSalidaDTO;
import com.disney.alkemy.servicios.PersonajeServicioImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import javax.transaction.Transactional;
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
    @GetMapping("/")
    public ResponseEntity<List<PersonajeDTO>> mostrarListadoDePersonajes() {

        List<PersonajeDTO> listaPersonajes = personajeServicio.listarPersonajesPorNombreImagen();

        if (listaPersonajes != null) {

            return new ResponseEntity<>(listaPersonajes, HttpStatus.OK);

        } else {

            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

        }

    }

    @ApiOperation(value = "Listado de personajes, filtrados por nombre")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = PersonajeSalidaDTO.class),
        @ApiResponse(code = 400, message = "BAD REQUEST. Algo falló en el cliente", response = String.class),
        @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR. Error inesperado del sistema")
    })
    @GetMapping("/search")
    public ResponseEntity<List<PersonajeSalidaDTO>> filtrarPorNombre(@RequestParam("name") String name) {

        List<PersonajeSalidaDTO> personajes = personajeServicio.buscarPersonajesPorNombre(name);

        if (personajes != null) {

            return new ResponseEntity<>(personajes, HttpStatus.OK);

        } else {

            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

        }

    }

    @ApiOperation(value = "Listado de personajes, filtrados por edad")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = PersonajeSalidaDTO.class),
        @ApiResponse(code = 400, message = "BAD REQUEST. Algo falló en el cliente", response = String.class),
        @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR. Error inesperado del sistema")
    })
    @GetMapping("/filter-age")
    public ResponseEntity<List<PersonajeSalidaDTO>> filtrarPorEdad(@RequestParam("age") byte age) {

        List<PersonajeSalidaDTO> personajes = personajeServicio.buscarPersonajesPorEdad(age);

        if (personajes != null) {

            return new ResponseEntity<>(personajes, HttpStatus.OK);

        } else {

            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

        }

    }

    @ApiOperation(value = "Listado de personajes filtrados por Pelicula/Serie")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = PersonajeSalidaDTO.class),
        @ApiResponse(code = 400, message = "BAD REQUEST. Algo falló en el cliente", response = String.class),
        @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR. Error inesperado del sistema")
    })
    @GetMapping("/filter-movie")
    public ResponseEntity<List<PersonajeSalidaDTO>> filtrarPorPeliculaSerie(@RequestParam("idMovie") Integer idMovie) {

        List<PersonajeSalidaDTO> personajes = personajeServicio.buscarPersonajesPorPeliculaSerie(idMovie);

        if (personajes != null) {

            return new ResponseEntity<>(personajes, HttpStatus.OK);

        } else {

            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

        }

    }

//    @ApiOperation(value = "Detalle del personaje", notes = "Atributos de los personajes: Nombre e Imagen")
//    @GetMapping("/detail/{id}")
//    public ResponseEntity<Personaje> detallarPersonaje(@PathVariable("id") Integer id) {
//
//        Personaje personaje = personajeServicio.detallarPersonajeConSusPeliculasSeries(id);
//
//        return new ResponseEntity<>(personaje, HttpStatus.OK);
//
//    }
    
    @ApiOperation(value = "Creación de personajes", notes = "Atributos que se necesita para la creación: Edad, Peso, Imagen, Nombre e Historia")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "CREATED. El recurso se creó correctamente", response = PersonajeEntradaDTO.class),
        @ApiResponse(code = 400, message = "BAD REQUEST. No se pudo crear el recurso, debido a una falla en el cliente", response = String.class),
        @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR. Error inesperado del sistema")
    })
    @PostMapping("/create-character")
    public ResponseEntity<PersonajeEntradaDTO> crearPersonaje(@RequestBody PersonajeEntradaDTO personajeEntradaDto) {

        if (personajeServicio.crearPersonaje(personajeEntradaDto)) {

            return new ResponseEntity<>(personajeEntradaDto, HttpStatus.CREATED);

        } else {

            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

        }

    }

    @ApiOperation(value = "Edición de personajes", notes = "Pasar ID por el path y los atributos que se necesitan para la creación, son: Edad, Peso, Imagen, Nombre e Historia")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "CREATED. El recurso se editó correctamente", response = PersonajeEntradaDTO.class),
        @ApiResponse(code = 400, message = "BAD REQUEST. No se pudo editar el recurso, debido a una falla en el cliente", response = String.class),
        @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR. Error inesperado del sistema")
    })
    @PutMapping("/{id}")
    public ResponseEntity<PersonajeEntradaDTO> editarPersonaje(@PathVariable("id") Integer id, @RequestBody PersonajeEntradaDTO personajeEntradaDto) {

        if (personajeServicio.modificarPersonaje(personajeEntradaDto, id)) {

            return new ResponseEntity<>(personajeEntradaDto, HttpStatus.CREATED);

        } else {

            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

        }

    }

    @ApiOperation(value = "Eliminación de personajes", notes = "Pasar ID por el path")
    @ApiResponses(value = {
        @ApiResponse(code = 204, message = "NO CONTENT. El recurso se borró correctamente", response = String.class),
        @ApiResponse(code = 400, message = "BAD REQUEST. No se pudo eliminar el recurso, debido a una falla en el cliente", response = String.class),
        @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR. Error inesperado del sistema")
    })
    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPersonaje(@PathVariable("id") Integer id) {

        if (personajeServicio.eliminarPersonaje(id)) {

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } else {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }

    }

}
