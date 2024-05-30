package com.uade.tpo.service;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.uade.tpo.entity.Productos;

public interface ProductosService {

    public Page<Productos> getProductos(Pageable pageable);

    public Optional<Productos> getProductosById(Long idProductos);

    public Productos crearProductos(Productos productos);

    public Productos actualizarProductos(Long idProductos, Productos productosActualizados);

    public Productos eliminarProductos(Long idProductos);

}
