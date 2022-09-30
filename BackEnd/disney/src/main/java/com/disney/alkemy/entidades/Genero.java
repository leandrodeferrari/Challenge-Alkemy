package com.disney.alkemy.entidades;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;

/**
 *
 * @author Leandro Deferrari
 */

@Entity
@Data
@Table(name = "generos")
public class Genero implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    @Lob 
    @Basic(fetch = FetchType.LAZY)
    private byte[] imagen;
    
}
