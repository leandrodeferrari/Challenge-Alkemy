package com.disney.alkemy.configuraciones;

import com.disney.alkemy.seguridad.JwtAutenticacionDeEntrada;
import com.disney.alkemy.seguridad.JwtFiltroDeToken;
//import com.disney.alkemy.servicios.UsuarioServicioImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.http.HttpMethod;
//import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 *
 * @author Leandro Deferrari
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SeguridadWebConfiguracion extends WebSecurityConfigurerAdapter {

//    @Autowired
//    private UsuarioServicioImpl usuarioServicio;
    @Autowired
    private JwtAutenticacionDeEntrada jwtEntrada;

    @Bean
    JwtFiltroDeToken JwtFiltroDeToken() {
        return new JwtFiltroDeToken();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable().exceptionHandling().authenticationEntryPoint(jwtEntrada).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
                .antMatchers("/auth/**").permitAll().antMatchers(HttpMethod.GET, "/characters/**").permitAll()
                .antMatchers(HttpMethod.GET, "/movies/**").permitAll().antMatchers(HttpMethod.GET, "/**")
                .permitAll().anyRequest().authenticated();
        http.addFilterBefore(JwtFiltroDeToken(), UsernamePasswordAuthenticationFilter.class);

    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
