package com.uade.tpo.controller;

import java.util.List;
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

import com.uade.tpo.entity.Compra;
import com.uade.tpo.entity.CompraProducto;
import com.uade.tpo.entity.Producto;
import com.uade.tpo.exception.CompraProductoNotFoundException;
import com.uade.tpo.service.CompraProductoService;
import com.uade.tpo.service.CompraService;
import com.uade.tpo.service.ProductoService;

@RestController
@RequestMapping("/api/compraProducto")
@CrossOrigin(origins = "http://localhost:3000")
public class CompraProductoController {

    @Autowired
    private CompraProductoService compraProductoService;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private CompraService compraService;

    @GetMapping
    public ResponseEntity<List<CompraProducto>> getAllCompraProductos() {
        List<CompraProducto> compraProductos = compraProductoService.findAllCompraProducto();
        return ResponseEntity.ok(compraProductos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompraProducto> getCompraProductoById(@PathVariable("id") Long id) {
        Optional<CompraProducto> optionalCompraProducto = compraProductoService.findCompraProductoById(id);
        return optionalCompraProducto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CompraProducto> createCompraProducto(@RequestBody CompraProducto compraProducto) {
        CompraProducto nuevoCompraProducto = compraProductoService.saveCompraProducto(compraProducto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCompraProducto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompraProducto> updateCompraProducto(@PathVariable("id") Long id, @RequestBody CompraProducto compraProducto) {
        try {
            CompraProducto compraProductoActualizado = compraProductoService.updateCompraProducto(id, compraProducto);
            return ResponseEntity.ok(compraProductoActualizado);
        } catch (CompraProductoNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompraProducto(@PathVariable("id") Long id) {
        compraProductoService.deleteCompraProducto(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/producto/{idProducto}")
    public ResponseEntity<CompraProducto> getCompraProductoByProducto(@PathVariable("idProducto") Long idProducto) {
        Optional<Producto> optionalProducto = productoService.getProductoById(idProducto);
        if (optionalProducto.isPresent()) {
            Producto producto = optionalProducto.get();
            Optional<CompraProducto> optionalCompraProducto = compraProductoService.findCompraProductoByProducto(producto);
            return optionalCompraProducto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/compra/{idCompra}")
    public ResponseEntity<CompraProducto> getCompraProductoByCompra(@PathVariable("idCompra") Long idCompra) {
        Optional<Compra> optionalCompra = compraService.findCompraById(idCompra);
        if (optionalCompra.isPresent()) {
            Compra compra = optionalCompra.get();
            Optional<CompraProducto> optionalCompraProducto = compraProductoService.findCompraProductoByCompra(compra);
            return optionalCompraProducto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
