package com.uade.tpo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uade.tpo.entity.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
}