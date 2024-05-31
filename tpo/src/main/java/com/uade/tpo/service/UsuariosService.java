package com.uade.tpo.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.uade.tpo.entity.Usuario;

public interface UsuariosService {
    public Page<Usuario> obtenerUsuarios(PageRequest pageRequest);

    public Optional<Usuario> obtenerUsuarioByEmail(String email);
}