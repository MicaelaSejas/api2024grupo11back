package com.uade.tpo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.tpo.exception.CartNotFoundException;
import com.uade.tpo.model.Carrito;
import com.uade.tpo.repository.CarritoRepository;

@Service
public class CarritoService {


    @Autowired
    public CarritoRepository carritoRepository;

    public Carrito getCarritoById(final Long id) throws CartNotFoundException{
        return this.carritoRepository.getCarritoById(id)
                .orElseThrow(() -> new CartNotFoundException("Carrito with id " + id + " not found"));
    }

}
