package com.uade.tpo.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.tpo.entity.Usuario;
import com.uade.tpo.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> findAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> findUsuarioById(Long id) {
        return usuarioRepository.findById(id);
    }

    public Optional<Usuario> findUsuarioByUsername(String usuario) {
        return usuarioRepository.findByUsuario(usuario);
    }

    public Optional<Usuario> findUsuarioByMail(String mail) {
        return usuarioRepository.findByMail(mail);
    }

    public List<Usuario> findUsuariosByRol(Long idRol) {
        return usuarioRepository.findByRolId(idRol);
    }

    public Optional<Usuario> findUsuarioByIdWithRol(Long id) {
        return usuarioRepository.findByIdWithRol(id);
    }

    public Usuario saveUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario updateUsuario(Long id, Usuario usuarioNuevo) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);
        if (optionalUsuario.isPresent()) {
            Usuario usuarioExistente = optionalUsuario.get();
            usuarioExistente.setUsuario(usuarioNuevo.getUsuario());
            usuarioExistente.setNombre(usuarioNuevo.getNombre());
            usuarioExistente.setApellido(usuarioNuevo.getApellido());
            usuarioExistente.setMail(usuarioNuevo.getMail());
            usuarioExistente.setPassword(usuarioNuevo.getPassword());
            usuarioExistente.setIdRol(usuarioNuevo.getIdRol());
            return usuarioRepository.save(usuarioExistente);
        } else {
            throw new NoSuchElementException("No se encontró el usuario con ID: " + id);
        }
    }

    public void deleteUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }


    public Optional<Usuario> login(String usuario, String password) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findByUsuario(usuario);
        if (optionalUsuario.isPresent()) {
            Usuario usuarioEncontrado = optionalUsuario.get();
            if (usuarioEncontrado.getPassword().equals(password)) {
                return optionalUsuario;
            }
        }
        return Optional.empty(); 
    }
}
