package com.uade.tpo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.uade.tpo.entity.Categoria;
import com.uade.tpo.exception.ProductDuplicateException;
import com.uade.tpo.repository.CategoriaRepository;

@Service
public class CategoriaServiceImp {
    @Autowired
    private CategoriaRepository categoriaRepository;

    public Page<Categoria> getCategorias(PageRequest pageable){
        return categoriaRepository.findAll(pageable);
    }

    public Optional<Categoria> getCategoriaById(Long categoriaId){
        return categoriaRepository.findById(categoriaId);
    }

    public Categoria createCategoria(String descripcion) throws ProductDuplicateException {
        List<Categoria> categorias = categoriaRepository.findByDescripcion(descripcion);
        if (categorias.isEmpty())
            return categoriaRepository.save(new Categoria(descripcion));
        throw new ProductDuplicateException();
    }
}
