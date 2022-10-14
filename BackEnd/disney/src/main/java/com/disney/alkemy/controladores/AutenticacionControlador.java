package com.disney.alkemy.controladores;

import com.disney.alkemy.dto.JwtDTO;
import com.disney.alkemy.dto.UsuarioIngresoDTO;
import com.disney.alkemy.dto.UsuarioRegistroDTO;
import com.disney.alkemy.excepciones.UsuarioExcepcion;
import com.disney.alkemy.seguridad.JwtProveedor;
import com.disney.alkemy.servicios.UsuarioServicioImpl;
import com.disney.alkemy.validaciones.UsuarioValidacion;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    @ApiOperation(value = "Ingreso de Usuario", notes = "Atributos del usuario, para su autenticación: Email y Contraseña")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "OK. El recurso se creó correctamente", response = String.class),
        @ApiResponse(code = 400, message = "BAD REQUEST. Algo falló en el cliente", response = String.class),
        @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR. Error inesperado del sistema")
    })
    @PostMapping("/login")
    public ResponseEntity<JwtDTO> ingresar(@RequestBody UsuarioIngresoDTO usuarioIngresoDto) {

        try {
            
            UsuarioValidacion.validarUsuarioIngresoDTO(usuarioIngresoDto);
            
        } catch (UsuarioExcepcion ex) {
            
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            
        }
        
        Authentication autenticacion = autenticacionManager.
                authenticate(new UsernamePasswordAuthenticationToken(usuarioIngresoDto.getEmail(), usuarioIngresoDto.getContrasenia()));
        
        SecurityContextHolder.getContext().setAuthentication(autenticacion);
        
        String token = jwtProveedor.generarToken(autenticacion);
        
        JwtDTO jwt = new JwtDTO(token);
        
        return new ResponseEntity<>(jwt, HttpStatus.OK);
        
    }

    @ApiOperation(value = "Registro de Usuario", notes = "Atributos del usuario, para su creación: Nombre, Email, Contraseña y Rol")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "CREATED. El recurso se creó correctamente", response = String.class),
        @ApiResponse(code = 400, message = "BAD REQUEST. Algo falló en el cliente", response = String.class),
        @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR. Error inesperado del sistema")
    })
    @PostMapping("/register")
    public ResponseEntity<String> registrarse(@RequestBody UsuarioRegistroDTO usuarioRegistrado) {

        try {
            
            UsuarioValidacion.validarUsuarioRegistroDTO(usuarioRegistrado);
            
        } catch (UsuarioExcepcion ex) {
            
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
            
        }
        
        usuarioServicio.crearUsuario(usuarioRegistrado);

        return new ResponseEntity<>("Usuario registrado", HttpStatus.CREATED);

    }
    
}
