package com.uade.tpo.controller.config;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private JwtAuthenticationFilter jwtAuthFilter;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req -> req.requestMatchers("/api/v1/auth/**").permitAll()
                        .requestMatchers("/error/**").permitAll()
                        // .requestMatchers("/api/v1/categoria").hasAnyAuthority("Vendedor")
                        .requestMatchers("/api/v1/categoria/**").permitAll()
                        // .requestMatchers("/api/v1/descuento").hasAnyAuthority("Vendedor")
                        .requestMatchers("/api/v1/descuento/**").permitAll()
                        // TODO: chequear que un comprador no pueda crear productos
                        .requestMatchers("/api/v1/producto/**").permitAll()
                        .requestMatchers("/api/v1/producto/**").permitAll()
                        // .requestMatchers("/carrito").authenticated()
                        .requestMatchers("/api/v1/carrito/**").permitAll()
                        // TODO: chequear que no pueda borrar compras
                        .requestMatchers("/api/v1/favoritos/**").permitAll()
                        .anyRequest()
                        .authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
