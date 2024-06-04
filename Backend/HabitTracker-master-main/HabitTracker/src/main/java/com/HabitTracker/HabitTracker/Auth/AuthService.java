package com.HabitTracker.HabitTracker.Auth;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.HabitTracker.HabitTracker.JWT.JwtService;
import com.HabitTracker.HabitTracker.User.Role;
import com.HabitTracker.HabitTracker.User.User;
import com.HabitTracker.HabitTracker.User.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthService.class);

    /**
     * Inicia sesión un usuario con las credenciales proporcionadas.
     * @param request La solicitud de inicio de sesión.
     * @return AuthResponse La respuesta de autenticación con el token JWT.
     */
    public AuthResponse login(LoginRequest request) {
        // Validar formato del username
        LOGGER.info("Validando el formato del username");
        if (!isValidEmail(request.getUsername())) {
            LOGGER.warn("Formato de username inválido");
            throw new IllegalArgumentException("El formato del username no es válido");
        }

        // Validar nivel de seguridad de la contraseña
        LOGGER.info("Validando nivel de seguridad de la contraseña");
        if (!isValidPassword(request.getPassword())) {
            LOGGER.warn("Contraseña insegura");
            throw new IllegalArgumentException("La contraseña no es segura");
        }

        // Autenticar usuario
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token = jwtService.generateToken(user);

        LOGGER.info("Usuario autenticado exitosamente");
        return AuthResponse.builder()
            .token(token)
            .build();
    }

    /**
     * Registra un nuevo usuario con la información proporcionada.
     * @param request La solicitud de registro.
     * @return AuthResponse La respuesta de autenticación con el token JWT.
     */
    public AuthResponse register(RegisterRequest request) {
        // Validar formato del username
        LOGGER.info("Validando el formato del username");
        if (!isValidEmail(request.getUsername())) {
            throw new IllegalArgumentException("El formato del username no es válido");
        }

        // Validar nivel de seguridad de la contraseña
        LOGGER.info("Validando nivel de seguridad de la contraseña");
        if (!isValidPassword(request.getPassword())) {
            throw new IllegalArgumentException("La contraseña no es segura");
        }

        // Crear y guardar nuevo usuario
        User user = User.builder()
            .username(request.getUsername())
            .password(passwordEncoder.encode(request.getPassword()))
            .firstname(request.getFirstname())
            .lastname(request.getLastname())
            .role(Role.USER)
            .build();

        userRepository.save(user);
        LOGGER.info("Usuario registrado exitosamente");

        return AuthResponse.builder()
            .token(jwtService.generateToken(user))
            .build();
    }

    // MÉTODOS DE VALIDACIÓN

    /**
     * Valida el formato del email.
     * @param email El email a validar.
     * @return true si el email es válido, false en caso contrario.
     */
    private boolean isValidEmail(String email) {
        return Pattern.compile(
                "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
                Pattern.CASE_INSENSITIVE
        ).asPredicate().test(email);
    }

    /**
     * Valida el nivel de seguridad de la contraseña.
     * @param password La contraseña a validar.
     * @return true si la contraseña es segura, false en caso contrario.
     */
    private static boolean isValidPassword(String password) {
        // Verificar la longitud de la contraseña
        if (password.length() < 8) {
            return false;
        }

        // Verificar si la contraseña contiene al menos una letra minúscula, una letra mayúscula y un número
        if (!password.matches(".*[a-z].*") || !password.matches(".*[A-Z].*") || !password.matches(".*\\d.*")) {
            return false;
        }

        // Verificar si la contraseña contiene al menos un carácter especial
        Pattern pattern = Pattern.compile("[!@#$%^&*()-_+=]");
        Matcher matcher = pattern.matcher(password);
        return matcher.find();
    }
}

