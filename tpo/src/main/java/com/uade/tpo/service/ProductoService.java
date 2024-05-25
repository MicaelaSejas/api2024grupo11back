package com.uade.tpo.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.uade.tpo.entity.Product;
import com.uade.tpo.exception.ProductDuplicateException;

public interface ProductoService {
    public Page<Product> getProducts(PageRequest pageRequest);

    public Optional<Product> getProductById(Long productId);

    public Product createProduct(String nombre, int cantidad, int precio, String descripcion) throws ProductDuplicateException;
}
