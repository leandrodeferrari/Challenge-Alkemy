package com.disney.alkemy.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author Leandro Deferrari
 */

@Entity
@Data
@Table(name = "personajes")
public class Personaje implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private byte edad;
    private float peso;
    private String imagen;
    private String nombre;
    private String historia;
    @ManyToMany
    @JoinTable(name = "personajes_peliculas_series",
            joinColumns = {
                @JoinColumn(name = "id_personaje", referencedColumnName = "id")},
            inverseJoinColumns = {
                @JoinColumn(name = "id_pelicula_serie", referencedColumnName = "id")})
    private List<PeliculaSerie> peliculasSeries;

}
