package com.uade.tpo.service;

import java.util.List;
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
    public Optional<Productos> getProductosById(Long id) {
        return productosRepository.findById(id);
    }

    @Override
    public Productos crearProductos(Productos productos) {
        Optional<Productos> listaProductos = productosRepository.findById(productos.getId());
        if (listaProductos.isEmpty()){
            return productosRepository.save(productos);
        }else{
            throw new IllegalStateException("El producto ya existe");
        }
    }

    @Override
    public List<Productos> getAllProductos() {
        return productosRepository.findAll();
    }

    @Override
    public Productos eliminarProductos(Long id) {
        Optional<Productos> productoOptional = productosRepository.findById(id);
        if(productoOptional.isPresent()){
            Productos productoEliminado = productoOptional.get();
            productosRepository.deleteById(id);
            return productoEliminado;
        }else{
            throw new NoSuchElementException("El producto no existe");
        }
    }

    @Override
    public Productos actualizarProductos(Long id, Productos productosActualizados) {
        productosActualizados.setId(id);
        return productosRepository.save(productosActualizados);
    }


}
