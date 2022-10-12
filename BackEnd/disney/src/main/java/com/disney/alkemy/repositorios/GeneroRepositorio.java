package com.disney.alkemy.repositorios;

import com.disney.alkemy.entidades.Genero;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Leandro Deferrari
 */

@Repository
public interface GeneroRepositorio extends JpaRepository<Genero, Integer> {

    public Optional<Genero> findByNombre(String nombre);
    
}
