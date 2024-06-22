package com.uade.tpo.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.tpo.entity.Categoria;
import com.uade.tpo.entity.Descuento;
import com.uade.tpo.entity.Producto;
import com.uade.tpo.repository.CategoriaRepository;
import com.uade.tpo.repository.DescuentoRepository;
import com.uade.tpo.repository.ProductoRepository;

@Service
public class ProductoService {

    @Autowired
    private  ProductoRepository productoRepository;

        @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private DescuentoRepository descuentoRepository;

    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    public Optional<Producto> getProductoById(Long id) {
        return productoRepository.findById(id);
    }

    public Producto saveProducto(Producto producto) {
        
        if (producto.getIdCategoria() != null) {
            Categoria categoria = categoriaRepository.findById(producto.getIdCategoria())
                    .orElseThrow(() -> new NoSuchElementException("No se encontró la categoría con ID: " + producto.getIdCategoria()));
            producto.setIdCategoria(categoria.getId());
        }
    
        
        if (producto.getIdDescuento() != null) {
            Descuento descuento = descuentoRepository.findById(producto.getIdDescuento())
                    .orElseThrow(() -> new NoSuchElementException("No se encontró el descuento con ID: " + producto.getIdDescuento()));
            producto.setIdDescuento(descuento.getId());
        }
    
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
        return productoRepository.findByIdDescuento(idDescuento);
    }

    public List<Producto> getProductosByCategoriaId(Long idCategoria) {
        return productoRepository.findByIdCategoria(idCategoria);
    }
}
