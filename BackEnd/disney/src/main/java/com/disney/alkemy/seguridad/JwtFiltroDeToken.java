package com.disney.alkemy.seguridad;

import com.disney.alkemy.servicios.UsuarioServicioImpl;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 *
 * @author Leandro Deferrari
 */

public class JwtFiltroDeToken extends OncePerRequestFilter {

    @Autowired
    private JwtProveedor jwtProveedor;

    @Autowired
    private UsuarioServicioImpl usuarioServicio;
    
    @Bean
    UsuarioServicioImpl usuarioServicioImpl() {
        return new UsuarioServicioImpl();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // obtenemos el token de la solicitud HTTP
        String token = getJWTfromRequest(request);

        // validamos el token
        if (StringUtils.hasText(token) && jwtProveedor.validateToken(token)) {
            // obtenemos el username del token
            String email = jwtProveedor.getEmailJWT(token);

            // cargamos el usuario asociado al token
            UserDetails userDetails = usuarioServicio.loadUserByUsername(email);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            // establecemos la seguridad
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request, response);
    }

    private String getJWTfromRequest(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer")) {
            return header.replace("Bearer ", "");
        }
        return null;
    }
}
