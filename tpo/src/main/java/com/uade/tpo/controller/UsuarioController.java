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

import com.uade.tpo.entity.Usuario;
import com.uade.tpo.service.RolService;
import com.uade.tpo.service.UsuarioService;

@RestController
@RequestMapping("/api/usuario")
@CrossOrigin(origins = "http://localhost:3000")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    private RolService rolService;

    @GetMapping
    public ResponseEntity<List<Usuario>> getAllUsuarios() {
        List<Usuario> usuarios = usuarioService.findAllUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable("id") Long id) {
        Optional<Usuario> optionalUsuario = usuarioService.findUsuarioById(id);
        return optionalUsuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/usuario/{usuario}")
    public ResponseEntity<Usuario> getUsuarioByUsername(@PathVariable("usuario") String usuario) {
        Optional<Usuario> optionalUsuario = usuarioService.findUsuarioByUsername(usuario);
        return optionalUsuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/mail/{mail}")
    public ResponseEntity<Usuario> getUsuarioByMail(@PathVariable("mail") String mail) {
        Optional<Usuario> optionalUsuario = usuarioService.findUsuarioByMail(mail);
        return optionalUsuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/rol/{idRol}")
    public ResponseEntity<List<Usuario>> getUsuariosByRol(@PathVariable("idRol") Long idRol) {
        List<Usuario> usuarios = usuarioService.findUsuariosByRol(idRol);
        return ResponseEntity.ok(usuarios);
    }


    @PostMapping
    public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario) {
        System.out.println("Datos recibidos para crear usuario:");
        System.out.println(usuario); 
    
        Usuario nuevoUsuario = usuarioService.saveUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable("id") Long id, @RequestBody Usuario usuario) {
        System.out.println("Datos recibidos para actualizar usuario con ID: " + id);
        System.out.println(usuario); 
    
        try {
            Usuario usuarioActualizado = usuarioService.updateUsuario(id, usuario);
            return ResponseEntity.ok(usuarioActualizado);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

@DeleteMapping("/{id}")
public ResponseEntity<Void> deleteUsuario(@PathVariable("id") Long id) {
    System.out.println("Eliminando usuario con ID: " + id);

    usuarioService.deleteUsuario(id);
    return ResponseEntity.noContent().build();
}
}
