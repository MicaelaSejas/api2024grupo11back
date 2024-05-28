package com.uade.tpo.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.uade.tpo.entity.Categorias;
import com.uade.tpo.repository.CategoriasRepository;

@Service
public class CategoriasServiceImplement implements CategoriasService {

    @Autowired
    private CategoriasRepository categoriasRepository;

    @Override
    public Page<Categorias> getCategorias(Pageable pageable) {
        return categoriasRepository.findAll(pageable);
    }

    @Override
    public Optional<Categorias> getCategoriasById(Long idCategorias) {
        return categoriasRepository.findById(idCategorias);
    }

    @Override
    public Categorias crearCategorias(Categorias categorias) {
        Optional<Categorias> listaCategorias = categoriasRepository.findById(categorias.getIdCategorias());
        if (listaCategorias.isEmpty()){
            return categoriasRepository.save(categorias);
        }else{
            throw new IllegalStateException("La categoría ya existe");
        }
    }

    @Override
    public List<Categorias> getAllCategorias() {
        return categoriasRepository.findAll();
    }

    @Override
    public Categorias eliminarCategorias(Long idCategorias) {
        Optional<Categorias> categorOptional = categoriasRepository.findById(idCategorias);
        if(categorOptional.isPresent()){
            Categorias categoriaEliminada = categorOptional.get();
            categoriasRepository.deleteById(idCategorias);
            return categoriaEliminada;
        }else{
            throw new NoSuchElementException("La categoría no existe");
        }
    }

    @Override
    public Categorias actualizarCategorias(Long idCategorias, Categorias categoriaActualizada) {
        categoriaActualizada.setIdCategorias(idCategorias);
        return categoriasRepository.save(categoriaActualizada);
    }

}
