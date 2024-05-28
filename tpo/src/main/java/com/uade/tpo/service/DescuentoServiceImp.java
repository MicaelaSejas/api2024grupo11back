package com.uade.tpo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.uade.tpo.entity.Descuento;
import com.uade.tpo.exception.ProductDuplicateException;
import com.uade.tpo.repository.DescuentoRepository;

@Service
public class DescuentoServiceImp {
    @Autowired
    private DescuentoRepository descuentoRepository;

    public Page<Descuento> getProducts(PageRequest pageable){
        return descuentoRepository.findAll(pageable);
    }

    public Optional<Descuento> getDescuentoById(Long descuentoId){
        return descuentoRepository.findById(descuentoId);
    }

    public Descuento createDescuento(int descuento) throws ProductDuplicateException {
        List<Descuento> descuentos = descuentoRepository.findByDescuento(descuento);
        if (descuentos.isEmpty())
            return descuentoRepository.save(new Descuento(descuento));
        throw new ProductDuplicateException();
    }
}
