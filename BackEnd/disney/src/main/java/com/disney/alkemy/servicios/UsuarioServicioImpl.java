package com.disney.alkemy.servicios;

import com.disney.alkemy.dto.UsuarioRegistroDTO;
import com.disney.alkemy.entidades.Usuario;
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

//            ServletRequestAttributes atributo = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
//
//            HttpSession sesion = atributo.getRequest().getSession(true);
//
//            sesion.setAttribute("UsuarioSesion", usuario);
            return new User(usuario.getEmail(), usuario.getContrasenia(), permisos);

        } else {

            throw new UsernameNotFoundException("Error al cargar usuario");

        }

    }

}
