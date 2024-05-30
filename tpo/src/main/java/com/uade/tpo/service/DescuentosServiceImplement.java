package com.uade.tpo.service;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.uade.tpo.entity.Descuentos;
import com.uade.tpo.repository.DescuentosRepository;

@Service
public class DescuentosServiceImplement implements DescuentosService {

    @Autowired
    private DescuentosRepository descuentosRepository;

    @Override
    public Page<Descuentos> getDescuentos(Pageable pageable) {
        return descuentosRepository.findAll(pageable);
    }

    @Override
    public Optional<Descuentos> getDescuentosById(Long idDescuentos) {
        return descuentosRepository.findById(idDescuentos);
    }

    @Override
    public Descuentos crearDescuentos(Descuentos descuentos) {
        return descuentosRepository.save(descuentos);
    }

    @Override
    public Descuentos actualizarDescuentos(Long idDescuentos, Descuentos descuentosActualizados) {
        descuentosActualizados.setIdDescuentos(idDescuentos);
        return descuentosRepository.save(descuentosActualizados);
    }
    
    @Override
    public Descuentos eliminarDescuentos(Long idDescuentos) {
        Optional<Descuentos> descuentosOptional = descuentosRepository.findById(idDescuentos);
        if (descuentosOptional.isPresent()) {
            Descuentos descuentoEliminado = descuentosOptional.get();
            descuentosRepository.deleteById(idDescuentos);
            return descuentoEliminado;
        } else {
            throw new NoSuchElementException("El descuento no existe");
        }
    }

}
