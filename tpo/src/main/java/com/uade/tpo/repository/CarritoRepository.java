package com.uade.tpo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.uade.tpo.entity.Carrito;

@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Long> {

    @Query(
            value = "SELECT * FROM Carrito c WHERE c.userId = ?1",
            nativeQuery = true)
    Optional<Carrito> findByUserId(Long id);

}
