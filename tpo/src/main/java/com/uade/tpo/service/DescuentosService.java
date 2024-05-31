package com.uade.tpo.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.uade.tpo.entity.Descuento;

public interface DescuentosService {

    public Page<Descuento> getDescuentos(Pageable pageable);

    public Optional<Descuento> getDescuentosById(Long idDescuentos);

    public Descuento crearDescuentos(Descuento descuentos);

    public Descuento actualizarDescuentos(Long idDescuentos, Descuento descuentosActualizados);

    public Descuento eliminarDescuentos(Long idDescuentos);



}
