package com.uade.tpo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.uade.tpo.entity.Descuento;

@Repository
public interface DescuentoRepository extends JpaRepository<Descuento, Long> {
    @Query(value ="select c from Descuento c where c.descuento= ?1")
    List<Descuento> findByDescuento(int descuento);
}
