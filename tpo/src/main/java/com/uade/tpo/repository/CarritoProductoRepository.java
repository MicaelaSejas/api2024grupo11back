package com.uade.tpo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uade.tpo.entity.Carrito;
import com.uade.tpo.entity.CarritoProducto;
import com.uade.tpo.entity.Producto;

@Repository
public interface CarritoProductoRepository extends JpaRepository<CarritoProducto, Long> {

    Optional<CarritoProducto> findByProducto(Producto producto);

    Optional<CarritoProducto> findByCarrito(Carrito carrito);
}
