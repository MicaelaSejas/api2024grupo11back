package com.uade.tpo.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.uade.tpo.entity.Descuento;
import com.uade.tpo.exception.ProductDuplicateException;

public interface DescuentoService {
    public Page<Descuento> getDescuentos(PageRequest pageRequest);

    public Optional<Descuento> getDescuentoById(Long descuentoId);

    public Descuento createDescuento(int Porcentaje) throws ProductDuplicateException;
}
