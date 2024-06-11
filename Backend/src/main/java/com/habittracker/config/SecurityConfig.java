package com.habittracker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.habittracker.jwt.JwtAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authProvider;

    /**
     * Configura la cadena de filtros de seguridad.
     *
     * @param http Configuración de seguridad HTTP.
     * @return SecurityFilterChain La cadena de filtros de seguridad.
     * @throws Exception Si ocurre un error en la configuración.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .csrf(csrf -> csrf.disable()) // Deshabilita la protección CSRF
            .authorizeHttpRequests(authRequest ->
                authRequest
                    .requestMatchers("/auth/**").permitAll() // Permite el acceso sin autenticación a las rutas /auth/**
                    .anyRequest().authenticated() // Requiere autenticación para cualquier otra ruta
            )
            .sessionManagement(sessionManager ->
                sessionManager
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Configura la política de sesión como STATELESS
            )
            .authenticationProvider(authProvider) // Configura el proveedor de autenticación
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class) // Añade el filtro de autenticación JWT antes del filtro de autenticación por usuario y contraseña
            .build();
    }
}