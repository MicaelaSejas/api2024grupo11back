package com.uade.tpo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.tpo.entity.Categoria;
import com.uade.tpo.repository.CategoriaRepository;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    
    public List<Categoria> findAllCategorias() {
        return categoriaRepository.findAll();
    }

    
    public Optional<Categoria> findCategoriaById(Long id) {
        return categoriaRepository.findById(id);
    }

    
    public Categoria saveCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    
    public void deleteCategoria(Long id) {
        categoriaRepository.deleteById(id);
    }

    
    public Categoria updateCategoria(Long id, Categoria categoriaActualizada) {
        Optional<Categoria> optionalCategoria = categoriaRepository.findById(id);
        if (optionalCategoria.isPresent()) {
            Categoria categoriaExistente = optionalCategoria.get();
            categoriaExistente.setDescripcion(categoriaActualizada.getDescripcion());
            return categoriaRepository.save(categoriaExistente);
        } else {
            throw new RuntimeException("No se encontró la categoría con ID: " + id);
        }
    }
}
