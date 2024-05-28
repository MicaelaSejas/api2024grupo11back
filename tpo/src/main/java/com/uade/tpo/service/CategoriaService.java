package com.uade.tpo.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.uade.tpo.entity.Categoria;
import com.uade.tpo.exception.ProductDuplicateException;

public interface CategoriaService {
    public Page<Categoria> getCategorias(PageRequest pageRequest);

    public Optional<Categoria> getCategoriaById(Long categoriaId);

    public Categoria createCategoria(String descripcion) throws ProductDuplicateException;
}
