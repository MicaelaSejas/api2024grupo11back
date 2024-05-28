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

import com.uade.tpo.entity.Carrito;
import com.uade.tpo.entity.dto.CarritoRequest;
import com.uade.tpo.entity.dto.CarritoResponse;
import com.uade.tpo.exception.CartNotFoundException;
import com.uade.tpo.service.CarritoService;

@RestController
@RequestMapping("api/v1/carrito")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;


    @GetMapping("/{userId}")
    public ResponseEntity<CarritoResponse> getCarritoByUserId(@PathVariable final Long userId) {

        try {
            Carrito carrito = this.carritoService.getCarritoByUserId(userId);
            return ResponseEntity.ok().body(new CarritoResponse("Carrito obtenido exitosamente.", carrito));

        } catch (CartNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CarritoResponse(HttpStatus.NOT_FOUND.getReasonPhrase(), exception.getMessage()));
        }

    }

    @GetMapping("/{carritoId}")
    public ResponseEntity<CarritoResponse> getCarritoById(@PathVariable final Long userId) {

        try {
            Carrito carrito = this.carritoService.getCarritoById(userId);
            return ResponseEntity.ok().body(new CarritoResponse("Carrito obtenido exitosamente.", carrito));

        } catch (CartNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CarritoResponse(HttpStatus.NOT_FOUND.getReasonPhrase(), exception.getMessage()));
        }

    }


//
//    @PostMapping("/agregar/{carritoId}")
//    public ResponseEntity<CarritoResponse> addToCarrito(@PathVariable final Long carritoId, @RequestBody final CarritoRequest request) {
//
//        try {
//        	carritoService.addToCarrito(carritoId, request);
//        	
//        } catch ( ) {
//
//        }
//
//    }
//
//
//    @PatchMapping("/actualizarCarrito")
//    public ResponseEntity<String> updateCarrito(@RequestBody final Carrito carrito) {
//
//        try {
//
//            // por las dudas, chequear que exista el carrito
//            // si existe, hacer update
//
//            // si no llega a existir, exception
//
//        } catch ( ) {
//
//        }
//
//    }
//
//    @PostMapping("vaciarCarrito/{}")

}
