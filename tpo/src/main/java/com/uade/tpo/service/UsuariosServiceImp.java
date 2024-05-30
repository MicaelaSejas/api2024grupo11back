package com.uade.tpo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uade.tpo.entity.Roles;
import com.uade.tpo.entity.Usuario;
import com.uade.tpo.exception.UsuarioDuplicatedException;
import com.uade.tpo.repository.UsuarioRepository;

@Service
public class UsuariosServiceImp implements UsuariosService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Page<Usuario> obtenerUsuarios(PageRequest pageable) {
        return usuarioRepository.findAll(pageable);
    }

    public Optional<Usuario> obtenerUsuarioByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    @Transactional(rollbackFor = Throwable.class)
    public Usuario nuevoUsuario(String username, String nombre, String apellido, String email,
            String password, Roles rol) throws UsuarioDuplicatedException {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(email);
        if (usuario.isEmpty()) {
            usuarioRepository.save(new Usuario(null, username, nombre, apellido, email, password, rol));
        }

        throw new UsuarioDuplicatedException();
    }
}
