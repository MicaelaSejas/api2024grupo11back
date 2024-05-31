package com.uade.tpo.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uade.tpo.controller.auth.AuthenticationRequest;
import com.uade.tpo.controller.auth.AuthenticationResponse;
import com.uade.tpo.controller.auth.RegisterRequest;
import com.uade.tpo.controller.config.JwtService;
import com.uade.tpo.entity.Carrito;
import com.uade.tpo.entity.Usuario;
import com.uade.tpo.repository.RolesRepository;
import com.uade.tpo.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImplement implements AuthenticationService {
        private final UsuarioRepository repository;
        private final PasswordEncoder passwordEncoder;
        private final JwtService jwtService;
        private final AuthenticationManager authenticationManager;
        private final RolesRepository roles;

        @Transactional(rollbackFor = Throwable.class)
        public AuthenticationResponse register(RegisterRequest request) {
                var Rol = roles.findById(request.getRoles());
                var user = Usuario.builder()
                                .username(request.getUsername())
                                .nombre(request.getFirstname())
                                .apellido(request.getLastname())
                                .email(request.getEmail())
                                .password(passwordEncoder.encode(request.getPassword()))
                                .roles(Rol.orElseThrow())
                                .carrito(new Carrito())
                                .build();

                repository.save(user);
                var jwtToken = jwtService.generateToken(user);
                return AuthenticationResponse.builder()
                                .accessToken(jwtToken)
                                .build();
        }

        public AuthenticationResponse authenticate(AuthenticationRequest request) {
                authenticationManager.authenticate(
                                new UsernamePasswordAuthenticationToken(
                                                request.getEmail(),
                                                request.getPassword()));

                var user = repository.findByEmail(request.getEmail())
                                .orElseThrow();
                var jwtToken = jwtService.generateToken(user);
                return AuthenticationResponse.builder()
                                .accessToken(jwtToken)
                                .build();
        }
}
