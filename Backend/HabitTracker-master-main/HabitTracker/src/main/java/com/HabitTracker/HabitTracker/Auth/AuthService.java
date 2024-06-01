package com.HabitTracker.HabitTracker.Auth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public AuthService(UserRepository userRepository, JwtService jwtService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public AuthResponse login(LoginRequest request){

         //Comprobaciones antes de logear un  usuario
         LOGGER.info("Comprobando si el formato del username es válido");
         if (!checkValidezEmail(request.getUsername())) {
            LOGGER.warn("El formato del usuario no es válido");
             throw new IllegalArgumentException("El formato del usuario no es válido");
         }
 
         LOGGER.info("Comprobando si el nivel de seguridad de la password es buena");
         if (!checkValidezPassword(request.getPassword())) {
            LOGGER.warn("La contrseña no es segura");
             throw new IllegalArgumentException("La contraseña no es segura");
         }

         authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
         UserDetails user = userRepository.findByUsername(request.getUsername()).orElseThrow();
         String token = jwtService.getToken(user);

         LOGGER.info("Usuario logeado exitosamente");
         return AuthResponse.builder()
            .token(token)
            .build();
    
    }

    public AuthResponse register(RegisterRequest request) {

        //Comprobaciones antes de registrar un nuevo usuario
        LOGGER.info("Comprobando si el formato del username es válido");
        if (!checkValidezEmail(request.getUsername())) {
            throw new IllegalArgumentException("El formato del usuario no es válido");
        }

        LOGGER.info("Comprobando si el nivel de seguridad de la password es buena");
        if (!checkValidezPassword(request.getPassword())) {
            throw new IllegalArgumentException("La contraseña no es segura");
        }
        
        
        User user = User.builder()
            .username(request.getUsername())
            .password(passwordEncoder.encode(request.getPassword()))
            .firstName(request.getFirstName())
            .lastName(request.getLastName())
            .role(Role.USER)
            .build();

        userRepository.save(user);
        LOGGER.info("Usuario registrado exitosamente");

        return AuthResponse.builder()
            .token(jwtService.getToken(user))
            .build();
            
    }

    //MÉTODOS DE COMPROBACIÓN

     //Formato del username
    private boolean checkValidezEmail(String email) {
        return Pattern.compile(
                "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
                Pattern.CASE_INSENSITIVE
        ).asPredicate().test(email);
    }

    //Nivel de seguridad del password
    private static boolean checkValidezPassword(String password) {
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
        if (!matcher.find()) {
            return false;
        }

        return true;
    }
}
