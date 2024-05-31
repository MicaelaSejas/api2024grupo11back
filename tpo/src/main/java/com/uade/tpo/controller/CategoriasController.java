package com.uade.tpo.controller;

import java.net.URI;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.controller.request.CategoriasRequest;
import com.uade.tpo.entity.Categoria;
import com.uade.tpo.service.CategoriasService;

@RestController
@RequestMapping("api/v1/categorias")
public class CategoriasController {

    @Autowired
    private CategoriasService categoriasService;

    @GetMapping
    public ResponseEntity<Page<Categoria>> getCategorias(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {
        if (page == null || size == null)
            return ResponseEntity.ok(categoriasService.getCategorias(PageRequest.of(0, Integer.MAX_VALUE)));
        return ResponseEntity.ok(categoriasService.getCategorias(PageRequest.of(page, size)));
    }


    @GetMapping("/{idCategorias}")
    public ResponseEntity<Categoria> getCategoriasById(@PathVariable Long idCategorias) {
        Optional<Categoria> result = categoriasService.getCategoriasById(idCategorias);
        if (result.isPresent())
            return ResponseEntity.ok(result.get());

        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Object> crearCategorias(@RequestBody CategoriasRequest categoriasRequest){
        Categoria nuevaCategoria = new Categoria();
        nuevaCategoria.setDescripcion(categoriasRequest.getDescripcion());
        Categoria result = categoriasService.crearCategorias(nuevaCategoria);
        return ResponseEntity.created(URI.create("/categorias/" + result.getIdCategorias())).body(result);
    }

    @PutMapping("/{idCategorias}")
    public ResponseEntity<Object> actualizarCategorias(@PathVariable Long idCategorias, @RequestBody CategoriasRequest categoriasRequest) {
        Optional<Categoria> categoriasOptional = categoriasService.getCategoriasById(idCategorias);
        if (categoriasOptional.isPresent()) {
            Categoria categoriaExistente = categoriasOptional.get();
            categoriaExistente.setDescripcion(categoriasRequest.getDescripcion());
            categoriasService.actualizarCategorias(idCategorias, categoriaExistente);
            return ResponseEntity.ok(categoriaExistente);
        }
        return ResponseEntity.notFound().build();
    }    
    
    @DeleteMapping("/{idCategorias}")
    public ResponseEntity<Object> eliminarCategorias(@PathVariable Long idCategorias) {
        try {
            categoriasService.eliminarCategorias(idCategorias);
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
}







