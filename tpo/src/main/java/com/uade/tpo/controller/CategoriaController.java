package com.uade.tpo.controller;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.entity.Categoria;
import com.uade.tpo.entity.dto.CategoriaRequest;
import com.uade.tpo.service.CategoriaService;

@RestController
@RequestMapping("categoria")
public class CategoriaController {
    
    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<Page<Categoria>> getCategorias(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {
        if (page == null || size == null)
            return ResponseEntity.ok(categoriaService.getCategorias(PageRequest.of(0, Integer.MAX_VALUE)));
        return ResponseEntity.ok(categoriaService.getCategorias(PageRequest.of(page, size)));
    }

    @GetMapping("/{idCategorias}")
    public ResponseEntity<Categoria> getCategoriaById(@PathVariable Long id) {
        Optional<Categoria> result = categoriaService.getCategoriaById(id);
        if (result.isPresent())
            return ResponseEntity.ok(result.get());

        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Object> createCategoria(@RequestBody CategoriaRequest categoriaRequest){
        Categoria nuevaCategoria = new Categoria();
        nuevaCategoria.setDescripcion(categoriaRequest.getDescripcion());
        Categoria result = categoriaService.createCategoria(nuevaCategoria);
        return ResponseEntity.created(URI.create("/categoria/" + result.getIdCategorias())).body(result);
    }
}
