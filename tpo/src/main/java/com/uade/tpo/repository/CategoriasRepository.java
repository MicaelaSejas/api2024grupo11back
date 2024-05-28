package com.uade.tpo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.uade.tpo.entity.Categorias;

@Repository
public interface CategoriasRepository extends JpaRepository<Categorias, Integer> {
    Optional<Categorias> getCategoriasById(Integer idCategorias);
}
