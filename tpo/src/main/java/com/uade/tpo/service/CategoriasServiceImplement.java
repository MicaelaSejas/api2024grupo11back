package com.uade.tpo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.uade.tpo.entity.Categorias;
import com.uade.tpo.entity.Descuentos;
import com.uade.tpo.repository.CategoriasRepository;

@Service
public class CategoriasServiceImplement implements CategoriasService {

    @Autowired
    private CategoriasRepository categoriasRepository;

    @Override
    public Page<Categorias> getCategorias(PageRequest pageable) {
        return categoriasRepository.findAll(pageable);
        throw new UnsupportedOperationException("Unimplemented method 'getCategorias'");
    }

    @Override
    public Optional<Categorias> getCategoriasById(Integer idCategorias) {
        return categoriasRepository.findById(idCategorias).orElse(null);
        throw new UnsupportedOperationException("Unimplemented method 'getCategoriasById'");
    }

    @Override
    public Categorias crearCategorias(Integer idCategorias) {
        List<Categorias> categorias = categoriasRepository.findById(idCategorias);
        if (categorias.isEmpty())
            return categoriasRepository.save(new Categorias(idCategorias));
        throw new UnsupportedOperationException("Unimplemented method 'crearCategorias'");
    }

    @Override
    public List<Categorias> getAllCategorias() {
        return categoriasRepository.findAll();
        throw new UnsupportedOperationException("Unimplemented method 'getAllCategorias'");
    }

    @Override
    public Categorias eliminarCategorias(Integer idCategorias) {
        categoriasRepository.deleteById(idCategorias);
        throw new UnsupportedOperationException("Unimplemented method 'eliminarCategorias'");
    }

    @Override
    public Categorias modificarCategorias(Integer idCategorias) {
        return categoriasRepository.save(idCategorias);
        throw new UnsupportedOperationException("Unimplemented method 'modificarCategorias'");
    }

}
