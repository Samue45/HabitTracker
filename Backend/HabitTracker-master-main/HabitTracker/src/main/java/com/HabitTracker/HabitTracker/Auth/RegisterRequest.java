package com.HabitTracker.HabitTracker.Auth;

import com.HabitTracker.HabitTracker.User.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Clase que representa una solicitud de registro
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    // Nombre de usuario del solicitante
    private String username;

    // Nombre del solicitante
    private String firstname;

    // Apellido del solicitante
    private String lastname;

    // Contraseña del solicitante
    private String password;

    // Rol del solicitante
    private Role role;
}
