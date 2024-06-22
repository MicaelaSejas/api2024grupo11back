package com.uade.tpo.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.controller.request.ProductoForm;
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
    private ProductoService productoService;

    @Autowired
    private DescuentoService descuentoService;

    @Autowired
    private CategoriaService categoriaService;

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
        productoRequest.setDescripcion(producto.getDescripcion());
        productoRequest.setImagen_1(producto.getImagen_1());
        productoRequest.setImagen_2(producto.getImagen_2());
        productoRequest.setPrecio(producto.getPrecio());
        productoRequest.setCantidad(producto.getCantidad());

        
        if (producto.getIdCategoria() != null) {
            productoRequest.setIdCategoria(producto.getIdCategoria());
        }

        
        if (producto.getIdDescuento() != null) {
            productoRequest.setIdDescuento(producto.getIdDescuento());
        }

        return productoRequest;
    }

    private Producto convertToProductoEntity(ProductoRequest productoRequest) {
        Producto producto = new Producto();
        producto.setId(productoRequest.getId());
        producto.setTitulo(productoRequest.getTitulo());
        producto.setDescripcion(productoRequest.getDescripcion());
        producto.setImagen_1(productoRequest.getImagen_1());
        producto.setImagen_2(productoRequest.getImagen_2());
        producto.setPrecio(productoRequest.getPrecio());
        producto.setCantidad(productoRequest.getCantidad());

        if (productoRequest.getIdCategoria() != null) {
            Categoria categoria = categoriaService.findCategoriaById(productoRequest.getIdCategoria())
                    .orElseThrow(() -> new NoSuchElementException("No se encontró la categoría con ID: " + productoRequest.getIdCategoria()));
            producto.setIdCategoria(categoria.getId());
        }
        if (productoRequest.getIdDescuento() != null) {
            Descuento descuento = descuentoService.obtenerDescuentoPorId(productoRequest.getIdDescuento())
                    .orElseThrow(() -> new NoSuchElementException("No se encontró el descuento con ID: " + productoRequest.getIdDescuento()));
            producto.setIdDescuento(descuento.getId());
        }
        return producto;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoRequest> getProductoById(@PathVariable Long id) {
        Optional<Producto> optionalProducto = productoService.getProductoById(id);
        if (optionalProducto.isPresent()) {
            Producto producto = optionalProducto.get();
            ProductoRequest productoRequest = convertToProductoRequest(producto);
            return ResponseEntity.ok(productoRequest);
        } else {
            throw new NoSuchElementException("No se encontró el producto con ID: " + id);
        }
    }

    @PostMapping
    public ResponseEntity<ProductoRequest> createProducto(@ModelAttribute ProductoForm productoForm) {
        System.out.println("Recibiendo solicitud para crear un nuevo producto...");
    
        
        System.out.println("Titulo: " + productoForm.getTitulo());
        System.out.println("Descripción: " + productoForm.getDescripcion());
        System.out.println("Precio: " + productoForm.getPrecio());
        System.out.println("Cantidad: " + productoForm.getCantidad());
        System.out.println("ID Categoría: " + productoForm.getIdCategoria());
        System.out.println("ID Descuento: " + productoForm.getIdDescuento());
    
        Producto producto = new Producto();
        producto.setTitulo(productoForm.getTitulo());
        producto.setDescripcion(productoForm.getDescripcion());
    
        
        try {
            if (productoForm.getImagen_1() != null && !productoForm.getImagen_1().isEmpty()) {
                producto.setImagen_1(productoForm.getImagen_1().getBytes());
            }
            if (productoForm.getImagen_2() != null && !productoForm.getImagen_2().isEmpty()) {
                producto.setImagen_2(productoForm.getImagen_2().getBytes());
            }
        } catch (IOException e) {
            System.err.println("Error al procesar imágenes: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    
        producto.setPrecio(productoForm.getPrecio());
        producto.setCantidad(productoForm.getCantidad());
    
        
        if (productoForm.getIdCategoria() != null) {
            System.out.println("Buscando categoría con ID: " + productoForm.getIdCategoria());
            Categoria categoria = categoriaService.findCategoriaById(productoForm.getIdCategoria())
                    .orElseThrow(() -> new NoSuchElementException("No se encontró la categoría con ID: " + productoForm.getIdCategoria()));
            producto.setIdCategoria(categoria.getId());
        }
        if (productoForm.getIdDescuento() != null) {
            System.out.println("Buscando descuento con ID: " + productoForm.getIdDescuento());
            Descuento descuento = descuentoService.obtenerDescuentoPorId(productoForm.getIdDescuento())
                    .orElseThrow(() -> new NoSuchElementException("No se encontró el descuento con ID: " + productoForm.getIdDescuento()));
            producto.setIdDescuento(descuento.getId());
        }
    
        
        System.out.println("Guardando el producto en la base de datos...");
        Producto savedProducto = productoService.saveProducto(producto);
        System.out.println("Producto guardado exitosamente");
    
        
        ProductoRequest productoRequest = convertToProductoRequest(savedProducto);
        return new ResponseEntity<>(productoRequest, HttpStatus.CREATED);
    }
    
    

    @PutMapping("/{id}")
    public ResponseEntity<ProductoRequest> updateProducto(@PathVariable Long id, @ModelAttribute ProductoForm productoForm) {
        Producto existingProducto = productoService.getProductoById(id)
                .orElseThrow(() -> new NoSuchElementException("No se encontró el producto con ID: " + id));

        existingProducto.setTitulo(productoForm.getTitulo());
        existingProducto.setDescripcion(productoForm.getDescripcion());

        
        try {
            if (productoForm.getImagen_1() != null && !productoForm.getImagen_1().isEmpty()) {
                existingProducto.setImagen_1(productoForm.getImagen_1().getBytes());
            }
            if (productoForm.getImagen_2() != null && !productoForm.getImagen_2().isEmpty()) {
                existingProducto.setImagen_2(productoForm.getImagen_2().getBytes());
            }
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        existingProducto.setPrecio(productoForm.getPrecio());
        existingProducto.setCantidad(productoForm.getCantidad());

        
        if (productoForm.getIdCategoria() != null) {
            Categoria categoria = categoriaService.findCategoriaById(productoForm.getIdCategoria())
                    .orElseThrow(() -> new NoSuchElementException("No se encontró la categoría con ID: " + productoForm.getIdCategoria()));
            existingProducto.setIdCategoria(categoria.getId());
        } else {
            existingProducto.setIdCategoria(null);
        }

        if (productoForm.getIdDescuento() != null) {
            Descuento descuento = descuentoService.obtenerDescuentoPorId(productoForm.getIdDescuento())
                    .orElseThrow(() -> new NoSuchElementException("No se encontró el descuento con ID: " + productoForm.getIdDescuento()));
            existingProducto.setIdDescuento(descuento.getId());
        } else {
            existingProducto.setIdDescuento(null);
        }

        Producto updatedProducto = productoService.saveProducto(existingProducto);
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
