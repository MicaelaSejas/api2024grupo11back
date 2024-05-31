package com.uade.tpo.controller;

import java.net.URI;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.controller.request.ProductosRequest;
import com.uade.tpo.entity.Categoria;
import com.uade.tpo.entity.Descuento;
import com.uade.tpo.entity.Producto;
import com.uade.tpo.service.CategoriasService;
import com.uade.tpo.service.DescuentosService;
import com.uade.tpo.service.ProductosService;

@RestController
@RequestMapping("productos")
public class ProductosController {

    @Autowired
    private ProductosService productosService;

    @Autowired
    private CategoriasService categoriasService;

    @Autowired
    private DescuentosService descuentosService;

    @GetMapping
    public ResponseEntity<Page<Producto>> getProductos(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {
        if (page == null || size == null)
            return ResponseEntity.ok(productosService.getProductos(PageRequest.of(0, Integer.MAX_VALUE)));
        return ResponseEntity.ok(productosService.getProductos(PageRequest.of(page, size)));
    }

    @GetMapping("/{idProductos}")
    public ResponseEntity<Producto> getProductosById(@PathVariable Long idProductos) {
        Optional<Producto> result = productosService.getProductosById(idProductos);
        if (result.isPresent())
            return ResponseEntity.ok(result.get());

        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Object> crearProductos(@RequestBody ProductosRequest productosRequest){
        Producto nuevoProductos = new Producto();
        nuevoProductos.setTitulo(productosRequest.getTitulo());
        nuevoProductos.setImagen_1(productosRequest.getImagen_1());
        nuevoProductos.setImagen_2(productosRequest.getImagen_2());
        nuevoProductos.setDescripcion(productosRequest.getDescripcion());
        nuevoProductos.setPrecio(productosRequest.getPrecio());
        nuevoProductos.setCantidad(productosRequest.getCantidad());
        long idCategorias = productosRequest.getIdCategoria();
        Categoria categorias = categoriasService.getCategoriasById(idCategorias).orElse(null);
        if(categorias != null) {
        nuevoProductos.setIdCategoria(categorias);
        }else{
        return ResponseEntity.notFound().build();
        }
        long idDescuentos = productosRequest.getIdDescuento();
        Descuento descuentos = descuentosService.getDescuentosById(idDescuentos).orElse(null);
        if(descuentos != null) {
        nuevoProductos.setIdDescuento(descuentos);
        }else{
        return ResponseEntity.notFound().build();
        }
        Producto result = productosService.crearProductos(nuevoProductos);
        return ResponseEntity.created(URI.create("/productos/" + result.getIdProductos())).body(result);
    }

    @PutMapping("/{idProductos}")
    public ResponseEntity<Object> actualizarProductos(@PathVariable Long idProductos, @RequestBody ProductosRequest productosRequest) {
        Optional<Producto> productosOptional = productosService.getProductosById(idProductos);
        if (productosOptional.isPresent()) {
            Producto productoExistente = productosOptional.get();
            productoExistente.setTitulo(productosRequest.getTitulo());
            productoExistente.setImagen_1(productosRequest.getImagen_1());
            productoExistente.setImagen_2(productosRequest.getImagen_2());
            productoExistente.setDescripcion(productosRequest.getDescripcion());
            productoExistente.setPrecio(productosRequest.getPrecio());
            productoExistente.setCantidad(productosRequest.getCantidad());
            long idCategorias = productosRequest.getIdCategoria();
            Categoria categorias = categoriasService.getCategoriasById(idCategorias).orElse(null);
            if(categorias != null) {
            productoExistente.setIdCategoria(categorias);
            }else{
            return ResponseEntity.notFound().build();
            }
            long idDescuentos = productosRequest.getIdDescuento();
            Descuento descuentos = descuentosService.getDescuentosById(idDescuentos).orElse(null);
            if(descuentos != null) {
            productoExistente.setIdDescuento(descuentos);
            }else{
            return ResponseEntity.notFound().build();
            }
            productosService.actualizarProductos(idProductos, productoExistente);
            return ResponseEntity.ok(productoExistente);
        }
        return ResponseEntity.notFound().build();
    }    

    @DeleteMapping("/{idProductos}")
    public ResponseEntity<Object> eliminarProductos(@PathVariable Long idProductos) {
        try {
            productosService.eliminarProductos(idProductos);
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
