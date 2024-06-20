package com.uade.tpo.service;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uade.tpo.controller.request.CarritoRequest;
import com.uade.tpo.controller.request.CarritoResponse;
import com.uade.tpo.entity.Carrito;
import com.uade.tpo.entity.CarritoProducto;
import com.uade.tpo.entity.Producto;
import com.uade.tpo.exception.CartAlreadyEmptyException;
import com.uade.tpo.exception.CartNotFoundException;
import com.uade.tpo.exception.ExceededCartQuantityException;
import com.uade.tpo.exception.ProductNotFoundException;
import com.uade.tpo.exception.ProductNotInCartException;
import com.uade.tpo.repository.CarritoProductoRepository;
import com.uade.tpo.repository.CarritoRepository;
import com.uade.tpo.repository.ProductoRepository;

@Service
public class CarritoServiceImpl implements CarritoService {

    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CarritoProductoRepository carritoProdRepository;

    @Override
    public Carrito getCarritoById(Long carritoId) throws CartNotFoundException {
        return carritoRepository.findById(carritoId)
                .orElseThrow(() -> new CartNotFoundException("Carrito con id " + carritoId + " no encontrado"));
    }

    @Transactional
    @Override
    public ResponseEntity<CarritoResponse> removeFromCarrito(Long carritoId, CarritoRequest request)
            throws CartNotFoundException, ProductNotInCartException {
        Carrito carrito = getCarritoById(carritoId);

        Optional<CarritoProducto> optCarritoProducto = carrito.getCarritoProducto().stream()
                .filter(cp -> cp.getProducto().getId().equals(request.getProductoId()))
                .findFirst();

        if (optCarritoProducto.isPresent()) {
            CarritoProducto carritoProducto = optCarritoProducto.get();
            int cantidadEliminada = carritoProducto.getCantidad();

            float nuevoTotal = carrito.getTotal()
                    - (carritoProducto.getProducto().getPrecio() * cantidadEliminada);
            carrito.setTotal(nuevoTotal);

            int nuevaCantidadProducto = carritoProducto.getProducto().getCantidad() + cantidadEliminada;
            carritoProducto.getProducto().setCantidad(nuevaCantidadProducto);

            carrito.getCarritoProducto().remove(carritoProducto);

            carritoRepository.save(carrito);
            carritoProdRepository.delete(carritoProducto);

            return ResponseEntity.ok(new CarritoResponse("Producto borrado del carrito exitosamente.", carrito));
        } else {
            throw new ProductNotInCartException("El producto no se encuentra en el carrito con id: " + carritoId);
        }
    }

    @Transactional
    @Override
    public ResponseEntity<CarritoResponse> addToCarrito(Long carritoId, CarritoRequest request)
            throws CartNotFoundException, ProductNotFoundException, ExceededCartQuantityException {
        Carrito carrito = getCarritoById(carritoId);
        int cantidad = request.getCantidad();

        Optional<Producto> optProducto = productoRepository.findById(request.getProductoId());

        if (optProducto.isPresent()) {
            Producto producto = optProducto.get();

            Optional<CarritoProducto> optCarritoProducto = carrito.getCarritoProducto().stream()
                    .filter(cp -> cp.getProducto().getId().equals(producto.getId()))
                    .findFirst();

            
            if (optCarritoProducto.isPresent()) {
                CarritoProducto carritoProducto = optCarritoProducto.get();
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
                    throw new ExceededCartQuantityException("No hay suficiente stock de ese producto");
                }

            } else {
                // Si el producto no está en el carrito
                if (producto.getCantidad() >= cantidad) {
                    producto.setCantidad(producto.getCantidad() - cantidad);

                    float total = carrito.getTotal() + (producto.getPrecio() * cantidad);
                    carrito.setTotal(total);

                    CarritoProducto carritoProducto = new CarritoProducto();
                    carritoProducto.setCarrito(carrito);
                    carritoProducto.setProducto(producto);
                    carritoProducto.setCantidad(cantidad);
                    carrito.getCarritoProducto().add(carritoProducto);
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
    @Override
    public ResponseEntity<CarritoResponse> emptyCarrito(Long carritoId)
            throws CartNotFoundException, CartAlreadyEmptyException {
        Carrito carrito = getCarritoById(carritoId);

        if (carrito.getCarritoProducto().isEmpty()) {
            throw new CartAlreadyEmptyException("El carrito ya está vacío");
        }

        Iterator<CarritoProducto> iterator = carrito.getCarritoProducto().iterator();
        while (iterator.hasNext()) {
            CarritoProducto carritoProducto = iterator.next();
            iterator.remove();

            Producto producto = carritoProducto.getProducto();

            int cantidadActual = producto.getCantidad();
            int cantidadEnCarrito = carritoProducto.getCantidad();

            producto.setCantidad(cantidadActual + cantidadEnCarrito);

            productoRepository.save(producto);
            carritoProdRepository.delete(carritoProducto);
        }

        carrito.setTotal(0);

        carritoRepository.save(carrito);

        return ResponseEntity.ok(new CarritoResponse("Carrito vaciado con éxito.", ""));
    }

    @Override
    public ResponseEntity<CarritoResponse> substractFromCarrito(Long carritoId, CarritoRequest request)
            throws CartNotFoundException, ProductNotFoundException, ProductNotInCartException,
            ExceededCartQuantityException {
        Carrito carrito = getCarritoById(carritoId);
        int cantidad = request.getCantidad();

        Optional<Producto> optProducto = productoRepository.findById(request.getProductoId());

        if (optProducto.isPresent()) {
            Producto producto = optProducto.get();

            Optional<CarritoProducto> optCarritoProducto = carrito.getCarritoProducto().stream()
                    .filter(cp -> cp.getProducto().getId().equals(producto.getId()))
                    .findFirst();

            if (optCarritoProducto.isPresent()) {
                CarritoProducto carritoProducto = optCarritoProducto.get();

                int nuevaCantidadDelCarrito = carritoProducto.getCantidad() - cantidad;
                float total = 0.0f;

                if (nuevaCantidadDelCarrito < 0) {
                    throw new ExceededCartQuantityException(
                            "La cantidad a eliminar excede la cantidad del carrito.");
                } else if (nuevaCantidadDelCarrito == 0) {
                    carrito.getCarritoProducto().remove(carritoProducto);
                    carritoProdRepository.delete(carritoProducto);
                } else {
                    carritoProducto.setCantidad(nuevaCantidadDelCarrito);
                }
                total = carrito.getTotal() - (producto.getPrecio() * cantidad);

                producto.setCantidad(producto.getCantidad() + cantidad);

                productoRepository.save(producto);
                carritoRepository.save(carrito);
                return ResponseEntity.ok(new CarritoResponse("Cantidad de producto en el carrito actualizada.", carrito));
            } else {
                throw new ProductNotInCartException("El producto no se encuentra en el carrito con id: " + carritoId);
            }
        } else {
            throw new ProductNotFoundException("No se encontró el producto con id: " + request.getProductoId());
        }
    }

    @Override
    public Carrito saveCarrito(Carrito carrito) {
        return carritoRepository.save(carrito);
    }

    @Override
    public Carrito updateCarrito(Long carritoId, Carrito carrito) {
        if (carritoRepository.existsById(carritoId)) {
            carrito.setId(carritoId);
            return carritoRepository.save(carrito);
        } else {
            throw new NoSuchElementException("No se encontró el carrito con ID: " + carritoId);
        }
    }
    



    @Override
    public void deleteCarrito(Long carritoId) {
        carritoRepository.deleteById(carritoId);
    }
}
