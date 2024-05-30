package com.uade.tpo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.uade.tpo.entity.Descuentos;

@Repository
public interface DescuentosRepository extends JpaRepository<Descuentos, Long>{
    @Query(
        value = "SELECT * FROM descuentos d WHERE d.idDescuentos = ?1",
        nativeQuery=true
    )
    Optional<Descuentos> findbyDescuentosId(Long idDescuentos);
}