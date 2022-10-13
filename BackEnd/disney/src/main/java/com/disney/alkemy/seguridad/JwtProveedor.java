package com.disney.alkemy.seguridad;

import io.jsonwebtoken.Jwts;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

//import org.springframework.http.HttpStatus;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.MalformedJwtException;
//import io.jsonwebtoken.ExpiredJwtException;
//import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
//import io.jsonwebtoken.UnsupportedJwtException;

/**
 *
 * @author Leandro Deferrari
 */

@Component
public class JwtProveedor {

    @Value("${jwt.secret}")
    private String secreto;

    @Value("${jwt.expiration}")
    private int expiracionMilisegundos;

    public String generarToken(Authentication autenticacion) {

        UserDetails usuario = (UserDetails) autenticacion.getPrincipal();

        String email = usuario.getUsername();

        Date fechaActual = new Date();

        Date fechaExpiracion = new Date(fechaActual.getTime() + expiracionMilisegundos);

        String token = Jwts.builder().setSubject(email).setIssuedAt(new Date()).setExpiration(fechaExpiracion)
                .signWith(SignatureAlgorithm.HS512, secreto).compact();

        return token;

    }

    public String getEmailJWT(String token) {
        Claims claims = Jwts.parser().setSigningKey(secreto).parseClaimsJws(token).getBody();
		return claims.getSubject();
    }

//    public boolean validateToken(String token) {
//		try {
//			Jwts.parser().setSigningKey(secreto).parseClaimsJws(token);
//			return true;
//		}catch (MalformedJwtException ex) {
//			throw new BlogAppException(HttpStatus.BAD_REQUEST,"Token JWT no valida");
//		}
//		catch (ExpiredJwtException ex) {
//			throw new BlogAppException(HttpStatus.BAD_REQUEST,"Token JWT caducado");
//		}
//		catch (UnsupportedJwtException ex) {
//			throw new BlogAppException(HttpStatus.BAD_REQUEST,"Token JWT no compatible");
//		}
//		catch (IllegalArgumentException ex) {
//			throw new BlogAppException(HttpStatus.BAD_REQUEST,"La cadena claims JWT esta vacia");
//		}
//	}
    
    public boolean validateToken(String authToken) {
		try {
			Jws<Claims> claims = Jwts.parser().setSigningKey(secreto).parseClaimsJws(authToken);
			return true;
		} catch (SignatureException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException ex) {
			throw new BadCredentialsException("INVALID_CREDENTIALS", ex);
		} catch (ExpiredJwtException ex) {
			throw ex;
		}
	}
    
}
