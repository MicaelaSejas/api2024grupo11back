package com.uade.tpo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.uade.tpo.entity.Descuentos;
import com.uade.tpo.entity.Productos;
import com.uade.tpo.repository.ProductosRepository;

@Service
public class ProductosServiceImplement implements ProductosService {

    @Autowired
    private ProductosRepository productosRepository;

    @Override
    public Page<Productos> getProductos(PageRequest pageable) {
        return productosRepository.findAll(pageable);
        throw new UnsupportedOperationException("Unimplemented method 'getProductos'");
    }

    @Override
    public Optional<Productos> getProductosById(Integer id) {
        return productosRepository.findById(id).orElse(null);
        throw new UnsupportedOperationException("Unimplemented method 'getProductosById'");
    }

    @Override
    public Productos crearProductos(Integer id) {
        List<Productos> productos = productosRepository.findById(id);
        if (productos.isEmpty())
            return productosRepository.save(new Productos(id));
        throw new UnsupportedOperationException("Unimplemented method 'crearProductos'");
    }

    @Override
    public List<Productos> getAllProductos( {
        return productosRepository.findAll();
        throw new UnsupportedOperationException("Unimplemented method 'getAllProductos'");
    }

    @Override
    public Productos eliminarProductos(Integer id) {
        productosRepository.deleteById(id);
        throw new UnsupportedOperationException("Unimplemented method 'eliminarProductos'");
    }

    @Override
    public Productos modificarProductos(Integer id) {
        return productosRepository.save(id);
        throw new UnsupportedOperationException("Unimplemented method 'modificarProductos'");
    }

}
