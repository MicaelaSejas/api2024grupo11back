package com.uade.tpo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.uade.tpo.entity.Carrito;
import com.uade.tpo.entity.CarritoProductos;
import com.uade.tpo.entity.Product;
import com.uade.tpo.entity.dto.CarritoRequest;
import com.uade.tpo.entity.dto.CarritoResponse;
import com.uade.tpo.exception.BadProductQuantityException;
import com.uade.tpo.exception.CartNotFoundException;
import com.uade.tpo.exception.ProductNotFoundException;
import com.uade.tpo.repository.CarritoProductosRepository;
import com.uade.tpo.repository.CarritoRepository;
import com.uade.tpo.repository.ProductoRepository;

import jakarta.transaction.Transactional;

@Service
public class CarritoService {


    @Autowired
    private CarritoRepository carritoRepository;
    
    @Autowired
    private ProductoRepository productoRepository;
    
    @Autowired
    private CarritoProductosRepository carritoProdRepository;

    public Carrito getCarritoById(final Long carritoId) throws CartNotFoundException {
    	return this.carritoRepository.findById(carritoId)
    			.orElseThrow(() -> new CartNotFoundException("Carrito with id " + carritoId + " not found"));
    }

    public Carrito getCarritoByUserId(final Long userId) throws CartNotFoundException {
        return this.carritoRepository.findByUserId(userId)
                .orElseThrow(() -> new CartNotFoundException("Carrito for user id " + userId + " not found"));
    }

    @Transactional
	public ResponseEntity<CarritoResponse> addToCarrito(final Long carritoId, final CarritoRequest request) throws CartNotFoundException, ProductNotFoundException {

    	// TODO: crear carrito si no tiene??
    	
    	Carrito carrito = getCarritoById(carritoId);
    	
    	int cantidad =  request.getCantidad();
    	
    	
    	Optional<Product> optProducto = productoRepository.findById(request.getProductoId());
    	
    	if (optProducto.isPresent()) {
    		
    		Product producto = optProducto.get();
    		
    		// si hay stock
    		if (producto.getCantidad() >= cantidad) {
    			producto.setCantidad(producto.getCantidad() - cantidad);
                
    			float total = carrito.getTotal() + (producto.getPrecio() * request.getCantidad());
                carrito.setTotal(total);

                CarritoProductos carritoProducto = new CarritoProductos();
                carritoProducto.setCarrito(carrito);
                carritoProducto.setProducto(producto);
                carritoProducto.setCantidad(cantidad);
                
                carrito.getCarritoProductos().add(carritoProducto);

                productoRepository.save(producto);
                carritoProdRepository.save(carritoProducto);
                carritoRepository.save(carrito);

                return ResponseEntity.ok(new CarritoResponse("Producto agregado exitosamente.", carrito));
    		} else {
                return ResponseEntity.badRequest().body(new CarritoResponse("No hay suficiente cantidad del producto en el inventario.", "BAD_REQUEST"));

    		}
    	} else {
    		throw new ProductNotFoundException("No se encontró el producto con id: " + request.getProductoId());
    	}
    	
	}


}
