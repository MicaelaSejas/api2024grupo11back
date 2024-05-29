package com.uade.tpo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.uade.tpo.entity.Product;

@Repository
public interface ProductoRepository extends JpaRepository<Product, Long> {
    @Query(
        value = "SELECT * FROM productos p WHERE p.id = ?1",
        nativeQuery=true
    )
    Optional<Product> findbyProductosId(Long id);
}
