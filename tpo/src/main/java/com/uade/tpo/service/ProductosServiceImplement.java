package com.uade.tpo.service;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.uade.tpo.entity.Producto;
import com.uade.tpo.repository.ProductosRepository;

@Service
public class ProductosServiceImplement implements ProductosService {

    @Autowired
    private ProductosRepository productosRepository;

    @Override
    public Page<Producto> getProductos(Pageable pageable) {
        return productosRepository.findAll(pageable);
    }

    @Override
    public Optional<Producto> getProductosById(Long idProductos) {
        return productosRepository.findById(idProductos);
    }

    @Override
    public Producto crearProductos(Producto productos) {
        return productosRepository.save(productos);
    }

    @Override
    public Producto actualizarProductos(Long idProductos, Producto productosActualizados) {
        productosActualizados.setIdProductos(idProductos);
        return productosRepository.save(productosActualizados);
    }

    @Override
    public Producto eliminarProductos(Long idProductos) {
        Optional<Producto> productoOptional = productosRepository.findById(idProductos);
        if(productoOptional.isPresent()){
            Producto productoEliminado = productoOptional.get();
            productosRepository.deleteById(idProductos);
            return productoEliminado;
        }else{
            throw new NoSuchElementException("El producto no existe");
        }
    }

}
