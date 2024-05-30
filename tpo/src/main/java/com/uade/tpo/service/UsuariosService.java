package com.uade.tpo.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.uade.tpo.entity.Roles;
import com.uade.tpo.entity.Usuario;
import com.uade.tpo.exception.UsuarioDuplicatedException;

public interface UsuariosService {
    public Page<Usuario> obtenerUsuarios(PageRequest pageRequest);

    public Optional<Usuario> obtenerUsuarioByEmail(String email);

    public Usuario nuevoUsuario(String username, String nombre, String apellido, String email,
            String password, Roles rol) throws UsuarioDuplicatedException;
}