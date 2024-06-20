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

import com.uade.tpo.entity.Descuento;
import com.uade.tpo.service.DescuentoService;

@RestController
@RequestMapping("/api/descuento")
@CrossOrigin(origins = "http://localhost:3000")
public class DescuentoController {

    @Autowired
    private DescuentoService descuentoService;

    @GetMapping
    public ResponseEntity<List<Descuento>> getAllDescuentos() {
        List<Descuento> descuentos = descuentoService.listarDescuentos();
        return ResponseEntity.ok(descuentos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Descuento> getDescuentoById(@PathVariable("id") Long id) {
        Optional<Descuento> optionalDescuento = descuentoService.obtenerDescuentoPorId(id);
        return optionalDescuento.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Descuento> createDescuento(@RequestBody Descuento descuento) {
        Descuento nuevoDescuento = descuentoService.guardarDescuento(descuento);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoDescuento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Descuento> updateDescuento(@PathVariable("id") Long id, @RequestBody Descuento descuento) {
        Descuento descuentoActualizado = descuentoService.actualizarDescuento(id, descuento);
        return ResponseEntity.ok(descuentoActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDescuento(@PathVariable("id") Long id) {
        descuentoService.eliminarDescuento(id);
        return ResponseEntity.noContent().build();
    }
}
