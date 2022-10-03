package com.disney.alkemy.repositorios;

import com.disney.alkemy.entidades.Personaje;
import java.util.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Leandro Deferrari
 */
@Repository
public interface PersonajeRepositorio extends JpaRepository<Personaje, Integer> {

    @Query(value = "SELECT nombre, imagen FROM personajes", nativeQuery = true)
    public List<Personaje> consultarNombresImagenesDePersonajes();

    public Optional<Personaje> findByNombre(String nombre);

    public Optional<Personaje> findByEdad(byte edad);

    public Optional<Personaje> findByPeso(float peso);

    // Hay que ver si funciona
    public Optional<Personaje> findByPeliculasSeries(Integer idPeliculaSerie);
    
//    @Query(value = "SELECT p FROM Personaje p JOIN p.peliculasSeries ps WHERE ps.id = :idPersonaje")
//    public List<Personaje> consultarPersonajeConSusPeliculasSeries(Integer idPersonaje);

}
