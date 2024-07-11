package com.uade.tpo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.uade.tpo.entity.Usuario;
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

	@Override
	public Optional<Usuario> findById(Long id) {
		return usuarioRepository.findById(id);
	}
}
