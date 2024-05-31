package com.uade.tpo.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.uade.tpo.entity.Categoria;

public interface CategoriasService {

    public Page<Categoria> getCategorias(Pageable pageable);

    public Optional<Categoria> getCategoriasById(Long idCategorias);

    public Categoria crearCategorias(Categoria categoria);

    public Categoria actualizarCategorias(Long idCategorias, Categoria categoriasActualizadas);

    public Categoria eliminarCategorias(Long idCategorias);

    

}
