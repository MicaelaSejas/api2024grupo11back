package com.uade.tpo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.uade.tpo.entity.Productos;

@Repository
public interface ProductosRepository extends JpaRepository<Productos, Integer> {
    Optional<Productos> getProductosById(Integer id);
}
