package com.uade.tpo.service;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.uade.tpo.entity.Productos;

public interface ProductosService {

    public Page<Productos> getProductos(Pageable pageable);

    public Optional<Productos> getProductosById(Long id);

    public Productos crearProductos(Productos productos);

    public List<Productos> getAllProductos();

    public Productos eliminarProductos(Long id);

    public Productos actualizarProductos(Long id, Productos productos);

}
