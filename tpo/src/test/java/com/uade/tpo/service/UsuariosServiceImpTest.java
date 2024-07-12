package com.uade.tpo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.uade.tpo.entity.Usuario;
import com.uade.tpo.repository.UsuarioRepository;

@ExtendWith(MockitoExtension.class)
public class UsuariosServiceImpTest {
    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuariosServiceImp usuariosService;

    private Usuario usuario;

    @BeforeEach
    void setUp() {
        usuario = new Usuario();
        usuario.setEmail("test@test.com");
    }

    @Test
    void testObtenerUsuarios() {
        PageRequest pageable = PageRequest.of(0, 10);
        Page<Usuario> page = new PageImpl<>(List.of(usuario));

        when(usuarioRepository.findAll(any(PageRequest.class))).thenReturn(page);

        Page<Usuario> result = usuariosService.obtenerUsuarios(pageable);

        assertEquals(1, result.getTotalElements());
        assertEquals("test@test.com", result.getContent().get(0).getEmail());
    }

    @Test
    void testObtenerUsuarioByEmail() {
        when(usuarioRepository.findByEmail("test@example.com")).thenReturn(Optional.of(usuario));

        Optional<Usuario> result = usuariosService.obtenerUsuarioByEmail("test@example.com");

        assertTrue(result.isPresent());
        assertEquals("test@example.com", result.get().getEmail());
    }
}
