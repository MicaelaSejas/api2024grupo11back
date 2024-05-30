package com.uade.tpo.service;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.uade.tpo.entity.Productos;
import com.uade.tpo.repository.ProductosRepository;

@Service
public class ProductosServiceImplement implements ProductosService {

    @Autowired
    private ProductosRepository productosRepository;

    @Override
    public Page<Productos> getProductos(Pageable pageable) {
        return productosRepository.findAll(pageable);
    }

    @Override
    public Optional<Productos> getProductosById(Long idProductos) {
        return productosRepository.findById(idProductos);
    }

    @Override
    public Productos crearProductos(Productos productos) {
        return productosRepository.save(productos);
    }

    @Override
    public Productos actualizarProductos(Long idProductos, Productos productosActualizados) {
        productosActualizados.setIdProductos(idProductos);
        return productosRepository.save(productosActualizados);
    }

    @Override
    public Productos eliminarProductos(Long idProductos) {
        Optional<Productos> productoOptional = productosRepository.findById(idProductos);
        if(productoOptional.isPresent()){
            Productos productoEliminado = productoOptional.get();
            productosRepository.deleteById(idProductos);
            return productoEliminado;
        }else{
            throw new NoSuchElementException("El producto no existe");
        }
    }

}
