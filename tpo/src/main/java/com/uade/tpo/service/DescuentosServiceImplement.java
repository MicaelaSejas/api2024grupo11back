package com.uade.tpo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.uade.tpo.entity.Descuentos;
import com.uade.tpo.repository.DescuentosRepository;

@Service
public class DescuentosServiceImplement implements DescuentosService {

    @Autowired
    private DescuentosRepository descuentosRepository;

    @Override
    public Page<Descuentos> getDescuentos(PageRequest pageable) {
        return descuentosRepository.findAll(pageable);
        throw new UnsupportedOperationException("Unimplemented method 'getDescuentos'");
    }

    @Override
    public Optional<Descuentos> getDescuentosById(Integer idDescuentos) {
        return descuentosRepository.findById(idDescuentos).orElse(null);
        throw new UnsupportedOperationException("Unimplemented method 'getDescuentosById'");
    }

    @Override
    public Descuentos crearDescuentos(Integer idDescuentos) {
        List<Descuentos> descuentos = descuentosRepository.findById(idDescuentos);
        if (descuentos.isEmpty())
            return descuentosRepository.save(new Descuentos(idDescuentos));
        throw new UnsupportedOperationException("Unimplemented method 'crearDescuentos'");
    }

    @Override
    public List<Descuentos> getAllDescuentos() {
        return descuentosRepository.findAll();
        throw new UnsupportedOperationException("Unimplemented method 'getAllDescuentos'");
    }

    @Override
    public Descuentos eliminarDescuentos(Integer idDescuentos) {
        descuentosRepository.deleteById(idDescuentos);
        throw new UnsupportedOperationException("Unimplemented method 'eliminarDescuentos'");
    }

    @Override
    public Descuentos modificarDescuentos(Integer idDescuentos) {
        return descuentosRepository.save(idDescuentos);
        throw new UnsupportedOperationException("Unimplemented method 'modificarDescuentos'");
    }

}
