package com.uade.tpo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.uade.tpo.controller.request.FavoritoRequest;
import com.uade.tpo.controller.request.FavoritoResponse;
import com.uade.tpo.entity.Favorito;
import com.uade.tpo.entity.FavoritoProductos;
import com.uade.tpo.entity.Producto;
import com.uade.tpo.exception.FavNotFoundException;
import com.uade.tpo.exception.ProductInFavsException;
import com.uade.tpo.repository.FavoritoProductosRepository;
import com.uade.tpo.repository.FavoritoRepository;
import com.uade.tpo.repository.ProductoRepository;

import jakarta.transaction.Transactional;

@Service
public class FavoritoServiceImpl implements FavoritoService {

    @Autowired
    private FavoritoRepository favoritoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private FavoritoProductosRepository favoritoProdRepository;

    @Transactional
    public ResponseEntity<FavoritoResponse> removeFromFavorito(final Long userId, final FavoritoRequest request)
            throws FavNotFoundException, ProductInFavsException {
        Favorito favorito = getFavoritoByUserId(userId);

        Optional<FavoritoProductos> optFavoritoProducto = favorito.getFavoritoProductos().stream()
                .filter(cp -> cp.getProducto().getId().equals(request.getProductoId()))
                .findFirst();

        if (optFavoritoProducto.isPresent()) {
            FavoritoProductos favoritoProducto = optFavoritoProducto.get();

            favorito.getFavoritoProductos().remove(favoritoProducto);

            favoritoRepository.save(favorito);
            favoritoProdRepository.delete(favoritoProducto);

            return ResponseEntity.ok(new FavoritoResponse("Producto borrado del carrito exitosamente.", favorito));
        } else {
            throw new ProductInFavsException("El producto no se encuentra en el carrito con id: " + userId);
        }

    }

    @Transactional
    public ResponseEntity<FavoritoResponse> addToFavorito(final Long userId, final FavoritoRequest request)
            throws FavNotFoundException, ProductInFavsException {

        Favorito favorito = getFavoritoByUserId(userId);

        Optional<Producto> optProducto = productoRepository.findById(request.getProductoId());

        if (optProducto.isPresent()) {
            Producto producto = optProducto.get();

            Optional<FavoritoProductos> optFavoritoProducto = favorito.getFavoritoProductos().stream()
                    .filter(cp -> cp.getProducto().getId().equals(producto.getId()))
                    .findFirst();

            if (optFavoritoProducto.isPresent()) {
                return ResponseEntity.badRequest().body(new FavoritoResponse(
                        "El producto ya está en favoritos", "BAD_REQUEST"));
            }

            FavoritoProductos favoritoProducto = new FavoritoProductos();
            favoritoProducto.setFavorito(favorito);
            favoritoProducto.setProducto(producto);
            favorito.getFavoritoProductos().add(favoritoProducto);
            favoritoProdRepository.save(favoritoProducto);
            favoritoRepository.save(favorito);

            return ResponseEntity.ok(new FavoritoResponse("Producto agregado exitosamente.", favorito));
        } else {
            return ResponseEntity.badRequest().body(new FavoritoResponse(
                    "Error al agregar producto a favoritos", "BAD_REQUEST"));
        }
    }

    @Override
    public Favorito getFavoritoByUserId(final Long userId) throws FavNotFoundException {
        return favoritoRepository.findByUserId(userId).orElseThrow();
    }

}