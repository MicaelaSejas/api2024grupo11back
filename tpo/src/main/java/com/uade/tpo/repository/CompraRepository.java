package com.uade.tpo.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.uade.tpo.entity.Compra;
import com.uade.tpo.entity.Usuario;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {

    Optional<Compra> findByUsuario(Usuario usuario);
    
    @Query(value="SELECT * FROM compra where idUsuario=?1", nativeQuery=true)
    Set<Compra> findAllByUsuarioId(Long usuarioId);

}