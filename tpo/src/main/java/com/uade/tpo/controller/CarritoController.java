package com.uade.tpo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.exception.CartNotFoundException;
import com.uade.tpo.model.Carrito;
import com.uade.tpo.service.CarritoService;

@RestController
@RequestMapping("api/v1/carrito")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    @GetMapping("/{id}")
    public ResponseEntity<String> getCarritoById(@PathVariable final Long id) {

        try {
            Carrito carrito = this.carritoService.getCarritoById(id);
            return ResponseEntity.ok().body(carrito.toString());

        } catch (CartNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }

    }

}
