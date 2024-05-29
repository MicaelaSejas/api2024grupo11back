package com.uade.tpo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.uade.tpo.entity.Product;

public interface ProductoService {
    public Page<Product> getProducts(PageRequest pageRequest);

    public Optional<Product> getProductById(Long id);

    public Product createProduct(Product product);

    public List<Product> getAllProduct();

    public Product eliminarProduct(Long id);

    public Product actualizarProductos(Long id, Product product);
}
