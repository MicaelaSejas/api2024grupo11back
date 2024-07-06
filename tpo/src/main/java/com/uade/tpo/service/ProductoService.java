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
import com.uade.tpo.request.ProductoRequest;

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

    public Optional<Producto> obtenerProductoPorId(Integer id) {
        return productoRepository.findById(id);
    }

    public Producto guardarProducto(Producto producto) {
        if (producto.getCategoria() != null) {
            Categoria categoria = categoriaRepository.findById(producto.getCategoria().getId())
                    .orElseThrow(() -> new NoSuchElementException("No se encontró la categoría con ID: " + producto.getCategoria().getId()));
            producto.setCategoria(categoria);
        }
        if (producto.getDescuento() != null) {
            Descuento descuento = descuentoRepository.findById(producto.getDescuento().getId())
                    .orElseThrow(() -> new NoSuchElementException("No se encontró el descuento con ID: " + producto.getDescuento().getId()));
            producto.setDescuento(descuento);
        }

        return productoRepository.save(producto);
    }


    public Producto actualizarProducto(Integer id, ProductoRequest request) {
        Optional<Producto> optionalProducto = productoRepository.findById(id);
        if (optionalProducto.isPresent()) {
            Producto productoExistente = optionalProducto.get();
            
            productoExistente.setTitulo(request.getTitulo());
            productoExistente.setDescripcion(request.getDescripcion());
            productoExistente.setPrecio(request.getPrecio());
            productoExistente.setCantidad(request.getCantidad());

            
            if (request.getIdCategoria() != null) {
                Categoria categoria = categoriaRepository.findById(request.getIdCategoria())
                        .orElseThrow(() -> new NoSuchElementException("No se encontró la categoría con ID: " + request.getIdCategoria()));
                productoExistente.setCategoria(categoria);
            }

            
            if (request.getIdDescuento() != null) {
                Descuento descuento = descuentoRepository.findById(request.getIdDescuento())
                        .orElseThrow(() -> new NoSuchElementException("No se encontró el descuento con ID: " + request.getIdDescuento()));
                productoExistente.setDescuento(descuento);
            }

            
            if (request.getImagen_1_URL() != null && !request.getImagen_1_URL().isEmpty()) {
                productoExistente.setImagen_1_URL(request.getImagen_1_URL());
            }

            if (request.getImagen_2_URL() != null && !request.getImagen_2_URL().isEmpty()) {
                productoExistente.setImagen_2_URL(request.getImagen_2_URL());
            }

            return productoRepository.save(productoExistente);
        } else {
            throw new NoSuchElementException("No se encontró el producto con ID: " + id);
        }
    }



    public void eliminarProducto(Integer id) {
        productoRepository.deleteById(id);
    }

    public int getCantidadDisponibleEnStock(Integer idProducto) {
        Optional<Producto> optionalProducto = productoRepository.findById(idProducto);
        if (optionalProducto.isPresent()) {
            Producto producto = optionalProducto.get();
            return producto.getCantidad();
        } else {
            throw new NoSuchElementException("No se encontró el producto con ID: " + idProducto);
        }
    }

    public boolean verificarStockDisponible(Integer idProducto, Integer cantidadRequerida) {
        Optional<Producto> optionalProducto = productoRepository.findById(idProducto);
        if (optionalProducto.isPresent()) {
            Producto producto = optionalProducto.get();
            return producto.getCantidad() >= cantidadRequerida;
        } else {
            throw new NoSuchElementException("No se encontró el producto con ID: " + idProducto);
        }
    }


    public List<Producto> getProductosByDescuentoId(Integer idDescuento) {
        Optional<Descuento> descuento = descuentoRepository.findById(idDescuento);
        if (descuento.isPresent()) {
            return descuento.get().getProductos();
        } else {
            throw new NoSuchElementException("No se encontró el descuento con ID: " + idDescuento);
        }
    }

    public List<Producto> getProductosByCategoriaId(Integer idDCategoria) {
        Optional<Categoria> categoria = categoriaRepository.findById(idDCategoria);
        if (categoria.isPresent()) {
            return categoria.get().getProductos();
        } else {
            throw new NoSuchElementException("No se encontró la categoria con ID: " + idDCategoria);
        }
    }

}
