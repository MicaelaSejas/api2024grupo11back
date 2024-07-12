package com.uade.tpo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.controller.request.FavoritoRequest;
import com.uade.tpo.controller.request.FavoritoResponse;
import com.uade.tpo.entity.Favorito;
import com.uade.tpo.exception.FavNotFoundException;
import com.uade.tpo.exception.ProductInFavsException;
import com.uade.tpo.service.FavoritoServiceImpl;

@RestController
@RequestMapping("api/v1/favorito")
public class FavoritoController {

    @Autowired
    private FavoritoServiceImpl favoritoService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<FavoritoResponse> getFavoritoByUserId(@PathVariable final Long userId)
            throws FavNotFoundException {

        try {
            Favorito favorito = this.favoritoService.getFavoritoByUserId(userId);
            return ResponseEntity.ok().body(new FavoritoResponse("Carrito obtenido exitosamente.", favorito));

        } catch (FavNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new FavoritoResponse(HttpStatus.NOT_FOUND.getReasonPhrase(), exception.getMessage()));
        }

    }

    @PostMapping("agregar/{userID}")
    public ResponseEntity<FavoritoResponse> addToCarrito(@PathVariable final Long userId,
            @RequestBody final FavoritoRequest request) throws ProductInFavsException {
        try {
            return this.favoritoService.addToFavorito(userId, request);

        } catch (FavNotFoundException | ProductInFavsException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new FavoritoResponse(HttpStatus.NOT_FOUND.getReasonPhrase(), exception.getMessage()));
        }
    }

    @DeleteMapping("quitar/{userID}")
    public ResponseEntity<FavoritoResponse> removeFromCarrito(@PathVariable final Long userId,
            @RequestBody final FavoritoRequest request) {
        try {
            return this.favoritoService.removeFromFavorito(userId, request);

        } catch (FavNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new FavoritoResponse(HttpStatus.NOT_FOUND.getReasonPhrase(), exception.getMessage()));
        } catch (ProductInFavsException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new FavoritoResponse(HttpStatus.BAD_REQUEST.getReasonPhrase(), exception.getMessage()));
        }
    }
}
