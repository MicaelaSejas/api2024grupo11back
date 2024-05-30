package com.uade.tpo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.uade.tpo.entity.Productos;

@Repository
public interface ProductosRepository extends JpaRepository<Productos, Long> {
    @Query(
        value = "SELECT * FROM productos p WHERE p.idProductos = ?1",
        nativeQuery=true
    )
    Optional<Productos> findbyProductosId(Long idProductos);
}

