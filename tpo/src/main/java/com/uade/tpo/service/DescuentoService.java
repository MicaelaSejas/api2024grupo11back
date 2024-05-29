package com.uade.tpo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.uade.tpo.entity.Descuento;

public interface DescuentoService {
    public Page<Descuento> getDescuento(PageRequest pageRequest);

    public Optional<Descuento> getDescuentoById(Long descuentoId);

    public Descuento createDescuento(Descuento descuento);

    public List<Descuento> getAllDescuento();

    public Descuento eliminarDescuento(Long id);

    public Descuento actualizarDescuento(Long id, Descuento descuentoActualizado);
}
