package com.uade.tpo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.uade.tpo.entity.Descuentos;

@Repository
public interface DescuentosRepository extends JpaRepository<Descuentos, Integer> {
    Optional<Descuentos> getDescuentosById(Integer idDescuentos);
}
