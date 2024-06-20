package com.uade.tpo.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.tpo.entity.Categoria;
import com.uade.tpo.entity.Descuento;
import com.uade.tpo.entity.Producto;
import com.uade.tpo.repository.ProductoRepository;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> findAllProductos() {
        return productoRepository.findAll();
    }

    public Optional<Producto> findProductoById(Long id) {
        return productoRepository.findById(id);
    }

    public Producto saveProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public Producto updateProducto(Long id, Producto productoNuevo) {
        Optional<Producto> optionalProducto = productoRepository.findById(id);
        if (optionalProducto.isPresent()) {
            Producto productoExistente = optionalProducto.get();
            productoExistente.setTitulo(productoNuevo.getTitulo());
            productoExistente.setImagen_1(productoNuevo.getImagen_1());
            productoExistente.setImagen_2(productoNuevo.getImagen_2());
            productoExistente.setImagen_3_url(productoNuevo.getImagen_3_url());
            productoExistente.setImagen_4_url(productoNuevo.getImagen_4_url());
            productoExistente.setPrecio(productoNuevo.getPrecio());
            productoExistente.setCantidad(productoNuevo.getCantidad());
            productoExistente.setCategoria(productoNuevo.getCategoria());
            productoExistente.setDescuento(productoNuevo.getDescuento());
            return productoRepository.save(productoExistente);
        } else {
            throw new NoSuchElementException("No se encontró el producto con ID: " + id);
        }
    }

    public void deleteProducto(Long id) {
        productoRepository.deleteById(id);
    }

    public Optional<Producto> findProductoByDescuento(Descuento descuento) {
        return productoRepository.findByIdDescuento(descuento);
    }

    public Optional<Producto> findProductoByCategoria(Categoria categoria) {
        return productoRepository.findByIdCategoria(categoria);
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

    
}
