package com.uade.tpo.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uade.tpo.entity.Compra;
import com.uade.tpo.entity.Usuario;
import com.uade.tpo.repository.CompraRepository;

@Service
public class CompraService {

    @Autowired
    private CompraRepository compraRepository;

    public List<Compra> findAllCompras() {
        return compraRepository.findAll();
    }

    public Optional<Compra> findCompraById(Long id) {
        return compraRepository.findById(id);
    }

    @Transactional
    public Compra saveCompra(Compra compra) {
        return compraRepository.save(compra);
    }

    @Transactional
    public Compra updateCompra(Long id, Compra compraNueva) {
        Optional<Compra> optionalCompra = compraRepository.findById(id);
        if (optionalCompra.isPresent()) {
            Compra compraExistente = optionalCompra.get();
            compraExistente.setUsuario(compraNueva.getUsuario());
            compraExistente.setPrecioTotal(compraNueva.getPrecioTotal());
            

            return compraRepository.save(compraExistente);
        } else {
            throw new NoSuchElementException("No se encontró la compra con ID: " + id);
        }
    }

    @Transactional
    public void deleteCompra(Long id) {
        compraRepository.deleteById(id);
    }

    public Optional<Compra> findCompraByUsuario(Usuario usuario) {
        return compraRepository.findByUsuario(usuario);
    }
}
