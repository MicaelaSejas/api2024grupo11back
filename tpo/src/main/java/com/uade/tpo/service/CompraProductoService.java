package com.uade.tpo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uade.tpo.entity.Compra;
import com.uade.tpo.entity.CompraProducto;
import com.uade.tpo.entity.Producto;
import com.uade.tpo.exception.CompraProductoNotFoundException;
import com.uade.tpo.repository.CompraProductoRepository;

@Service
public class CompraProductoService {

    @Autowired
    private CompraProductoRepository compraProductoRepository;


    public List<CompraProducto> findAllCompraProducto() {
        return compraProductoRepository.findAll();
    }

    public Optional<CompraProducto> findCompraProductoById(Long id) {
        return compraProductoRepository.findById(id);
    }

    @Transactional
    public CompraProducto saveCompraProducto(CompraProducto compraProducto) {
        return compraProductoRepository.save(compraProducto);
    }

    @Transactional
    public CompraProducto updateCompraProducto(Long id, CompraProducto compraProductoNuevo) throws CompraProductoNotFoundException {
        Optional<CompraProducto> optionalCompraProducto = compraProductoRepository.findById(id);
        if (optionalCompraProducto.isPresent()) {
            CompraProducto compraProductoExistente = optionalCompraProducto.get();
            compraProductoExistente.setCompra(compraProductoNuevo.getCompra());
            compraProductoExistente.setProducto(compraProductoNuevo.getProducto());
            compraProductoExistente.setCantidad(compraProductoNuevo.getCantidad());
            return compraProductoRepository.save(compraProductoExistente);
        } else {
            throw new CompraProductoNotFoundException("No se encontró el CompraProducto con ID: " + id);
        }
    }

    @Transactional
    public void deleteCompraProducto(Long id) {
        compraProductoRepository.deleteById(id);
    }

    public Optional<CompraProducto> findCompraProductoByProducto(Producto producto) {
        return compraProductoRepository.findByProducto(producto);
    }

    public Optional<CompraProducto> findCompraProductoByCompra(Compra compra) {
        return compraProductoRepository.findByCompra(compra);
    }
}