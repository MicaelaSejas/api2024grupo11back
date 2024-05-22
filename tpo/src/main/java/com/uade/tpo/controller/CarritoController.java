package com.uade.tpo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/carrito")
public class CarritoController {


    @GetMapping("/{id}")
    public ResponseEntity<String> getCarritoById(@PathVariable final Long id) {
        return ResponseEntity.ok().body(Long.toString(id));
    }


}
