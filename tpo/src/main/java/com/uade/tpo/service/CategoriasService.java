package com.uade.tpo.service;

import com.uade.tpo.entity.Categorias;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface CategoriasService {

    public Page<Categorias> getCategorias(PageRequest pageRequest);

    public Optional<Categorias> getCategoriasById(Integer idCategorias);

    public Categorias crearCategorias(Integer idCategorias);

    public List<Categorias> getAllCategorias();

    public Categorias eliminarCategorias(Integer idCategorias);

    public Categorias modificarCategorias(Integer idCategorias);

}
