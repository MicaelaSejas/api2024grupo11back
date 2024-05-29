package com.uade.tpo.controller;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.controller.request.ProductosRequest;
import com.uade.tpo.entity.Categorias;
import com.uade.tpo.entity.Descuentos;
import com.uade.tpo.entity.Productos;
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
    public ResponseEntity<Page<Productos>> getProductos(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {
        if (page == null || size == null)
            return ResponseEntity.ok(productosService.getProductos(PageRequest.of(0, Integer.MAX_VALUE)));
        return ResponseEntity.ok(productosService.getProductos(PageRequest.of(page, size)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Productos> getProductosById(@PathVariable Long id) {
        Optional<Productos> result = productosService.getProductosById(id);
        if (result.isPresent())
            return ResponseEntity.ok(result.get());

        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Object> crearProductos(@RequestBody ProductosRequest productosRequest){
        Productos nuevoProductos = new Productos();
        nuevoProductos.setTitulo(productosRequest.getTitulo());
        nuevoProductos.setCategoria(productosRequest.getCategoria());
        nuevoProductos.setImagen_1(productosRequest.getImagen_1());
        nuevoProductos.setImagen_2(productosRequest.getImagen_2());
        nuevoProductos.setDescripcion(productosRequest.getDescripcion());
        nuevoProductos.setPrecio(productosRequest.getPrecio());
        nuevoProductos.setCantidad(productosRequest.getCantidad());
        long idCategorias = productosRequest.getIdCategoria();
        Categorias categorias = categoriasService.getCategoriasById(idCategorias).orElse(null);
        if(categorias != null) {
        nuevoProductos.setidCategoria(categorias);
        }else{
        return ResponseEntity.notFound().build();
        }
        long idDescuentos = productosRequest.getIdDescuento();
        Descuentos descuentos = descuentosService.getDescuentosById(idDescuentos).orElse(null);
        if(descuentos != null) {
        nuevoProductos.setidDescuento(descuentos);
        }else{
        return ResponseEntity.notFound().build();
        }
        Productos result = productosService.crearProductos(nuevoProductos);
        return ResponseEntity.created(URI.create("/productos/" + result.getId())).body(result);
    }
    
}
