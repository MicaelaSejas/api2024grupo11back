package com.uade.tpo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.uade.tpo.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	@Query(value = "SELECT * FROM usuarios WHERE email=?1", nativeQuery=true)
    Optional<Usuario> findByEmail(String email);

    Optional<Usuario> findByUsername(String username);
}
