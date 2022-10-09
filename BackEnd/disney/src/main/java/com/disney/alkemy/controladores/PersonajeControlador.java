package com.disney.alkemy.controladores;

import com.disney.alkemy.entidades.Personaje;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Leandro Deferrari
 */

@RestController
@RequestMapping("/characters")
public class PersonajeControlador {

    @GetMapping
    public ResponseEntity<List<Personaje>> mostrarListadoDePersonajes(){
        
        return null;
        
    }
    
}
