package com.uade.tpo.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.uade.tpo.entity.Categoria;
import com.uade.tpo.repository.CategoriaRepository;

@Service
public class CategoriaServiceImp implements CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    public Page<Categoria> getCategorias(PageRequest pageable){
        return categoriaRepository.findAll(pageable);
    }

    public Optional<Categoria> getCategoriaById(Long categoriaId){
        return categoriaRepository.findById(categoriaId);
    }

    public Categoria createCategoria(Categoria categoria) {
        Optional<Categoria> listaCategorias = categoriaRepository.findById(categoria.getIdCategorias());
        if (listaCategorias.isEmpty()){
            return categoriaRepository.save(categoria);
        }else{
            throw new IllegalStateException("La categoría ya existe");
        }
    }

    public List<Categoria> getAllCategorias() {
        return categoriaRepository.findAll();
    }

    public Categoria eliminarCategoria(Long id) {
        Optional<Categoria> categorOptional = categoriaRepository.findById(id);
        if(categorOptional.isPresent()){
            Categoria categoriaEliminada = categorOptional.get();
            categoriaRepository.deleteById(id);
            return categoriaEliminada;
        }else{
            throw new NoSuchElementException("La categoría no existe");
        }
    }

    public Categoria actualizarCategoria(Long id, Categoria categoriaActualizada) {
        categoriaActualizada.setIdCategorias(id);
        return categoriaRepository.save(categoriaActualizada);
    }
}
