package com.uade.tpo.controller.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req -> req
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/error/**").permitAll()
                        // .requestMatchers("/api/categoria").hasAuthority("Vendedor")
                        .requestMatchers("/api/carrito/**").permitAll()
                        .requestMatchers("/api/carritoProducto/**").permitAll()
                        .requestMatchers("/api/categoria/**").permitAll()
                        .requestMatchers("/api/compra/**").permitAll()
                        .requestMatchers("/api/compraProducto/**").permitAll()
                        .requestMatchers("/api/descuento/**").permitAll()
                        .requestMatchers("/api/producto/**").permitAll()
                        .requestMatchers("/api/rol/**").permitAll()
                        .requestMatchers("/api/usuario/**").permitAll()

                        .anyRequest().authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
