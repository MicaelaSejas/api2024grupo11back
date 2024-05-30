package com.uade.tpo.service;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.uade.tpo.entity.Descuento;
import com.uade.tpo.repository.DescuentosRepository;

@Service
public class DescuentosServiceImplement implements DescuentosService {

    @Autowired
    private DescuentosRepository descuentosRepository;

    @Override
    public Page<Descuento> getDescuentos(Pageable pageable) {
        return descuentosRepository.findAll(pageable);
    }

    @Override
    public Optional<Descuento> getDescuentosById(Long idDescuentos) {
        return descuentosRepository.findById(idDescuentos);
    }

    @Override
    public Descuento crearDescuentos(Descuento descuentos) {
        return descuentosRepository.save(descuentos);
    }

    @Override
    public Descuento actualizarDescuentos(Long idDescuentos, Descuento descuentosActualizados) {
        descuentosActualizados.setIdDescuentos(idDescuentos);
        return descuentosRepository.save(descuentosActualizados);
    }
    
    @Override
    public Descuento eliminarDescuentos(Long idDescuentos) {
        Optional<Descuento> descuentosOptional = descuentosRepository.findById(idDescuentos);
        if (descuentosOptional.isPresent()) {
            Descuento descuentoEliminado = descuentosOptional.get();
            descuentosRepository.deleteById(idDescuentos);
            return descuentoEliminado;
        } else {
            throw new NoSuchElementException("El descuento no existe");
        }
    }

}
