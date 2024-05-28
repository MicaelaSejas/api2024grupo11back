package com.uade.tpo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.uade.tpo.entity.Categorias;

@Repository
public interface CategoriasRepository extends JpaRepository<Categorias, Long> {
    @Query(
        value = "SELECT * FROM categorias c WHERE c.idCategorias = ?1",
        nativeQuery=true
    )
    Optional<Categorias> findbyCategoriasId(Long idCategorias);
}

