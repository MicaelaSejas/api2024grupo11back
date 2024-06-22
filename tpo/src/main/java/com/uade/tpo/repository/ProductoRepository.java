package com.uade.tpo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uade.tpo.entity.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    List<Producto> findByIdDescuento(Long idDescuento);
    List<Producto> findByIdCategoria(Long idCategoria);

}
