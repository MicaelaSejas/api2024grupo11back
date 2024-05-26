package com.uade.tpo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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


    @GetMapping("/{userId}")
    public ResponseEntity<String> getCarritoByUserId(@PathVariable final Long userId) {

        try {
            Carrito carrito = this.carritoService.getCarritoByUserId(userId);
            return ResponseEntity.ok().body(carrito.toString());

        } catch (CartNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }

    }


    // o agregar/{idProducto}
    @PostMapping("/agregar")
    public ResponseEntity<String> addToCarrito(@RequestBody final Carrito carrito) {

        try {
            // si carrito no existe, lo creo y agrego producto

            // si carrito existe, agrego el producto
        } catch ( ) {

        }

    }


    @PatchMapping("/actualizarCarrito")
    public ResponseEntity<String> updateCarrito(@RequestBody final Carrito carrito) {

        try {

            // por las dudas, chequear que exista el carrito
            // si existe, hacer update

            // si no llega a existir, exception

        } catch ( ) {

        }

    }

    @PostMapping("vaciarCarrito/{}")

}
