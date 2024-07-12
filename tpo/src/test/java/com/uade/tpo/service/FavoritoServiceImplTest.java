package com.uade.tpo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.uade.tpo.controller.request.FavoritoRequest;
import com.uade.tpo.controller.request.FavoritoResponse;
import com.uade.tpo.entity.Favorito;
import com.uade.tpo.entity.FavoritoProductos;
import com.uade.tpo.entity.Producto;
import com.uade.tpo.exception.ProductInFavsException;
import com.uade.tpo.repository.FavoritoProductosRepository;
import com.uade.tpo.repository.FavoritoRepository;
import com.uade.tpo.repository.ProductoRepository;

@ExtendWith(MockitoExtension.class)
public class FavoritoServiceImplTest {

    @Mock
    private FavoritoRepository favoritoRepository;

    @Mock
    private ProductoRepository productoRepository;

    @Mock
    private FavoritoProductosRepository favoritoProdRepository;

    @InjectMocks
    private FavoritoServiceImpl favoritoService;

    private Favorito favorito;
    private Producto producto;
    private FavoritoProductos favoritoProducto;

    @BeforeEach
    void setUp() {
        producto = new Producto();
        producto.setId(1L);

        favoritoProducto = new FavoritoProductos();
        favoritoProducto.setProducto(producto);

        favorito = new Favorito();
        favorito.setId(1L);
        favorito.getFavoritoProductos().add(favoritoProducto);
    }

    @Test
    void testRemoveFromFavorito() throws Exception {
        Long userId = 1L;
        FavoritoRequest request = new FavoritoRequest();
        request.setProductoId(1L);

        when(favoritoRepository.findByUserId(userId)).thenReturn(Optional.of(favorito));

        ResponseEntity<FavoritoResponse> response = favoritoService.removeFromFavorito(userId, request);

        assertEquals("Producto borrado del carrito exitosamente.", response.getBody().getMessage());
        verify(favoritoRepository, times(1)).save(any(Favorito.class));
        verify(favoritoProdRepository, times(1)).delete(any(FavoritoProductos.class));
    }

    @Test
    void testAddToFavorito() throws Exception {
        Long userId = 1L;
        FavoritoRequest request = new FavoritoRequest();
        request.setProductoId(2L); // Producto con ID diferente al que ya está en favoritos

        Producto newProducto = new Producto();
        newProducto.setId(2L);

        when(favoritoRepository.findByUserId(userId)).thenReturn(Optional.of(favorito));
        when(productoRepository.findById(2L)).thenReturn(Optional.of(newProducto));

        ResponseEntity<FavoritoResponse> response = favoritoService.addToFavorito(userId, request);

        assertEquals("Producto agregado exitosamente.", response.getBody().getMessage());
        verify(favoritoProdRepository, times(1)).save(any(FavoritoProductos.class));
        verify(favoritoRepository, times(1)).save(any(Favorito.class));
    }

    @Test
    void testAddToFavoritoProductAlreadyInFavorites() throws Exception {
        Long userId = 1L;
        FavoritoRequest request = new FavoritoRequest();
        request.setProductoId(1L); // Producto con ID ya en favoritos

        when(favoritoRepository.findByUserId(userId)).thenReturn(Optional.of(favorito));
        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));

        ResponseEntity<FavoritoResponse> response = favoritoService.addToFavorito(userId, request);

        assertEquals("El producto ya está en favoritos", response.getBody().getMessage());
        verify(favoritoProdRepository, times(0)).save(any(FavoritoProductos.class));
        verify(favoritoRepository, times(0)).save(any(Favorito.class));
    }

    @Test
    void testRemoveFromFavoritoProductNotFound() {
        Long userId = 1L;
        FavoritoRequest request = new FavoritoRequest();
        request.setProductoId(2L); // Producto con ID diferente al que ya está en favoritos

        when(favoritoRepository.findByUserId(userId)).thenReturn(Optional.of(favorito));

        assertThrows(ProductInFavsException.class, () -> {
            favoritoService.removeFromFavorito(userId, request);
        });
        verify(favoritoRepository, times(0)).save(any(Favorito.class));
        verify(favoritoProdRepository, times(0)).delete(any(FavoritoProductos.class));
    }
}