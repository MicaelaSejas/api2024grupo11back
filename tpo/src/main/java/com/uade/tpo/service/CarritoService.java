package com.uade.tpo.service;

import org.springframework.http.ResponseEntity;

import com.uade.tpo.controller.request.CarritoRequest;
import com.uade.tpo.controller.request.CarritoResponse;
import com.uade.tpo.entity.Carrito;
import com.uade.tpo.exception.CartAlreadyEmptyException;
import com.uade.tpo.exception.CartNotFoundException;
import com.uade.tpo.exception.ExceededCartQuantityException;
import com.uade.tpo.exception.ProductNotFoundException;
import com.uade.tpo.exception.ProductNotInCartException;

public interface CarritoService {

	public Carrito getCarritoById(final Long carritoId) throws CartNotFoundException;
	
	public ResponseEntity<CarritoResponse> removeFromCarrito(final Long carritoId, final CarritoRequest request)
            throws CartNotFoundException, ProductNotInCartException;
	
	public ResponseEntity<CarritoResponse> addToCarrito(final Long carritoId, final CarritoRequest request)
            throws CartNotFoundException, ProductNotFoundException, ExceededCartQuantityException;

	public ResponseEntity<CarritoResponse> emptyCarrito(Long carritoId)
            throws CartNotFoundException, CartAlreadyEmptyException;
	
	public ResponseEntity<CarritoResponse> substractFromCarrito(Long carritoId, CarritoRequest request)
            throws CartNotFoundException, ProductNotFoundException, ProductNotInCartException,
            ExceededCartQuantityException;
}
