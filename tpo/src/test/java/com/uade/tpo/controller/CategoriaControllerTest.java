package com.uade.tpo.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import org.springframework.http.MediaType;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.uade.tpo.entity.Categoria;
import com.uade.tpo.service.CategoriaService;

@ExtendWith(MockitoExtension.class)
public class CategoriaControllerTest {
    @Mock
    private CategoriaService categoriaService;

    @InjectMocks
    private CategoriaController categoriaController;

    private Categoria categoria;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(categoriaController).build();

        categoria = new Categoria();
        categoria.setDescripcion("Una silla");
        categoria.setId(1);
    }

    @Test
    void testFindAllCategorias() throws Exception {
        when(categoriaService.findAllCategorias()).thenReturn(List.of(categoria));

        mockMvc.perform(get("/api/v1/categoria").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].descripcion").value("Una silla"));
    }

    @Test
    void testGetCategoriaById_Success() throws Exception {
        when(categoriaService.findCategoriaById(1)).thenReturn(Optional.of(categoria));

        mockMvc.perform(get("/api/v1/categoria/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.descripcion").value("Una silla"));
    }

    @Test
    void testGetCategoriaById_NotFound() throws Exception {
        when(categoriaService.findCategoriaById(1)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/v1/categoria/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testCreateCategoria_Success() throws Exception {
        when(categoriaService.saveCategoria(any(Categoria.class))).thenReturn(categoria);

        mockMvc.perform(post("/api/v1/categoria")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"descripcion\": \"Una silla\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.descripcion").value("Una silla"));
    }

    @Test
    void testCreateCategoria_Error() throws Exception {
        when(categoriaService.saveCategoria(any(Categoria.class))).thenThrow(new RuntimeException("Error"));

        mockMvc.perform(post("/api/v1/categoria")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"descripcion\": \"Una silla\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testUpdateCategoria_Success() throws Exception {
        when(categoriaService.updateCategoria(eq(1), any(Categoria.class))).thenReturn(categoria);

        mockMvc.perform(put("/api/v1/categoria/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"descripcion\": \"Una silla mas linda\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.descripcion").value("Una silla"));
    }

    @Test
    void testUpdateCategoria_Error() throws Exception {
        when(categoriaService.updateCategoria(eq(1), any(Categoria.class)))
                .thenThrow(new RuntimeException("No se pudo actualizar"));

        mockMvc.perform(put("/api/v1/categoria/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"descripcion\": \"Una silla mas linda\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testDeleteCategoria_Success() throws Exception {
        doNothing().when(categoriaService).deleteCategoria(1);

        mockMvc.perform(delete("/api/v1/categoria/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteCategoria_Error() throws Exception {
        doThrow(new RuntimeException("No se pudo eliminar")).when(categoriaService).deleteCategoria(1);

        mockMvc.perform(delete("/api/v1/categoria/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
