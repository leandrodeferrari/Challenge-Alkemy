package com.disney.alkemy.controladores;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Leandro Deferrari
 */

@RestController
@RequestMapping("/auth")
public class AutenticacionControlador {

    @PostMapping("/login")
    public void ingresar(){
        
        
        
    }
    
    @PostMapping("/register")
    public void registrarse(){
        
        
    }
    
}
