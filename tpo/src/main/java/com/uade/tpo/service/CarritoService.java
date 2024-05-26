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

    public Carrito getCarritoByUserId(final Long userId) throws CartNotFoundException{
        return this.carritoRepository.getCarritoByUserId(userId)
                .orElseThrow(() -> new CartNotFoundException("Carrito for user id " + userId + " not found"));
    }


}
