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

import com.uade.tpo.entity.Compra;
import com.uade.tpo.entity.Usuario;
import com.uade.tpo.service.CompraService;
import com.uade.tpo.service.UsuarioService;

@RestController
@RequestMapping("/api/compra")
@CrossOrigin(origins = "http://localhost:3000")
public class CompraController {

    @Autowired
    private CompraService compraService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Compra>> getAllCompras() {
        List<Compra> compras = compraService.findAllCompras();
        return ResponseEntity.ok(compras);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Compra> getCompraById(@PathVariable("id") Long id) {
        Optional<Compra> optionalCompra = compraService.findCompraById(id);
        return optionalCompra.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Compra> createCompra(@RequestBody Compra compra) {
        Compra nuevaCompra = compraService.saveCompra(compra);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaCompra);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Compra> updateCompra(@PathVariable("id") Long id, @RequestBody Compra compra) {
        try {
            Compra compraActualizada = compraService.updateCompra(id, compra);
            return ResponseEntity.ok(compraActualizada);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompra(@PathVariable("id") Long id) {
        compraService.deleteCompra(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<Compra> getComprasByUsuario(@PathVariable("idUsuario") Long idUsuario) {
        Optional<Usuario> optionalUsuario = usuarioService.findUsuarioById(idUsuario);
        if (optionalUsuario.isPresent()) {
            Optional<Compra> optionalCompra = compraService.findCompraByUsuario(optionalUsuario.get());
            return optionalCompra.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
