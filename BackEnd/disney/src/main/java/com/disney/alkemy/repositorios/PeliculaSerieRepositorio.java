package com.disney.alkemy.repositorios;

import com.disney.alkemy.entidades.Genero;
import com.disney.alkemy.entidades.PeliculaSerie;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Leandro Deferrari
 */

@Repository
public interface PeliculaSerieRepositorio extends JpaRepository<PeliculaSerie, Integer> {

    @Query(value = "SELECT * FROM peliculas_series", nativeQuery = true)
    public List<PeliculaSerie> consultarPeliculasSeries();

    public List<PeliculaSerie> findByTitulo(String titulo);

    public List<PeliculaSerie> findByGenero(Genero genero);

    @Query(value = "SELECT * FROM peliculas_series ORDER BY fecha_de_creacion ASC", nativeQuery = true)
    public List<PeliculaSerie> ordenarPeliculasSeriesPorFechaDeCreacionAsc();

    @Query(value = "SELECT * FROM peliculas_series ORDER BY fecha_de_creacion DESC", nativeQuery = true)
    public List<PeliculaSerie> ordenarPeliculasSeriesPorFechaDeCreacionDesc();
    
}
