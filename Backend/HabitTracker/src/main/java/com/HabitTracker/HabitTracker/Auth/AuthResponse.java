package com.HabitTracker.HabitTracker.Auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Clase que representa la respuesta de autenticación
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {

    // Token de autenticación
    private String token;
}

