package com.uade.tpo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.uade.tpo.entity.Product;
import com.uade.tpo.exception.ProductDuplicateException;
import com.uade.tpo.repository.ProductoRepository;

@Service
public class ProductServiceImp {
    @Autowired
    private ProductoRepository productoRepository;

    public Page<Product> getProducts(PageRequest pageable){
        return productoRepository.findAll(pageable);
    }

    public Optional<Product> getProductById(Long productId){
        return productoRepository.findById(productId);
    }

    public Product createProduct(String titulo, int cantidad, int precio, String descripcion) throws ProductDuplicateException {
        List<Product> productos = productoRepository.findByTitulo(titulo);
        if (productos.isEmpty())
            return productoRepository.save(new Product(titulo, cantidad, precio, descripcion));
        throw new ProductDuplicateException();
    }
}
