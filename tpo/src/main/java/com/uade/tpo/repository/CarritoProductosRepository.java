package com.uade.tpo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uade.tpo.entity.CarritoProductos;
@Repository

public interface  CarritoProductosRepository extends JpaRepository<CarritoProductos, Long>{


}
