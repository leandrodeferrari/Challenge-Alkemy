package com.disney.alkemy.repositorios;

import com.disney.alkemy.entidades.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Leandro Deferrari
 */

@Repository
public interface UsuarioRepositorio extends JpaRepository {

    public Optional<Usuario> findByEmail(String email);

}
