package com.uade.tpo.service;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.uade.tpo.entity.Categoria;
import com.uade.tpo.repository.CategoriasRepository;

@Service
public class CategoriasServiceImplement implements CategoriasService {

    @Autowired
    private CategoriasRepository categoriasRepository;

    @Override
    public Page<Categoria> getCategorias(Pageable pageable) {
        return categoriasRepository.findAll(pageable);
    }

    @Override
    public Optional<Categoria> getCategoriasById(Long idCategorias) {
        return categoriasRepository.findById(idCategorias);
    }

    @Override
    public Categoria crearCategorias(Categoria categorias) {
        return categoriasRepository.save(categorias);
    }

    @Override
    public Categoria actualizarCategorias(Long idCategorias, Categoria categoriaActualizada) {
        categoriaActualizada.setIdCategorias(idCategorias);
        return categoriasRepository.save(categoriaActualizada);
    }

    @Override
    public Categoria eliminarCategorias(Long idCategorias) {
        Optional<Categoria> categorOptional = categoriasRepository.findById(idCategorias);
        if(categorOptional.isPresent()){
            Categoria categoriaEliminada = categorOptional.get();
            categoriasRepository.deleteById(idCategorias);
            return categoriaEliminada;
        }else{
            throw new NoSuchElementException("La categoría no existe");
        }
    }



}
