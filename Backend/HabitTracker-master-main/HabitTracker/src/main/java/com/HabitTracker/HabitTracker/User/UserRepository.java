package com.HabitTracker.HabitTracker.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para la entidad User.
 * Proporciona métodos para realizar operaciones CRUD y consultas específicas.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Encuentra un usuario por su nombre de usuario.
     *
     * @param username el nombre de usuario del usuario.
     * @return un Optional que contiene el usuario si se encuentra, o vacío si no.
     */
    Optional<User> findByUsername(String username);
}
