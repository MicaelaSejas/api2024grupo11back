package com.uade.tpo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uade.tpo.entity.FavoritoProductos;

@Repository
public interface FavoritoProductosRepository extends JpaRepository<FavoritoProductos, Long> {

}
