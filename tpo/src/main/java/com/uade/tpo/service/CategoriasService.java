package com.uade.tpo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.uade.tpo.entity.Categorias;

public interface CategoriasService {

    public Page<Categorias> getCategorias(Pageable pageable);

    public Optional<Categorias> getCategoriasById(Long idCategorias);

    public Categorias crearCategorias(Categorias categoria);

    public List<Categorias> getAllCategorias();

    public Categorias eliminarCategorias(Long idCategorias);

    public Categorias actualizarCategorias(Long idCategorias, Categorias categoria);

}
