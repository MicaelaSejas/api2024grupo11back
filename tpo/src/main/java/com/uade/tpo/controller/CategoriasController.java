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

import com.uade.tpo.controller.request.CategoriasRequest;
import com.uade.tpo.entity.Categorias;
import com.uade.tpo.service.CategoriasService;

@RestController
@RequestMapping("categorias")
public class CategoriasController {

    @Autowired
    private CategoriasService categoriasService;

    @GetMapping
    public ResponseEntity<Page<Categorias>> getCategorias(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {
        if (page == null || size == null)
            return ResponseEntity.ok(categoriasService.getCategorias(PageRequest.of(0, Integer.MAX_VALUE)));
        return ResponseEntity.ok(categoriasService.getCategorias(PageRequest.of(page, size)));
    }

    @GetMapping("/{idCategorias}")
    public ResponseEntity<Categorias> getCategoriasById(@PathVariable Long idCategorias) {
        Optional<Categorias> result = categoriasService.getCategoriasById(idCategorias);
        if (result.isPresent())
            return ResponseEntity.ok(result.get());

        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Object> crearCategorias(@RequestBody CategoriasRequest categoriasRequest){
        Categorias nuevaCategoria = new Categorias();
        nuevaCategoria.setDescripcion(categoriasRequest.getDescripcion());
        Categorias result = categoriasService.crearCategorias(nuevaCategoria);
        return ResponseEntity.created(URI.create("/categorias/" + result.getIdCategorias())).body(result);
    }
    
}
