package com.disney.alkemy.entidades;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

/**
 *
 * @author Leandro Deferrari
 */

@Entity
@Data
@Table(name = "peliculas_series")
public class PeliculaSerie implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String titulo;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] imagen;
    @Column(name = "fecha_de_creacion")
    private LocalDateTime fechaDeCreacion;
    private byte calificacion;
    @ManyToOne
    @JoinColumn(name = "id_genero")
    private Genero genero;
    @ManyToMany
    @JoinTable(name = "personajes_peliculas_series",
            joinColumns = {
                @JoinColumn(name = "id_pelicula_serie", referencedColumnName = "id")},
            inverseJoinColumns = {
                @JoinColumn(name = "id_personaje", referencedColumnName = "id")})
    private List<Personaje> personajes;

}
