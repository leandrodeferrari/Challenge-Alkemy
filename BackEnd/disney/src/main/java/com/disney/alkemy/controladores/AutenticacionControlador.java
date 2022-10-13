package com.disney.alkemy.controladores;

import com.disney.alkemy.dto.JwtDTO;
import com.disney.alkemy.dto.UsuarioIngresoDTO;
import com.disney.alkemy.dto.UsuarioRegistroDTO;
import com.disney.alkemy.seguridad.JwtProveedor;
import com.disney.alkemy.servicios.UsuarioServicioImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Leandro Deferrari
 */

@RestController
@RequestMapping("/auth")
public class AutenticacionControlador {

    @Autowired
    private AuthenticationManager autenticacionManager;

    @Autowired
    private UsuarioServicioImpl usuarioServicio;
    
    @Autowired
    private JwtProveedor jwtProveedor;

    @PostMapping("/login")
    public ResponseEntity<JwtDTO> ingresar(@RequestBody UsuarioIngresoDTO usuarioIngresoDto) {

        Authentication autenticacion = autenticacionManager.
                authenticate(new UsernamePasswordAuthenticationToken(usuarioIngresoDto.getEmail(), usuarioIngresoDto.getContrasenia()));
        
        SecurityContextHolder.getContext().setAuthentication(autenticacion);
        
        String token = jwtProveedor.generarToken(autenticacion);
        
        JwtDTO jwt = new JwtDTO(token);
        
        return new ResponseEntity<>(jwt, HttpStatus.OK);
        
    }

    @PostMapping("/register")
    public ResponseEntity<String> registrarse(@RequestBody UsuarioRegistroDTO usuarioRegistrado) {

        usuarioServicio.crearUsuario(usuarioRegistrado);

        return new ResponseEntity<>("Usuario registrado", HttpStatus.OK);

    }

}
