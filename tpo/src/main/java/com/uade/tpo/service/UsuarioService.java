package com.uade.tpo.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.tpo.entity.Rol;
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

    public Optional<Usuario> findUsuarioByUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }

    public Optional<Usuario> findUsuarioByMail(String mail) {
        return usuarioRepository.findByMail(mail);
    }

   public Optional<Usuario> findUsuariosByRol(Rol rol) {
        return usuarioRepository.findByIdRol(rol);
    }


    public Usuario saveUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario updateUsuario(Long id, Usuario usuarioNuevo) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);
        if (optionalUsuario.isPresent()) {
            Usuario usuarioExistente = optionalUsuario.get();
            usuarioExistente.setUsername(usuarioNuevo.getUsername());
            usuarioExistente.setNombre(usuarioNuevo.getNombre());
            usuarioExistente.setApellido(usuarioNuevo.getApellido());
            usuarioExistente.setMail(usuarioNuevo.getMail());
            usuarioExistente.setPassword(usuarioNuevo.getPassword());
            usuarioExistente.setRol(usuarioNuevo.getRol());
            return usuarioRepository.save(usuarioExistente);
        } else {
            throw new NoSuchElementException("No se encontró el usuario con ID: " + id);
        }
    }

    public void deleteUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}
