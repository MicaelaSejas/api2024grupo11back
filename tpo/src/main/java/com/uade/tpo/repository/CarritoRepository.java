package com.uade.tpo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uade.tpo.entity.Carrito;
import com.uade.tpo.entity.Usuario;

@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Long> {


    Optional<Carrito> findByUsuario(Usuario usuario);

}
