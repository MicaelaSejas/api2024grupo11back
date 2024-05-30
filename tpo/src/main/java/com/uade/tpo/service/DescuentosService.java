package com.uade.tpo.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.uade.tpo.entity.Descuentos;

public interface DescuentosService {

    public Page<Descuentos> getDescuentos(Pageable pageable);

    public Optional<Descuentos> getDescuentosById(Long idDescuentos);

    public Descuentos crearDescuentos(Descuentos descuentos);

    public Descuentos actualizarDescuentos(Long idDescuentos, Descuentos descuentosActualizados);

    public Descuentos eliminarDescuentos(Long idDescuentos);



}
