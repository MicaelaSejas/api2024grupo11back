package com.uade.tpo.service;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.uade.tpo.repository.CarritoRepository;
import com.uade.tpo.repository.RolesRepository;
import com.uade.tpo.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImplement implements AuthenticationService {
	
		@Autowired
        private UsuarioRepository repository;
		
		@Autowired
		private PasswordEncoder passwordEncoder;
		
		@Autowired
		private JwtService jwtService;
		
		@Autowired
		private AuthenticationManager authenticationManager;
		
		@Autowired
		private RolesRepository roles;
		
		@Autowired
		private CarritoRepository carritoRepository;

        @Override
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
//                                .carrito(new Carrito())
                                .build();
                
                Carrito carrito = new Carrito();
                carrito.setUsuario((Usuario) user);
                

                repository.save(user);
                carritoRepository.save(carrito);
                var jwtToken = jwtService.generateToken(user);
                return AuthenticationResponse.builder()
                                .accessToken(jwtToken)
                                .build();
        }

        @Override
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
