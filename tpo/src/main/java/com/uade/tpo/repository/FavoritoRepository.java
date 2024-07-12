package com.uade.tpo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.uade.tpo.entity.Favorito;

@Repository
public interface FavoritoRepository extends JpaRepository<Favorito, Long> {

    @Query(value = "SELECT * FROM Favorito c WHERE c.idUsuario = ?1", nativeQuery = true)
    Optional<Favorito> findByUserId(Long id);

}
