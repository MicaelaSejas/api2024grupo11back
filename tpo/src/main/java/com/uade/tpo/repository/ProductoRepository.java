package com.uade.tpo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.uade.tpo.entity.Product;

@Repository
public interface ProductoRepository extends JpaRepository<Product, Long>{

    @Query(value ="select c from Product c where c.titulo= ?1")
    List<Product> findByTitulo(String nombre);
}
