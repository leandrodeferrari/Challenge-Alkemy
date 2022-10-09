package com.disney.alkemy.repositorios;

import com.disney.alkemy.dto.PersonajeDTO;
import com.disney.alkemy.entidades.Personaje;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Leandro Deferrari
 */

@Repository
public interface PersonajeRepositorio extends JpaRepository<Personaje, Integer> {

    @Query(value = "SELECT nombre, imagen FROM personajes", nativeQuery = true)
    public List<PersonajeDTO> consultarNombresImagenesDePersonajes();

    public Optional<Personaje> findByNombre(String nombre);

    public Optional<Personaje> findByEdad(byte edad);

    public Optional<Personaje> findByPeso(float peso);

    public Optional<Personaje> findByPeliculasSeries(Integer idPeliculaSerie);
    
//    @Query(value = "SELECT p FROM Personaje p JOIN p.peliculasSeries ps WHERE ps.id = :idPersonaje")
//    public List<Personaje> consultarPersonajeConSusPeliculasSeries(Integer idPersonaje);

}
