package com.uade.tpo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.entity.Usuario;
import com.uade.tpo.service.UsuariosService;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    @Autowired
    private UsuariosService usuariosService;

    @GetMapping
    public Page<Usuario> obtenerUsuarios(@RequestParam int page, @RequestParam int size) {
        return usuariosService.obtenerUsuarios(PageRequest.of(page, size));
    }

    @GetMapping("/{id}")
    public Optional<Usuario> obtenerUsuarioById(@PathVariable Long id) {
        return usuariosService.findById(id);
    }

    @GetMapping("/email")
    public Optional<Usuario> obtenerUsuarioByEmail(@RequestParam String email) {
        return usuariosService.obtenerUsuarioByEmail(email);
    }
}
