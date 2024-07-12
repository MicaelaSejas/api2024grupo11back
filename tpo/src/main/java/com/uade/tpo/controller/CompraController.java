package com.uade.tpo.controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.controller.request.CompraRequest;
import com.uade.tpo.controller.request.CompraResponse;
import com.uade.tpo.entity.Compra;
import com.uade.tpo.entity.Usuario;
import com.uade.tpo.service.CompraService;
import com.uade.tpo.service.UsuariosService;

@RestController
@RequestMapping("/api/v1/compra")
public class CompraController {

    @Autowired
    private CompraService compraService;

    @Autowired
    private UsuariosService usuarioService;

    @GetMapping
    public ResponseEntity<List<Compra>> getAllCompras() {
        List<Compra> compras = compraService.findAllCompras();
        return ResponseEntity.ok(compras);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Compra> getCompraById(@PathVariable("id") Long id) {
        Optional<Compra> optionalCompra = compraService.findCompraById(id);
        return optionalCompra.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(value="/create", consumes= {"application/json"})
    public ResponseEntity<CompraResponse> createCompra(@RequestBody CompraRequest req) {
    	System.out.println(req.toString());
    	try {
    		Compra nuevaCompra = compraService.createCompra(req);
    		return ResponseEntity.ok(new CompraResponse("Compra creada con exito", nuevaCompra));
    	} catch(Exception ex) {
    		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CompraResponse(ex.getMessage()));
    	}
    }

    @PutMapping("/{id}")
    public ResponseEntity<Compra> updateCompra(@PathVariable("id") Long id, @RequestBody Compra compra) {
        try {
            Compra compraActualizada = compraService.updateCompra(id, compra);
            return ResponseEntity.ok(compraActualizada);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompra(@PathVariable("id") Long id) {
        compraService.deleteCompra(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<Set<Compra>> getComprasByUsuario(@PathVariable("idUsuario") Long idUsuario) {
        Optional<Usuario> optionalUsuario = usuarioService.findById(idUsuario);
        if (optionalUsuario.isPresent()) {
            Set<Compra> compras = compraService.findCompraByUsuario(idUsuario);
            compras.forEach(compra -> compra.getCompraProductos().size());
            return ResponseEntity.ok(compras);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}