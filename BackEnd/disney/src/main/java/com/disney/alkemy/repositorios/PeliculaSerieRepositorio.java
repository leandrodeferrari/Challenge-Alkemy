package com.disney.alkemy.repositorios;

import com.disney.alkemy.entidades.PeliculaSerie;
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
public interface PeliculaSerieRepositorio extends JpaRepository<PeliculaSerie, Integer> {

    @Query(value = "SELECT titulo, imagen, fecha_de_creacion FROM peliculas_series", nativeQuery = true)
    public List<PeliculaSerie> consultarTitulosImagenesFechasDeCreacionDePeliculasSeries();

    public Optional<PeliculaSerie> findByTitulo(String titulo);

    public Optional<PeliculaSerie> findByGenero(Integer idGenero);

    @Query(value = "SELECT * FROM peliculas_series ORDER BY fecha_de_creacion ASC", nativeQuery = true)
    public List<PeliculaSerie> ordenarPeliculasSeriesPorFechaDeCreacionAsc();

    @Query(value = "SELECT * FROM peliculas_series ORDER BY fecha_de_creacion DESC", nativeQuery = true)
    public List<PeliculaSerie> ordenarPeliculasSeriesPorFechaDeCreacionDesc();

//    @Query(value = "SELECT ps FROM PeliculaSerie ps JOIN ps.personajes p WHERE p.id = :idPeliculaSerie")
//    public List<Personaje> consultarPersonajeConSusPeliculasSeries(Integer idPeliculaSerie);
    
}
