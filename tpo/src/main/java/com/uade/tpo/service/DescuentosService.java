package com.uade.tpo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.uade.tpo.entity.Descuentos;

public interface DescuentosService {

    public Page<Descuentos> getDescuentos(Pageable pageable);

    public Optional<Descuentos> getDescuentosById(Long idDescuentos);

    public Descuentos crearDescuentos(Descuentos descuentos);

    public List<Descuentos> getAllDescuentos();

    public Descuentos eliminarDescuentos(Long idDescuentos);

    public Descuentos actualizarDescuentos(Long idDescuentos, Descuentos descuentosActualizados);

}
