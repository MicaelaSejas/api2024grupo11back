package com.uade.tpo.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.tpo.entity.Producto;
import com.uade.tpo.repository.ProductoRepository;

@Service
public class ProductoService {

    @Autowired
    private  ProductoRepository productoRepository;

    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    public Optional<Producto> getProductoById(Long id) {
        return productoRepository.findById(id);
    }

    public Producto saveProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public void deleteProducto(Long id) {
        productoRepository.deleteById(id);
    }

    public int getCantidadDisponibleEnStock(Long idProducto) {
        Optional<Producto> optionalProducto = productoRepository.findById(idProducto);
        if (optionalProducto.isPresent()) {
            Producto producto = optionalProducto.get();
            return producto.getCantidad();
        } else {
            throw new NoSuchElementException("No se encontró el producto con ID: " + idProducto);
        }
    }

    public boolean verificarStockDisponible(Long idProducto, int cantidadRequerida) {
        Optional<Producto> optionalProducto = productoRepository.findById(idProducto);
        if (optionalProducto.isPresent()) {
            Producto producto = optionalProducto.get();
            return producto.getCantidad() >= cantidadRequerida;
        } else {
            throw new NoSuchElementException("No se encontró el producto con ID: " + idProducto);
        }
    }

    public List<Producto> getProductosByDescuentoId(Long idDescuento) {
        return productoRepository.findByDescuentoId(idDescuento);
    }

    public List<Producto> getProductosByCategoriaId(Long idCategoria) {
        return productoRepository.findByCategoriaId(idCategoria);
    }
}
