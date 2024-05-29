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

import com.uade.tpo.entity.Categoria;
import com.uade.tpo.entity.Descuento;
import com.uade.tpo.entity.Product;
import com.uade.tpo.entity.dto.ProductRequest;
import com.uade.tpo.service.CategoriaService;
import com.uade.tpo.service.DescuentoService;
import com.uade.tpo.service.ProductoService;

@RestController
@RequestMapping("products")
public class ProductoController {
    
    @Autowired
    private ProductoService productoService;

    @Autowired
    private DescuentoService descuentoService;

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<Page<Product>> getProducts(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {
        if (page == null || size == null)
            return ResponseEntity.ok(productoService.getProducts(PageRequest.of(0, Integer.MAX_VALUE)));
        return ResponseEntity.ok(productoService.getProducts(PageRequest.of(page, size)));
    }

    @GetMapping("/{productoId}")
    public ResponseEntity<Product> getProductById(@PathVariable Long productId) {
        Optional<Product> result = productoService.getProductById(productId);
        if (result.isPresent())
            return ResponseEntity.ok(result.get());

        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Object> createProduct(@RequestBody ProductRequest productRequest){
        Product nuevoProductos = new Product();
        nuevoProductos.setTitulo(productRequest.getTitulo());
        
        nuevoProductos.setDescripcion(productRequest.getDescripcion());
        nuevoProductos.setPrecio(productRequest.getPrecio());
        nuevoProductos.setCantidad(productRequest.getCantidad());
        long idCategorias = productRequest.getIdCategoria();
        Categoria categoria = categoriaService.getCategoriaById(idCategorias).orElse(null);
        if(categoria != null) {
        nuevoProductos.setidCategoria(categoria);
        }else{
        return ResponseEntity.notFound().build();
        }
        long idDescuentos = productRequest.getIdDescuento();
        Descuento descuento = descuentoService.getDescuentoById(idDescuentos).orElse(null);
        if(descuento != null) {
        nuevoProductos.setidDescuento(descuento);
        }else{
        return ResponseEntity.notFound().build();
        }
        Product result = productoService.createProduct(nuevoProductos);
        return ResponseEntity.created(URI.create("/producto/" + result.getId())).body(result);
    }
}
