package com.disney.alkemy.controladores;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.disney.alkemy.dto.PeliculaSerieDTO;
import com.disney.alkemy.dto.PeliculaSerieDetalleDTO;
import com.disney.alkemy.dto.PeliculaSerieEntradaDTO;
import com.disney.alkemy.dto.PeliculaSerieSalidaDTO;
import com.disney.alkemy.servicios.PeliculaSerieServicioImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Leandro Deferrari
 */

@RestController
@RequestMapping("/movies")
public class PeliculaSerieControlador {

    @Autowired
    private PeliculaSerieServicioImpl peliculaSerieServicio;
    
    @ApiOperation(value = "Listado de peliculas/series", notes = "Atributos de las peliculas/series a mostrar: Título, Imagen y Fecha de creación")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = PeliculaSerieDTO.class),
        @ApiResponse(code = 400, message = "BAD REQUEST. Algo falló en el cliente", response = String.class),
        @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR. Error inesperado del sistema")
    })
    @GetMapping
    public ResponseEntity<List<PeliculaSerieDTO>> mostrarListadoDePeliculasSeries(){
        
        List<PeliculaSerieDTO> peliculasSeriesDto = peliculaSerieServicio.listarPeliculasSeriesPorTituloImagenFechaDeCreacion();
        
        if(peliculasSeriesDto != null) {
            
            return new ResponseEntity<>(peliculasSeriesDto, HttpStatus.OK);
            
        } else {
            
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            
        }
        
    }
    
    @ApiOperation(value = "Listado de peliculas/series, filtrados por título", notes = "Recibe, como parámetro, un título (String, longitud máx: 30)")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = PeliculaSerieSalidaDTO.class),
        @ApiResponse(code = 400, message = "BAD REQUEST. Algo falló en el cliente", response = String.class),
        @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR. Error inesperado del sistema")
    })
    @GetMapping("/search")
    public ResponseEntity<List<PeliculaSerieSalidaDTO>> buscarPorTitulo(@RequestParam("title") String title){
        
        List<PeliculaSerieSalidaDTO> peliculasSeriesSalida = peliculaSerieServicio.buscarPeliculasSeriesPorTitulo(title);

        if (peliculasSeriesSalida != null) {

            return new ResponseEntity<>(peliculasSeriesSalida, HttpStatus.OK);

        } else {
        
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        
        }
        
    }
    
    @ApiOperation(value = "Listado de peliculas/series, filtrados por género", notes = "Recibe, como parámetro, el ID de un género (Integer)")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = PeliculaSerieSalidaDTO.class),
        @ApiResponse(code = 400, message = "BAD REQUEST. Algo falló en el cliente", response = String.class),
        @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR. Error inesperado del sistema")
    })
    @GetMapping("/filter-genre")
    public ResponseEntity<List<PeliculaSerieSalidaDTO>> filtrarPorGenero(@RequestParam("idGenre") Integer idGenre){
        
        List<PeliculaSerieSalidaDTO> peliculasSeriesSalida = peliculaSerieServicio.buscarPeliculasSeriesPorGenero(idGenre);

        if (peliculasSeriesSalida != null) {

            return new ResponseEntity<>(peliculasSeriesSalida, HttpStatus.OK);

        } else {
        
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        
        }
        
    }
    
    @ApiOperation(value = "Listado de peliculas/series, ordenadas de forma ascendente o descendente", notes = "Recibe, como parámetro, el modo de ordenamiento (String de longitud máx 4, ASC O DESC, sin disntición de mayúsculas o minúsculas)")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = PeliculaSerieSalidaDTO.class),
        @ApiResponse(code = 400, message = "BAD REQUEST. Algo falló en el cliente", response = String.class),
        @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR. Error inesperado del sistema")
    })
    @GetMapping("/order")
    public ResponseEntity<List<PeliculaSerieSalidaDTO>> ordenarPeliculasSeries(@RequestParam("mode") String mode){
        
        if(mode.equalsIgnoreCase("asc")){
            
            List<PeliculaSerieSalidaDTO> peliculasSeriesSalida = peliculaSerieServicio.ordenarPeliculasSeriesPorFechaDeCreacionAsc();
            
            return new ResponseEntity<>(peliculasSeriesSalida, HttpStatus.OK);
            
        } else if(mode.equalsIgnoreCase("desc")){
            
            List<PeliculaSerieSalidaDTO> peliculasSeriesSalida = peliculaSerieServicio.ordenarPeliculasSeriesPorFechaDeCreacionDesc();
            
            return new ResponseEntity<>(peliculasSeriesSalida, HttpStatus.OK);
            
        } else {
            
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            
        }
        
    }
    
    @ApiOperation(value = "Detalle de la Pelicula/serie, con sus Personajes", notes = "Recibe por path, el ID de la Pelicula/serie")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = PeliculaSerieDetalleDTO.class),
        @ApiResponse(code = 400, message = "BAD REQUEST. Algo falló en el cliente", response = String.class),
        @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR. Error inesperado del sistema")
    })
    @GetMapping("/detail/{id}")
    public ResponseEntity<PeliculaSerieDetalleDTO> detallarPeliculaSerie(@PathVariable("id") Integer id) {

        PeliculaSerieDetalleDTO peliculaSerieDetalle = peliculaSerieServicio.detallarPeliculaSerieConSusPersonajes(id);

        return new ResponseEntity<>(peliculaSerieDetalle, HttpStatus.OK);

    }
    
    @ApiOperation(value = "Creación de pelicula/serie", notes = "Atributos que se necesita para la creación: Título, Imagen, Calificación y Nombre del Género, este último sin disntición de mayúsculas o minúsculas")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "CREATED. El recurso se creó correctamente", response = PeliculaSerieEntradaDTO.class),
        @ApiResponse(code = 400, message = "BAD REQUEST. No se pudo crear el recurso, debido a una falla en el cliente", response = String.class),
        @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR. Error inesperado del sistema")
    })
    @PostMapping("/create-movie")
    public ResponseEntity<PeliculaSerieEntradaDTO> crearPeliculaSerie(@RequestBody PeliculaSerieEntradaDTO peliculaSerieEntradaDto){
        
        if(peliculaSerieServicio.crearPeliculaSerie(peliculaSerieEntradaDto)){
            
            return new ResponseEntity<>(peliculaSerieEntradaDto, HttpStatus.CREATED);
            
        } else {
            
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            
        }
        
    }
    
    @ApiOperation(value = "Edición de pelicula/serie", notes = "Pasar ID por el path y los atributos que se necesitan para la edición, son: Título, Imagen, Calificación y Nombre del Género, este último sin disntición de mayúsculas o minúsculas")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "CREATED. El recurso se editó correctamente", response = PeliculaSerieEntradaDTO.class),
        @ApiResponse(code = 400, message = "BAD REQUEST. No se pudo editar el recurso, debido a una falla en el cliente", response = String.class),
        @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR. Error inesperado del sistema")
    })
    @PutMapping("/{id}")
    public ResponseEntity<PeliculaSerieEntradaDTO> editarPersonaje(@PathVariable("id") Integer id, @RequestBody PeliculaSerieEntradaDTO peliculaSerieEntradaDTO) {

        if (peliculaSerieServicio.modificarPeliculaSerie(peliculaSerieEntradaDTO, id)) {

            return new ResponseEntity<>(peliculaSerieEntradaDTO, HttpStatus.CREATED);

        } else {

            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

        }

    }
    
    @ApiOperation(value = "Eliminación de pelicula/serie", notes = "Pasar ID por el path")
    @ApiResponses(value = {
        @ApiResponse(code = 204, message = "NO CONTENT. El recurso se borró correctamente", response = String.class),
        @ApiResponse(code = 400, message = "BAD REQUEST. No se pudo eliminar el recurso, debido a una falla en el cliente", response = String.class),
        @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR. Error inesperado del sistema")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPersonaje(@PathVariable("id") Integer id) {

        if (peliculaSerieServicio.eliminarPeliculaSerie(id)) {

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } else {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }

    }
    
}
