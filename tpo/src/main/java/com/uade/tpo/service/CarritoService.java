package com.uade.tpo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.tpo.entity.Carrito;
import com.uade.tpo.entity.dto.CarritoRequest;
import com.uade.tpo.exception.CartNotFoundException;
import com.uade.tpo.repository.CarritoRepository;

@Service
public class CarritoService {


    @Autowired
    public CarritoRepository carritoRepository;

    public Carrito getCarritoById(final Long carritoId) throws CartNotFoundException {
    	return this.carritoRepository.findById(carritoId)
    			.orElseThrow(() -> new CartNotFoundException("Carrito with id " + carritoId + " not found"));
    }

    public Carrito getCarritoByUserId(final Long userId) throws CartNotFoundException {
        return this.carritoRepository.findByUserId(userId)
                .orElseThrow(() -> new CartNotFoundException("Carrito for user id " + userId + " not found"));
    }

	public void addToCarrito(final Long carritoId, final CarritoRequest request) throws CartNotFoundException {

        Carrito carrito = getCarritoById(carritoId);

        // TODO:
        // - chequear si es conveniente hacer un fetch con el id
        // o si es conveniente ya traer el producto en la request con su cantidad
        // o si solo guardar los id
        // o si crear entidad producto carrito y mapear ahi y luego ponerlo en el carrito
        // - retrieve product y check si hay stock ?
        // - si no hay stock -> exception que se catchea en front
        // - si hay stock


	}


}
