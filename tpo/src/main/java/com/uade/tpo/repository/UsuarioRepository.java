package com.uade.tpo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.uade.tpo.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByMail(String mail);

    Optional<Usuario> findByUsuario(String usuario);

    List<Usuario> findByRolId(Long idRol);

    @Query("SELECT u FROM Usuario u JOIN FETCH u.rol WHERE u.id = :id")
    Optional<Usuario> findByIdWithRol(Long id)
    ;
}
