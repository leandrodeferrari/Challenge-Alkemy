package com.disney.alkemy.servicios;

import com.disney.alkemy.dto.UsuarioRegistroDTO;
import com.disney.alkemy.entidades.Usuario;
import com.disney.alkemy.excepciones.UsuarioExcepcion;
import com.disney.alkemy.mapeadores.UsuarioMapeador;
import com.disney.alkemy.repositorios.UsuarioRepositorio;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Leandro Deferrari
 */

@Service
public class UsuarioServicioImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    
    @Autowired
    private UsuarioMapeador usuarioMapeador;

    public boolean crearUsuario(UsuarioRegistroDTO usuarioRegistado) {

        validarUsuarioRegistroDTO(usuarioRegistado);
        
        Usuario usuario = usuarioMapeador.usuarioRegistroDTOToUsuario(usuarioRegistado);
        
        usuario.setFechaDeAlta(LocalDateTime.now());
        usuario.setContrasenia(new BCryptPasswordEncoder().encode(usuario.getContrasenia()));

        usuarioRepositorio.save(usuario);
         
        return true;

    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<Usuario> respuesta = usuarioRepositorio.findByEmail(email);

        if (respuesta.isPresent()) {

            Usuario usuario = respuesta.get();

            List<GrantedAuthority> permisos = new ArrayList<>();

            GrantedAuthority permiso = new SimpleGrantedAuthority("ROLE_" + usuario.getRol());

            permisos.add(permiso);

            return new User(usuario.getEmail(), usuario.getContrasenia(), permisos);

        } else {

            throw new UsernameNotFoundException("Error al cargar usuario");

        }

    }

    private void validarUsuarioRegistroDTO(UsuarioRegistroDTO usuarioRegistroDto){
        
        validarNombre(usuarioRegistroDto.getNombre());
        validarEmail(usuarioRegistroDto.getEmail());
        validarContrasenia(usuarioRegistroDto.getContrasenia());
        validarRol(usuarioRegistroDto.getRol());
        
    }
    
    private void validarNombre(String nombre){
        
        if(nombre == null || nombre.isEmpty() || nombre.length() > 30){
            
            throw new UsuarioExcepcion("Nombre inválido, demasiado largo o vacío");
            
        }
        
    }
    
    private void validarEmail(String email){
        
        if(email == null || email.isEmpty() || email.length() > 50){
            
            throw new UsuarioExcepcion("Email inválido, demasiado largo o vacío");
            
        }
        
    }
    
    private void validarContrasenia(String contrasenia){
        
        if(contrasenia == null || contrasenia.isEmpty() || contrasenia.length() > 255){
            
            throw new UsuarioExcepcion("Contraseña inválida, demasiada larga o vacía");
            
        }
        
    }
    
    private void validarRol(String rol){
        
        if(rol == null || rol.isEmpty() || rol.length() > 20){
            
            throw new UsuarioExcepcion("Rol inválido, demasiado largo o vacío");
            
        }
        
    }
    
}
