package com.uade.tpo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.uade.tpo.entity.Product;

@Repository
public class ProductoRepository {

    @Query(value ="select c from Product c where c.nombre= ?1")
    List<Product> findByNombre(String nombre);
}
