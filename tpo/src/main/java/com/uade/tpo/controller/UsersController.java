package com.uade.tpo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.controller.auth.RegisterRequest;
import com.uade.tpo.entity.Carrito;
import com.uade.tpo.entity.Usuario;
import com.uade.tpo.entity.dto.CarritoResponse;
import com.uade.tpo.exception.CartNotFoundException;
import com.uade.tpo.exception.UsuarioDuplicatedException;
import com.uade.tpo.service.UsuariosService;

import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URI;
import java.util.Locale.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api/v1/login")
public class UsersController {

    @Autowired
    private UsuariosService UsuariosService;

    /*
     * TODO: Crear nuevo usuario
     * Obtener usuario por credenciales
     * Crear rol
     * Asignar rol
     * Administrar usuarios
     * 
     * Validaciones
     * Que email e username sean únicos
     * 
     */
    /*
     * @PostMapping
     * public ResponseEntity<Object> nuevoUsuario(@RequestBody RegisterRequest
     * registerRequest)
     * throws UsuarioDuplicatedException {
     * 
     * Usuario result = UsuariosService.nuevoUsuario(registerRequest.getEmail(),
     * registerRequest.getFirstname(), registerRequest.getLastname(),
     * registerRequest.getEmail(),
     * registerRequest.getPassword(), registerRequest.getRoles());
     * 
     * return ResponseEntity.created(URI.create("/created/" +
     * result.getId())).body(result);
     * }
     */

}