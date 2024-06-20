package com.uade.tpo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uade.tpo.entity.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {


}
