package com.uade.tpo.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.tpo.entity.Rol;
import com.uade.tpo.repository.RolRepository;

@Service
public class RolService {

    @Autowired
    private RolRepository rolRepository;

    public List<Rol> findAllRoles() {
        return rolRepository.findAll();
    }

    public Optional<Rol> findRolById(Long id) {
        return rolRepository.findById(id);
    }

    public Rol saveRol(Rol rol) {
        return rolRepository.save(rol);
    }

    public Rol updateRol(Long id, Rol rolNuevo) {
        Optional<Rol> optionalRol = rolRepository.findById(id);
        if (optionalRol.isPresent()) {
            Rol rolExistente = optionalRol.get();
            rolExistente.setDescripcion(rolNuevo.getDescripcion());
            rolExistente.setUsuarios(rolNuevo.getUsuarios());
            return rolRepository.save(rolExistente);
        } else {
            throw new NoSuchElementException("No se encontró el rol con ID: " + id);
        }
    }

    public void deleteRol(Long id) {
        rolRepository.deleteById(id);
    }
}

