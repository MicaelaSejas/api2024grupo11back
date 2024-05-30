package com.uade.tpo.service;

import java.util.Collections;
import java.util.Iterator;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.uade.tpo.controller.request.CarritoRequest;
import com.uade.tpo.controller.request.CarritoResponse;
import com.uade.tpo.entity.Carrito;
import com.uade.tpo.entity.CarritoProductos;
import com.uade.tpo.entity.Product;
import com.uade.tpo.exception.BadProductQuantityException;
import com.uade.tpo.exception.CartNotFoundException;
import com.uade.tpo.exception.ProductNotFoundException;
import com.uade.tpo.exception.ProductNotInCartException;
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
    			.orElseThrow(() -> new CartNotFoundException("Carrito con id " + carritoId + " no encontrado"));
    }

    // TODO:
    // check if product quanity is updated
    
    @Transactional
    public ResponseEntity<CarritoResponse> removeFromCarrito(final Long carritoId, final CarritoRequest request)  throws CartNotFoundException, ProductNotInCartException {
        Carrito carrito = getCarritoById(carritoId);
        
        Optional<CarritoProductos> optCarritoProducto = carrito.getCarritoProductos().stream()
                .filter(cp -> cp.getProducto().getId().equals(request.getProductoId()))
                .findFirst();
        
        if (optCarritoProducto.isPresent()) {
        	int cantidadEliminada = optCarritoProducto.get().getCantidad();

        	float nuevoTotal = carrito.getTotal() - (optCarritoProducto.get().getProducto().getPrecio() * cantidadEliminada);
            carrito.setTotal(nuevoTotal);
        	
            carrito.getCarritoProductos().remove(optCarritoProducto.get());
            
            carritoProdRepository.delete(optCarritoProducto.get());
            carritoRepository.save(carrito);
            
            return ResponseEntity.ok(new CarritoResponse("Producto borrado del carrito exitosamente.", carrito));
        } else {
        	throw new ProductNotInCartException("El producto no se encuentra en el carrito con id: " + carritoId);
        }

    }
    
    @Transactional
    public ResponseEntity<CarritoResponse> addToCarrito(final Long carritoId, final CarritoRequest request) throws CartNotFoundException, ProductNotFoundException {

        Carrito carrito = getCarritoById(carritoId);
        int cantidad = request.getCantidad();

        Optional<Product> optProducto = productoRepository.findById(request.getProductoId());

        if (optProducto.isPresent()) {
            Product producto = optProducto.get();

            // El producto ya está en el carrito
            Optional<CarritoProductos> optCarritoProducto = carrito.getCarritoProductos().stream()
                    .filter(cp -> cp.getProducto().getId().equals(producto.getId()))
                    .findFirst();

            // Si el producto ya está en el carrito
            if (optCarritoProducto.isPresent()) {
                CarritoProductos carritoProducto = optCarritoProducto.get();
                int nuevaCantidad = carritoProducto.getCantidad() + cantidad;
                
                if (producto.getCantidad() >= cantidad) {
                    producto.setCantidad(producto.getCantidad() - cantidad);
                    carritoProducto.setCantidad(nuevaCantidad);
                    
                    float total = carrito.getTotal() + (producto.getPrecio() * cantidad);
                    carrito.setTotal(total);
                    
                    productoRepository.save(producto);
                    carritoRepository.save(carrito);
                    return ResponseEntity.ok(new CarritoResponse("Cantidad de producto en el carrito actualizada.", carrito));
                } else {
                    return ResponseEntity.badRequest().body(new CarritoResponse("No hay suficiente cantidad del producto en el inventario.", "BAD_REQUEST"));
                }
                
            } else {
                // Si el producto no está en el carrito
                if (producto.getCantidad() >= cantidad) {
                    producto.setCantidad(producto.getCantidad() - cantidad);

                    float total = carrito.getTotal() + (producto.getPrecio() * cantidad);
                    carrito.setTotal(total);

                    CarritoProductos carritoProducto = new CarritoProductos();
                    carritoProducto.setCarrito(carrito);
                    carritoProducto.setProducto(producto);
                    carritoProducto.setCantidad(cantidad);
                    carrito.getCarritoProductos().add(carritoProducto);
                    carritoProdRepository.save(carritoProducto);

                    productoRepository.save(producto);
                    carritoRepository.save(carrito);

                    return ResponseEntity.ok(new CarritoResponse("Producto agregado exitosamente.", carrito));
                } else {
                    return ResponseEntity.badRequest().body(new CarritoResponse("No hay suficiente cantidad del producto en el inventario.", "BAD_REQUEST"));
                }
            }
        } else {
            throw new ProductNotFoundException("No se encontró el producto con id: " + request.getProductoId());
        }
    }

    
    // TODO:
    // check if product quanity is updated
    
    @Transactional
	public ResponseEntity<CarritoResponse> emptyCarrito(Long carritoId) throws CartNotFoundException {
		Carrito carrito = getCarritoById(carritoId);
		
		carrito.setTotal(0);
		
        Iterator<CarritoProductos> iterator = carrito.getCarritoProductos().iterator();
        while (iterator.hasNext()) {
            CarritoProductos carritoProducto = iterator.next();
            iterator.remove(); 
            carritoProdRepository.delete(carritoProducto);
        }

        
		this.carritoRepository.save(carrito);
		
		return ResponseEntity.ok(new CarritoResponse("Carrito vaciado con éxito.", ""));
	}

	public ResponseEntity<CarritoResponse> substractFromCarrito(Long carritoId, CarritoRequest request) throws CartNotFoundException, ProductNotFoundException{
		// TODO Auto-generated method stub
		return null;
	}


}
