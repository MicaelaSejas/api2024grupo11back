package com.uade.tpo.service;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.uade.tpo.entity.Producto;

public interface ProductosService {

    public Page<Producto> getProductos(Pageable pageable);

    public Optional<Producto> getProductosById(Long idProductos);

    public Producto crearProductos(Producto productos);

    public Producto actualizarProductos(Long idProductos, Producto productosActualizados);

    public Producto eliminarProductos(Long idProductos);

}
