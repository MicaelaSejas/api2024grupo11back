package com.uade.tpo.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.uade.tpo.entity.Product;
import com.uade.tpo.repository.ProductoRepository;

@Service
public class ProductServiceImp implements ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    public Page<Product> getProducts(PageRequest pageable){
        return productoRepository.findAll(pageable);
    }

    public Optional<Product> getProductById(Long productId){
        return productoRepository.findById(productId);
    }

    public Product createProduct(Product product) {
        Optional<Product> listaProductos = productoRepository.findById(product.getId());
        if (listaProductos.isEmpty()){
            return productoRepository.save(product);
        }else{
            throw new IllegalStateException("El producto ya existe");
        }
    }

    public List<Product> getAllProduct() {
        return productoRepository.findAll();
    }

    public Product eliminarProduct(Long id) {
        Optional<Product> productoOptional = productoRepository.findById(id);
        if(productoOptional.isPresent()){
            Product productoEliminado = productoOptional.get();
            productoRepository.deleteById(id);
            return productoEliminado;
        }else{
            throw new NoSuchElementException("El producto no existe");
        }
    }

    public Product actualizarProductos(Long id, Product productosActualizados) {
        productosActualizados.setId(id);
        return productoRepository.save(productosActualizados);
    }
}
