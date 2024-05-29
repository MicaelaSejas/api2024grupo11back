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

import com.uade.tpo.entity.Descuento;
import com.uade.tpo.entity.dto.DescuentoRequest;
import com.uade.tpo.service.DescuentoService;

@RestController
@RequestMapping("descuento")
public class DescuentoController {
    
    @Autowired
    private DescuentoService descuentoService;

    @GetMapping
    public ResponseEntity<Page<Descuento>> getDescuento(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {
        if (page == null || size == null)
            return ResponseEntity.ok(descuentoService.getDescuento(PageRequest.of(0, Integer.MAX_VALUE)));
        return ResponseEntity.ok(descuentoService.getDescuento(PageRequest.of(page, size)));
    }

    @GetMapping("/{idDescuentos}")
    public ResponseEntity<Descuento> getDescuentoById(@PathVariable Long id) {
        Optional<Descuento> result = descuentoService.getDescuentoById(id);
        if (result.isPresent())
            return ResponseEntity.ok(result.get());

        return ResponseEntity.noContent().build();
    }
    
    @PostMapping
    public ResponseEntity<Object> crearDescuento(@RequestBody DescuentoRequest descuentoRequest){
        Descuento nuevoDescuento = new Descuento();
        nuevoDescuento.setDescuento(descuentoRequest.getDescuento());
        Descuento result = descuentoService.createDescuento(nuevoDescuento);
        return ResponseEntity.created(URI.create("/descuento/" + result.getIdDescuentos())).body(result);
    }
}
