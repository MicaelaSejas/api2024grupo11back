package com.uade.tpo.controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.controller.request.ProductoRequest;
import com.uade.tpo.entity.Categoria;
import com.uade.tpo.entity.Descuento;
import com.uade.tpo.entity.Producto;
import com.uade.tpo.service.CategoriaService;
import com.uade.tpo.service.DescuentoService;
import com.uade.tpo.service.ProductoService;

@RestController
@RequestMapping("/api/producto")
@CrossOrigin(origins = "http://localhost:3000")

public class ProductoController {

    @Autowired
    private  ProductoService productoService;

    @Autowired
    private  DescuentoService descuentoService;
    
    @Autowired
    private  CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<List<ProductoRequest>> getAllProductos() {
        List<Producto> productos = productoService.getAllProductos();
        List<ProductoRequest> productoRequests = productos.stream()
                .map(this::convertToProductoRequest)
                .collect(Collectors.toList());
        return ResponseEntity.ok(productoRequests);
    }

    private ProductoRequest convertToProductoRequest(Producto producto) {
        ProductoRequest productoRequest = new ProductoRequest();
        productoRequest.setId(producto.getId());
        productoRequest.setTitulo(producto.getTitulo());
        productoRequest.setImagen1Url(producto.getImagen_1_url());
        productoRequest.setImagen2Url(producto.getImagen_2_url());
        productoRequest.setPrecio(producto.getPrecio());
        productoRequest.setCantidad(producto.getCantidad());
        
        if (producto.getCategoria() != null) {
         productoRequest.setIdCategoria(producto.getCategoria().getId());
        }
        if (producto.getDescuento() != null) {
            productoRequest.setIdDescuento(producto.getDescuento().getId());
        }
        return productoRequest;
    }
    
    private Producto convertToProductoEntity(ProductoRequest productoRequest) {
        Producto producto = new Producto();
        producto.setId(productoRequest.getId());
        producto.setTitulo(productoRequest.getTitulo());
        producto.setImagen_1_url(productoRequest.getImagen1Url());
        producto.setImagen_2_url(productoRequest.getImagen2Url());
        producto.setPrecio(productoRequest.getPrecio());
        producto.setCantidad(productoRequest.getCantidad());
    if (productoRequest.getIdCategoria() != null) {
        Categoria categoria = categoriaService.findCategoriaById(productoRequest.getIdCategoria())
                .orElseThrow(() -> new NoSuchElementException("No se encontró la categoría con ID: " + productoRequest.getIdCategoria()));
        producto.setCategoria(categoria);
    }
    if (productoRequest.getIdDescuento() != null) {
        Descuento descuento = descuentoService.obtenerDescuentoPorId(productoRequest.getIdDescuento())
                .orElseThrow(() -> new NoSuchElementException("No se encontró el descuento con ID: " + productoRequest.getIdDescuento()));
        producto.setDescuento(descuento);
    }
        return producto;
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ProductoRequest> getProductoById(@PathVariable Long id) {
        Optional<Producto> optionalProducto = productoService.getProductoById(id);
        if (optionalProducto.isPresent()) {
            Producto producto = optionalProducto.get();
            ProductoRequest productoRequest = convertToProductoRequest(producto); // Método para convertir Producto a ProductoRequest
            return ResponseEntity.ok(productoRequest);
        } else {
            throw new NoSuchElementException("No se encontró el producto con ID: " + id);
        }
    }

    @PostMapping
    public ResponseEntity<Producto> createProducto(@RequestBody Producto producto) {
        Producto savedProducto = productoService.saveProducto(producto);
        return new ResponseEntity<>(savedProducto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoRequest> updateProducto(@PathVariable Long id, @RequestBody ProductoRequest productoRequest) {
        // Convertir ProductoRequest a Producto
        Producto producto = convertToProductoEntity(productoRequest);
    
        // Verificar si el producto existe
        Producto existingProducto = productoService.getProductoById(id)
                .orElseThrow(() -> new NoSuchElementException("No se encontró el producto con ID: " + id));
    
        // Actualizar los campos del producto existente con los nuevos valores del productoRequest
        existingProducto.setTitulo(productoRequest.getTitulo());
        existingProducto.setImagen_1_url(productoRequest.getImagen1Url()); // Corregido
        existingProducto.setImagen_2_url(productoRequest.getImagen2Url()); // Corregido
        existingProducto.setPrecio(productoRequest.getPrecio());
        existingProducto.setCantidad(productoRequest.getCantidad());
        
        // Obtener y establecer la categoría y el descuento desde sus IDs si se proporcionan en el ProductoRequest
        Categoria categoria = null;
        if (productoRequest.getIdCategoria() != null) {
            categoria = categoriaService.findCategoriaById(productoRequest.getIdCategoria())
                    .orElseThrow(() -> new NoSuchElementException("No se encontró la categoría con ID: " + productoRequest.getIdCategoria()));
        }
        existingProducto.setCategoria(categoria);
    
        Descuento descuento = null;
        if (productoRequest.getIdDescuento() != null) {
            descuento = descuentoService.obtenerDescuentoPorId(productoRequest.getIdDescuento())
                    .orElseThrow(() -> new NoSuchElementException("No se encontró el descuento con ID: " + productoRequest.getIdDescuento()));
        }
        existingProducto.setDescuento(descuento);
    
        // Guardar el producto actualizado en la base de datos
        Producto updatedProducto = productoService.saveProducto(existingProducto);
    
        // Convertir el producto actualizado a ProductoRequest y devolverlo en la respuesta
        ProductoRequest updatedProductoRequest = convertToProductoRequest(updatedProducto);
        return ResponseEntity.ok(updatedProductoRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducto(@PathVariable Long id) {
        productoService.deleteProducto(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/categoria/{idCategoria}")
    public ResponseEntity<List<Producto>> getProductosByCategoriaId(@PathVariable Long idCategoria) {
        List<Producto> productos = productoService.getProductosByCategoriaId(idCategoria);
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/descuento/{idDescuento}")
    public ResponseEntity<List<Producto>> getProductosByDescuentoId(@PathVariable Long idDescuento) {
        List<Producto> productos = productoService.getProductosByDescuentoId(idDescuento);
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/stock")
    public ResponseEntity<Boolean> verificarStock(@RequestParam Long idProducto, @RequestParam int cantidad) {
        boolean disponible = productoService.verificarStockDisponible(idProducto, cantidad);
        return ResponseEntity.ok(disponible);
    }
}