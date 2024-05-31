package com.uade.tpo.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.uade.tpo.entity.Rol;

import java.util.Optional;

@Repository
public interface RolesRepository extends JpaRepository<Rol, Long> {
    Optional<Rol> findById(Long id);
}
