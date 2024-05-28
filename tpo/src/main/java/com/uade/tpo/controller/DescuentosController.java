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

import com.uade.tpo.controller.request.DescuentosRequest;
import com.uade.tpo.entity.Descuentos;
import com.uade.tpo.service.DescuentosService;

@RestController
@RequestMapping("descuentos")
public class DescuentosController {

    @Autowired
    private DescuentosService descuentosService;

    @GetMapping
    public ResponseEntity<Page<Descuentos>> getDescuentos(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {
        if (page == null || size == null)
            return ResponseEntity.ok(descuentosService.getDescuentos(PageRequest.of(0, Integer.MAX_VALUE)));
        return ResponseEntity.ok(descuentosService.getDescuentos(PageRequest.of(page, size)));
    }

    @GetMapping("/{idDescuentos}")
    public ResponseEntity<Descuentos> getDescuentosById(@PathVariable Long idDescuentos) {
        Optional<Descuentos> result = descuentosService.getDescuentosById(idDescuentos);
        if (result.isPresent())
            return ResponseEntity.ok(result.get());

        return ResponseEntity.noContent().build();
    }
    
    @PostMapping
    public ResponseEntity<Object> crearDescuentos(@RequestBody DescuentosRequest descuentosRequest){
        Descuentos nuevoDescuento = new Descuentos();
        nuevoDescuento.setPorcentaje(descuentosRequest.getPorcentaje());
        Descuentos result = descuentosService.crearDescuentos(nuevoDescuento);
        return ResponseEntity.created(URI.create("/descuentos/" + result.getIdDescuentos())).body(result);
    }
    
}
