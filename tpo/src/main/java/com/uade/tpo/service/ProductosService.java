package com.uade.tpo.service;

import com.uade.tpo.entity.Productos;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface ProductosService {

    public Page<Productos> getProductos(PageRequest pageRequest);

    public Optional<Productos> getProductosById(Integer id);

    public Productos crearProductos(Integer id);

    public List<Productos> getAllProductos();

    public Productos eliminarProductos(Integer id);

    public Productos modificarProductos(Integer id);

}