package com.uade.tpo.service;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uade.tpo.controller.request.CompraRequest;
import com.uade.tpo.controller.request.CompraRequest.ProductoCompraRequest;
import com.uade.tpo.entity.Compra;
import com.uade.tpo.entity.CompraProducto;
import com.uade.tpo.entity.Producto;
import com.uade.tpo.entity.Usuario;
import com.uade.tpo.exception.ProductNotFoundException;
import com.uade.tpo.repository.CompraRepository;
import com.uade.tpo.repository.ProductoRepository;
import com.uade.tpo.repository.UsuarioRepository;

@Service
public class CompraService {

    @Autowired
    private CompraRepository compraRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private ProductoRepository prodRepository;

    public List<Compra> findAllCompras() {
        return compraRepository.findAll();
    }

    public Optional<Compra> findCompraById(Long id) {
        return compraRepository.findById(id);
    }

    @Transactional
    public Compra createCompra(CompraRequest req) throws ProductNotFoundException {
    	Compra compra = new Compra();
    	Optional<Usuario> usuario = usuarioRepository.findById(req.getUsuarioId());
    	if (usuario.isPresent()) {
    		compra.setUsuario(usuario.get());
    		compra.setPrecioTotal(req.getTotal());
    		
    		Set<CompraProducto> setCompraProducto = new HashSet<>();
    		
    		for(ProductoCompraRequest prod: req.getProductos()) {
    			CompraProducto cp = new CompraProducto();
    			Optional<Producto> producto = prodRepository.findById(prod.getProductoId());
    			if (producto.isPresent()) {
    				cp.setProducto(producto.get());
    				cp.setCantidad(prod.getCantidad());
    				cp.setCompra(compra);
    			} else {
    				throw new ProductNotFoundException("No se encontro el producto con id " + prod.getProductoId());
    			}
    			setCompraProducto.add(cp);
    		}
    		compra.setCompraProductos(setCompraProducto);
    		
    		compraRepository.save(compra);
    		
    		return compra;
    	} else {
    		throw new UsernameNotFoundException("No se encontro el usuario con id " + req.getUsuarioId());
    	}
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

    public Set<Compra> findCompraByUsuario(Long usuarioId) {
        return compraRepository.findAllByUsuarioId(usuarioId);
    }
}