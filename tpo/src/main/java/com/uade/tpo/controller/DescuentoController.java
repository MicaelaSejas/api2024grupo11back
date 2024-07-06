package com.uade.tpo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.entity.Descuento;
import com.uade.tpo.service.DescuentoService;

@RestController
@RequestMapping("/api/v1/descuento")
public class DescuentoController {

    @Autowired
    private DescuentoService descuentoService;

    @GetMapping
    public ResponseEntity<List<Descuento>> getAllDescuentos() {
        List<Descuento> descuentos = descuentoService.listarDescuentos();
        return ResponseEntity.ok(descuentos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Descuento> getDescuentoById(@PathVariable("id") Integer id) {
        Optional<Descuento> optionalDescuento = descuentoService.obtenerDescuentoPorId(id);
        if(optionalDescuento.isPresent()){
            return ResponseEntity.ok(optionalDescuento.get());
        }else{
            throw new RuntimeException("Descuento no encontrado con ID: " + id);
        }
    }

    @PostMapping
    public ResponseEntity<Descuento> createDescuento(@RequestBody Descuento descuento) {
        try {
            Descuento nuevoDescuento = descuentoService.guardarDescuento(descuento);    
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoDescuento);
        } catch (RuntimeException e) {
            throw  new RuntimeException("Error al crear el descuento: " + e.getMessage());
        }
    }
        
    @PutMapping("/{id}")
    public ResponseEntity<Descuento> updateDescuento(@PathVariable("id") Integer id, @RequestBody Descuento descuento) {
        try{
        Descuento descuentoActualizado = descuentoService.actualizarDescuento(id, descuento);
        return ResponseEntity.ok(descuentoActualizado);
    } catch (RuntimeException e){
        throw new RuntimeException("No se pudo actualizar el descuento con el ID: " + id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDescuento(@PathVariable("id") Integer id) {
        try {
            descuentoService.eliminarDescuento(id);    
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            throw new RuntimeException("No se pudo eliminar el descuento con ID: " + id + ". " + e.getMessage());
        }
    }
        
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error en la operación: " + ex.getMessage());
    }
    
}




