package com.disney.alkemy.repositorios;

import com.disney.alkemy.entidades.PeliculaSerie;
import com.disney.alkemy.entidades.Personaje;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Leandro Deferrari
 */

@Repository
public interface PersonajeRepositorio extends JpaRepository<Personaje, Integer> {

    @Query(value = "SELECT * FROM personajes", nativeQuery = true)
    public List<Personaje> consultarPersonajes();

    public List<Personaje> findByNombre(String nombre);

    public List<Personaje> findByEdad(byte edad);

    public List<Personaje> findByPeso(float peso);

    public List<Personaje> findByPeliculasSeries(PeliculaSerie peliculaSerie);

}
