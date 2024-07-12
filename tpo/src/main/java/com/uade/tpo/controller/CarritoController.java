package com.uade.tpo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.controller.request.CarritoRequest;
import com.uade.tpo.controller.request.CarritoResponse;
import com.uade.tpo.entity.Carrito;
import com.uade.tpo.exception.CartAlreadyEmptyException;
import com.uade.tpo.exception.CartNotFoundException;
import com.uade.tpo.exception.ExceededCartQuantityException;
import com.uade.tpo.exception.ProductNotFoundException;
import com.uade.tpo.exception.ProductNotInCartException;
import com.uade.tpo.exception.UsuarioNotFoundException;
import com.uade.tpo.service.CarritoServiceImpl;

@RestController
@RequestMapping("api/v1/carrito")
public class CarritoController {

    @Autowired
    private CarritoServiceImpl carritoService;

    @GetMapping("{carritoId}")
    public ResponseEntity<CarritoResponse> getCarritoById(@PathVariable final Long carritoId) {

        try {
            Carrito carrito = this.carritoService.getCarritoById(carritoId);
            return ResponseEntity.ok().body(new CarritoResponse("Carrito obtenido exitosamente.", carrito));

        } catch (CartNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new CarritoResponse(HttpStatus.NOT_FOUND.getReasonPhrase(), exception.getMessage()));
        }

    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<CarritoResponse> getCarritoByUserId(@PathVariable final Long userId) {

        try {
            Carrito carrito = this.carritoService.getCarritoByUsuarioId(userId);
            return ResponseEntity.ok().body(new CarritoResponse("Carrito obtenido exitosamente.", carrito));

        } catch (CartNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new CarritoResponse(HttpStatus.NOT_FOUND.getReasonPhrase(), exception.getMessage()));
        }

    }
    
    @GetMapping("/user/email/{email}")
    public ResponseEntity<CarritoResponse> getCarritoByUserEmail(@PathVariable final String email) {

        try {
            Carrito carrito = this.carritoService.getCarritoByEmail(email);
            return ResponseEntity.ok().body(new CarritoResponse("Carrito obtenido exitosamente.", carrito));

        } catch (UsuarioNotFoundException | CartNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new CarritoResponse(HttpStatus.NOT_FOUND.getReasonPhrase(), exception.getMessage()));
        }

    }
    

    /*
     * Si el producto no existe, lo agrega.
     * Si existe, suma en 1 la cantidad.
     * 
     */
    @PostMapping("agregar/{carritoId}")
    public ResponseEntity<CarritoResponse> addToCarrito(@PathVariable final Long carritoId,
            @RequestBody final CarritoRequest request) {
        try {
            return this.carritoService.addToCarrito(carritoId, request);

        } catch (CartNotFoundException | ProductNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new CarritoResponse(HttpStatus.NOT_FOUND.getReasonPhrase(), exception.getMessage()));
        } catch (ExceededCartQuantityException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new CarritoResponse(HttpStatus.BAD_REQUEST.getReasonPhrase(), exception.getMessage()));
        }

    }

    @PostMapping("restar/{carritoId}")
    public ResponseEntity<CarritoResponse> substractFromCarrito(@PathVariable final Long carritoId,
            @RequestBody final CarritoRequest request) {
        try {
            return this.carritoService.substractFromCarrito(carritoId, request);

        } catch (CartNotFoundException | ProductNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new CarritoResponse(HttpStatus.NOT_FOUND.getReasonPhrase(), exception.getMessage()));
        } catch (ProductNotInCartException | ExceededCartQuantityException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new CarritoResponse(HttpStatus.BAD_REQUEST.getReasonPhrase(), exception.getMessage()));
        }

    }

    @PutMapping("quitar/{carritoId}")
    public ResponseEntity<CarritoResponse> removeFromCarrito(@PathVariable final Long carritoId,
            @RequestBody final CarritoRequest request) {
        try {
            return this.carritoService.removeFromCarrito(carritoId, request);
        } catch (CartNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new CarritoResponse(HttpStatus.NOT_FOUND.getReasonPhrase(), exception.getMessage()));
        } catch (ProductNotInCartException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new CarritoResponse(HttpStatus.BAD_REQUEST.getReasonPhrase(), exception.getMessage()));
        }
    }

    @PutMapping("vaciar/{carritoId}")
    public ResponseEntity<CarritoResponse> emptyCarrito(@PathVariable final Long carritoId) {
        try {
            return this.carritoService.emptyCarrito(carritoId);
        } catch (CartNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new CarritoResponse(HttpStatus.NOT_FOUND.getReasonPhrase(), exception.getMessage()));
        } catch (CartAlreadyEmptyException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new CarritoResponse(HttpStatus.BAD_REQUEST.getReasonPhrase(), exception.getMessage()));
        }
    }
}
