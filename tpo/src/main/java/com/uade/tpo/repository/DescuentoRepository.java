package com.uade.tpo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uade.tpo.entity.Descuento;

@Repository
public interface DescuentoRepository extends JpaRepository<Descuento, Integer> {
}
