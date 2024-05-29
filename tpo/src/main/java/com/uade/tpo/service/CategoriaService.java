package com.uade.tpo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.uade.tpo.entity.Categoria;

public interface CategoriaService {
    public Page<Categoria> getCategorias(PageRequest pageRequest);

    public Optional<Categoria> getCategoriaById(Long categoriaId);

    public Categoria createCategoria(Categoria categoria);

    public List<Categoria> getAllCategorias();

    public Categoria eliminarCategoria(Long id);

    public Categoria actualizarCategoria(Long id, Categoria categoria);
}
