package com.uade.tpo.controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<List<Producto>> getAllProductos() {
        List<Producto> productos = productoService.findAllProductos();
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> getProductoById(@PathVariable("id") Long id) {
        Optional<Producto> optionalProducto = productoService.findProductoById(id);
        return optionalProducto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Producto> createProducto(@RequestBody Producto producto) {
        Producto nuevoProducto = productoService.saveProducto(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoProducto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> updateProducto(@PathVariable("id") Long id, @RequestBody Producto producto) {
        try {
            Producto productoActualizado = productoService.updateProducto(id, producto);
            return ResponseEntity.ok(productoActualizado);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducto(@PathVariable("id") Long id) {
        productoService.deleteProducto(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/descuento/{idDescuento}")
    public ResponseEntity<Producto> getProductoByDescuento(@PathVariable("idDescuento") Long idDescuento) {
        Optional<Descuento> optionalDescuento = descuentoService.obtenerDescuentoPorId(idDescuento);
        if (optionalDescuento.isPresent()) {
            Optional<Producto> optionalProducto = productoService.findProductoByDescuento(optionalDescuento.get());
            return optionalProducto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/categoria/{idCategoria}")
    public ResponseEntity<Producto> getProductosByCategoria(@PathVariable("idCategoria") Long idCategoria) {
        Optional<Categoria> optionalCategoria = categoriaService.findCategoriaById(idCategoria);
        if (optionalCategoria.isPresent()) {
            Optional<Producto> optionalProducto = productoService.findProductoByCategoria(optionalCategoria.get());
            return optionalProducto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    

    @GetMapping("/{idProducto}/stock")
    public ResponseEntity<Integer> getCantidadDisponibleEnStock(@PathVariable("idProducto") Long idProducto) {
        try {
            int cantidadDisponible = productoService.getCantidadDisponibleEnStock(idProducto);
            return ResponseEntity.ok(cantidadDisponible);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{idProducto}/verificarStock/{cantidad}")
    public ResponseEntity<Boolean> verificarStockDisponible(@PathVariable("idProducto") Long idProducto,
                                                            @PathVariable("cantidad") int cantidadRequerida) {
        try {
            boolean stockDisponible = productoService.verificarStockDisponible(idProducto, cantidadRequerida);
            return ResponseEntity.ok(stockDisponible);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
