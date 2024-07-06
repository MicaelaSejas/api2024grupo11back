package com.uade.tpo.service;

import java.util.Iterator;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.uade.tpo.controller.request.CarritoRequest;
import com.uade.tpo.controller.request.CarritoResponse;
import com.uade.tpo.entity.Carrito;
import com.uade.tpo.entity.CarritoProductos;
import com.uade.tpo.entity.Producto;
import com.uade.tpo.exception.CartAlreadyEmptyException;
import com.uade.tpo.exception.CartNotFoundException;
import com.uade.tpo.exception.ExceededCartQuantityException;
import com.uade.tpo.exception.ProductNotFoundException;
import com.uade.tpo.exception.ProductNotInCartException;
import com.uade.tpo.repository.CarritoProductosRepository;
import com.uade.tpo.repository.CarritoRepository;
import com.uade.tpo.repository.ProductoRepository;

import jakarta.transaction.Transactional;

@Service
public class CarritoServiceImpl implements CarritoService {

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

    @Transactional
    public ResponseEntity<CarritoResponse> removeFromCarrito(final Long carritoId, final CarritoRequest request)
            throws CartNotFoundException, ProductNotInCartException {
        Carrito carrito = getCarritoById(carritoId);

        Optional<CarritoProductos> optCarritoProducto = carrito.getCarritoProductos().stream()
                .filter(cp -> cp.getProducto().getId().equals(request.getProductoId()))
                .findFirst();

        if (optCarritoProducto.isPresent()) {
            CarritoProductos carritoProducto = optCarritoProducto.get();
            int cantidadEliminada = carritoProducto.getCantidad();

            double nuevoTotal = carrito.getTotal()
                    - (carritoProducto.getProducto().getPrecioConDescuento() * cantidadEliminada);
            carrito.setTotal(nuevoTotal);

            int nuevaCantidadProducto = carritoProducto.getProducto().getCantidad() + cantidadEliminada;
            carritoProducto.getProducto().setCantidad(nuevaCantidadProducto);

            carrito.getCarritoProductos().remove(carritoProducto);

            carritoRepository.save(carrito);
            carritoProdRepository.delete(carritoProducto);

            return ResponseEntity.ok(new CarritoResponse("Producto borrado del carrito exitosamente.", carrito));
        } else {
            throw new ProductNotInCartException("El producto no se encuentra en el carrito con id: " + carritoId);
        }

    }

    @Transactional
    public ResponseEntity<CarritoResponse> addToCarrito(final Long carritoId, final CarritoRequest request)
            throws CartNotFoundException, ProductNotFoundException, ExceededCartQuantityException {

        Carrito carrito = getCarritoById(carritoId);
        int cantidad = request.getCantidad();

        Optional<Producto> optProducto = productoRepository.findById(request.getProductoId());

        if (optProducto.isPresent()) {
            Producto producto = optProducto.get();

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

                    double total = carrito.getTotal() + (producto.getPrecioConDescuento() * cantidad);
                    carrito.setTotal(total);

                    productoRepository.save(producto);
                    carritoRepository.save(carrito);
                    return ResponseEntity
                            .ok(new CarritoResponse("Cantidad de producto en el carrito actualizada.", carrito));
                } else {
                    throw new ExceededCartQuantityException("No hay suficiente stock de ese producto");
                }

            } else {
                // Si el producto no está en el carrito
                if (producto.getCantidad() >= cantidad) {
                    producto.setCantidad(producto.getCantidad() - cantidad);

                    double total = carrito.getTotal() + (producto.getPrecioConDescuento() * cantidad);
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
                    return ResponseEntity.badRequest().body(new CarritoResponse(
                            "No hay suficiente cantidad del producto en el inventario.", "BAD_REQUEST"));
                }
            }
        } else {
            throw new ProductNotFoundException("No se encontró el producto con id: " + request.getProductoId());
        }
    }

    @Transactional
    public ResponseEntity<CarritoResponse> emptyCarrito(Long carritoId)
            throws CartNotFoundException, CartAlreadyEmptyException {
        Carrito carrito = getCarritoById(carritoId);

        if (carrito.getCarritoProductos().isEmpty()) {
            throw new CartAlreadyEmptyException("El carrito ya está vacio");
        }

        Iterator<CarritoProductos> iterator = carrito.getCarritoProductos().iterator();
        while (iterator.hasNext()) {
            CarritoProductos carritoProducto = iterator.next();
            iterator.remove();

            Producto producto = carritoProducto.getProducto();

            int cantidadActual = producto.getCantidad();
            int cantidadEnCarrito = carritoProducto.getCantidad();

            producto.setCantidad(cantidadActual + cantidadEnCarrito);

            productoRepository.save(producto);
            carritoProdRepository.delete(carritoProducto);
        }

        carrito.setTotal(0);

        this.carritoRepository.save(carrito);

        return ResponseEntity.ok(new CarritoResponse("Carrito vaciado con éxito.", ""));
    }

    @Transactional
    public ResponseEntity<CarritoResponse> substractFromCarrito(Long carritoId, CarritoRequest request)
            throws CartNotFoundException, ProductNotFoundException, ProductNotInCartException,
            ExceededCartQuantityException {

        Carrito carrito = getCarritoById(carritoId);
        int cantidad = request.getCantidad();

        Optional<Producto> optProducto = productoRepository.findById(request.getProductoId());

        if (optProducto.isPresent()) {
            Producto producto = optProducto.get();

            Optional<CarritoProductos> optCarritoProducto = carrito.getCarritoProductos().stream()
                    .filter(cp -> cp.getProducto().getId().equals(producto.getId()))
                    .findFirst();

            if (optCarritoProducto.isPresent()) {
                CarritoProductos carritoProducto = optCarritoProducto.get();

                int nuevaCantidadDelCarrito = carritoProducto.getCantidad() - cantidad;
                double total = 0;

                if (nuevaCantidadDelCarrito < 0) {
                    throw new ExceededCartQuantityException(
                            "La cantidad a eliminar excede el la cantidad del carrito.");
                } else if (nuevaCantidadDelCarrito == 0) {
                    carrito.getCarritoProductos().remove(carritoProducto);
                    carritoProdRepository.delete(carritoProducto);
                } else {
                    carritoProducto.setCantidad(nuevaCantidadDelCarrito);
                }
                total = carrito.getTotal() - (producto.getPrecioConDescuento() * cantidad);

                producto.setCantidad(producto.getCantidad() + cantidad);

                carrito.setTotal(total);

                productoRepository.save(producto);
                carritoRepository.save(carrito);
                return ResponseEntity
                        .ok(new CarritoResponse("Cantidad de producto en el carrito actualizada.", carrito));
            } else {
                throw new ProductNotInCartException("El producto no se encuentra en el carrito con id: " + carritoId);
            }
        } else {
            throw new ProductNotFoundException("No se encontró el producto con id: " + request.getProductoId());
        }
    }

}