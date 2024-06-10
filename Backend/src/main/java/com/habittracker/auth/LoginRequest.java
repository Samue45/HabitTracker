package com.habittracker.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Clase que representa una solicitud de inicio de sesión
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    // Nombre de usuario del solicitante
    private String username;

    // Contraseña del solicitante
    private String password;
}