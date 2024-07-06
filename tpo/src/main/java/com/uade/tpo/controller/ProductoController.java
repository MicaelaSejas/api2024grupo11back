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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.controller.request.ProductoRequest;
import com.uade.tpo.entity.Categoria;
import com.uade.tpo.entity.Descuento;
import com.uade.tpo.entity.Producto;
import com.uade.tpo.repository.CategoriaRepository;
import com.uade.tpo.repository.DescuentoRepository;
import com.uade.tpo.service.ProductoService;

@RestController
@RequestMapping("/api/v1/producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private DescuentoRepository descuentoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public ResponseEntity<List<Producto>> getAllProductos() {
        List<Producto> productos = productoService.getAllProductos();
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerProductoPorId(@PathVariable("id") Long id) {
        Optional<Producto> producto = productoService.obtenerProductoPorId(id);
        return producto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable("id") Long id) {
        productoService.eliminarProducto(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping
    public ResponseEntity<Producto> guardarProducto(@RequestBody ProductoRequest request) {
        try {
            Producto producto = convertirAProducto(request);
            Producto productoGuardado = productoService.guardarProducto(producto);
            return ResponseEntity.status(HttpStatus.CREATED).body(productoGuardado);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                 .body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable("id") Long id,
                                                       @RequestBody ProductoRequest request) {
        try {
            Producto producto = convertirAProducto(request);
            producto.setId(id);
            Producto productoActualizado = productoService.guardarProducto(producto);
            return ResponseEntity.ok(productoActualizado);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                 .body(null);
        }
    }

    private Producto convertirAProducto(ProductoRequest request) {
        Producto producto = new Producto();
        producto.setTitulo(request.getTitulo());
        producto.setDescripcion(request.getDescripcion());
        producto.setPrecio(request.getPrecio());
        producto.setCantidad(request.getCantidad());
        producto.setImagen_1_URL(request.getImagen_1_URL());
        producto.setImagen_2_URL(request.getImagen_2_URL());

        if (request.getIdCategoria() != 0) {
            Categoria categoria = categoriaRepository.findById(request.getIdCategoria())
                    .orElseThrow(() -> new NoSuchElementException("No se encontró la categoría con ID: " + request.getIdCategoria()));
            producto.setCategoria(categoria);
        }

        if (request.getIdDescuento() != null && request.getIdDescuento() != 0) {
            Descuento descuento = descuentoRepository.findById(request.getIdDescuento())
                    .orElseThrow(() -> new NoSuchElementException("No se encontró el descuento con ID: " + request.getIdDescuento()));
            producto.setDescuento(descuento);
        }

        return producto;
    }


    @GetMapping("/{id}/stock")
    public ResponseEntity<Integer> getCantidadDisponibleEnStock(@PathVariable("id") Long idProducto) {
        int cantidadDisponible = productoService.getCantidadDisponibleEnStock(idProducto);
        return ResponseEntity.ok(cantidadDisponible);
    }
    


    @GetMapping("/{id}/verificarStock")
    public ResponseEntity<Boolean> verificarStockDisponible(@PathVariable("id") Long idProducto, @RequestParam("cantidad") Integer cantidadRequerida) {
        boolean stockSuficiente = productoService.verificarStockDisponible(idProducto, cantidadRequerida);
        return ResponseEntity.ok(stockSuficiente);
    }
 
    @GetMapping("/categoria/{idCategoria}")
    public ResponseEntity<List<Producto>> getProductosByCategoriaId(@PathVariable("idCategoria") Integer idCategoria) {
        List<Producto> productos = productoService.getProductosByCategoriaId(idCategoria);
        return ResponseEntity.ok(productos);
    }
 

    @GetMapping("/descuento/{idDescuento}")
    public ResponseEntity<List<Producto>> getProductosByDescuentoId(@PathVariable("idDescuento") Integer idDescuento) {
        List<Producto> productos = productoService.getProductosByDescuentoId(idDescuento);
        return ResponseEntity.ok(productos);
    }
    

}
