package com.uade.tpo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.uade.tpo.entity.Categoria;
import com.uade.tpo.repository.CategoriaRepository;

@ExtendWith(MockitoExtension.class)
public class CategoriaServiceTest {
    @Mock
    private CategoriaRepository categoriaRepository;

    @InjectMocks
    private CategoriaService categoriaService;

    private Categoria categoria;

    @BeforeEach
    void setUp() {
        categoria = new Categoria();
        categoria.setDescripcion("Una silla");
        categoria.setId(1);
    }

    @Test
    void testFindAllCategorias_Success() {
        when(categoriaRepository.findAll()).thenReturn(List.of(categoria));

        List<Categoria> result = categoriaService.findAllCategorias();

        assertEquals("Una silla", result.get(0).getDescripcion());
    }

    @Test
    void testFindById_Success() {
        when(categoriaRepository.findById(1)).thenReturn(Optional.of(categoria));

        Optional<Categoria> result = categoriaService.findCategoriaById(1);

        assertTrue(result.isPresent());
        assertEquals("Una silla", result.get().getDescripcion());
    }

    @Test
    void testFindByIdl_NotFound() {
        when(categoriaRepository.findById(10)).thenReturn(Optional.empty());

        Optional<Categoria> result = categoriaService.findCategoriaById(10);

        assertFalse(result.isPresent());
    }

    @Test
    public void testSaveCategoria_Success() {
        when(categoriaRepository.save(any(Categoria.class))).thenReturn(categoria);

        Categoria result = categoriaService.saveCategoria(categoria);

        assertNotNull(result);
        assertEquals("Una silla", result.getDescripcion());
        verify(categoriaRepository, times(1)).save(categoria);
    }

    @Test
    public void testDeleteCategoria_Success() {
        doNothing().when(categoriaRepository).deleteById(1);

        categoriaService.deleteCategoria(1);

        verify(categoriaRepository, times(1)).deleteById(1);
    }

    @Test
    public void testUpdateCategoria_Success() {
        Categoria categoriaActualizada = new Categoria();
        categoriaActualizada.setDescripcion("Nueva descripcion");

        when(categoriaRepository.findById(1)).thenReturn(Optional.of(categoria));
        when(categoriaRepository.save(any(Categoria.class))).thenReturn(categoriaActualizada);

        Categoria result = categoriaService.updateCategoria(1, categoriaActualizada);

        assertNotNull(result);
        assertEquals("Nueva descripcion", result.getDescripcion());
        verify(categoriaRepository, times(1)).findById(1);
        verify(categoriaRepository, times(1)).save(categoria);
    }

    @Test
    public void testUpdateCategoria_NotFound() {
        Categoria categoriaActualizada = new Categoria();
        categoriaActualizada.setDescripcion("Nueva descripcion");

        when(categoriaRepository.findById(10)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            categoriaService.updateCategoria(10, categoriaActualizada);
        });

        assertEquals("No se encontró la categoría con ID: 10", exception.getMessage());
        verify(categoriaRepository, times(1)).findById(10);
        verify(categoriaRepository, times(0)).save(any(Categoria.class));
    }
}
