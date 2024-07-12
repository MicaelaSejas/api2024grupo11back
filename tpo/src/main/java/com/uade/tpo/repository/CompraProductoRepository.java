package com.uade.tpo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uade.tpo.entity.Compra;
import com.uade.tpo.entity.CompraProducto;
import com.uade.tpo.entity.Producto;

@Repository
public interface CompraProductoRepository extends JpaRepository<CompraProducto, Long> {

    Optional<CompraProducto> findByProducto(Producto producto);

    Optional<CompraProducto> findByCompra(Compra compra);
}