package com.HabitTracker.HabitTracker.HabitTracker;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.HabitTracker.HabitTracker.Exceptions.HabitNotFoundException;
import com.HabitTracker.HabitTracker.Exceptions.UserNotFoundException;
import com.HabitTracker.HabitTracker.User.User;
import com.HabitTracker.HabitTracker.User.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HabitService {

    private static final Logger LOGGER = LoggerFactory.getLogger(HabitService.class);

    private final HabitRepository habitRepository;
    private final UserRepository userRepository;

    // USUARIO

    /**
     * Obtenemos la autenticación del usuario para luego obtener los hábitos en base a su ID
     * @return Autenticación del usuario
     */
    private User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }

    // Métodos GETTER

    /**
     * Obtenemos una lista de todos los hábitos asociados al usuario autenticado
     *
     * @return Lista de hábitos del usuario.
     */
    public List<Habit> getHabitsByUserId() {
        Long userId = getAuthenticatedUser().getId();
        return habitRepository.findHabitByUserId(userId);
    }

    
    /**
     * Obtenemos una lista de todos los hábitos asociados al usuario autenticado en base al día del hábito
     *
     * @param nameDay Nombre del día asociado al hábito
     * @return Lista de hábitos del usuario en base al día de la semana.
     */
    public List<Habit> getHabitByNameDay(String nameDay) {
        Long userId = getAuthenticatedUser().getId();
        return habitRepository.findHabitByUserIdAndNameDay(userId, nameDay);
    }
    
    /**
     * Obtenemos uno de los hábitos asociados al usuario autenticado en base al ID del hábito
     *
     * @param habitId El ID del hábito.
     * @return El hábito correspondiente al ID y usuario especificados.
     */
    public Habit getHabitByHabitId(Long habitId) {
        Long userId = getAuthenticatedUser().getId();
        Habit habit = habitRepository.findByIdAndUserId(habitId, userId);
        if (habit == null) {
            throw new HabitNotFoundException("Hábito no encontrado para el usuario");
        }
        return habit;
    }

    // Método POST

    /**
     * Creamos un nuevo hábito
     *
     * @param habit Hábito que se quiere crear
     * @return Guardamos el hábito en la Base de Datos
     */
    public Long saveHabit(Habit habit) {
        Long userId = getAuthenticatedUser().getId();
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new UserNotFoundException("Usuario no encontrado"));

        LOGGER.info("Creando un nuevo hábito: {}", habit);

        if (!user.getId().equals(userId)) {
            throw new HabitNotFoundException("No tienes autorización para actualizar este hábito");
        }

        validateHabit(habit);

        Habit newHabit = Habit.builder()
                .name(habit.getName())
                .description(habit.getDescription())
                .level_priority(habit.getLevel_priority())
                .type(habit.getType())
                .nameDay(habit.getNameDay())
                .user(user)
                .build();

        Long id = habitRepository.save(newHabit).getId();
        LOGGER.info("El hábito ha sido creado exitosamente con id: {}", id);
        return id;
    }


    //Método UPDATE

    /**
     * Actualizamos un hábito ya existente
     *
     * @param habitId El ID del hábito.
     * @param newHabit El hábito con las modificaciones que se quieren realizar
     * @return Guardamos el hábito con las modificaciones en la Base de Datos
     */
    public Habit updateHabit(Long habitId, Habit newHabit) {
        Long userId = getAuthenticatedUser().getId();
        
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new UserNotFoundException("Usuario no encontrado"));

        Habit oldHabit = habitRepository.findById(habitId)
            .orElseThrow(() -> new HabitNotFoundException("Hábito no encontrado"));

        if (!user.getId().equals(userId)) {
            throw new HabitNotFoundException("No tienes autorización para actualizar este hábito");
        }

        LOGGER.info("Actualizando hábito con id: {} para el usuario con id: {}", habitId, userId);

        validateHabit(newHabit);

        oldHabit.setName(newHabit.getName());
        oldHabit.setDescription(newHabit.getDescription());
        oldHabit.setType(newHabit.getType());
        oldHabit.setLevel_priority(newHabit.getLevel_priority());
        oldHabit.setNameDay(newHabit.getNameDay());

        Habit updatedHabit = habitRepository.save(oldHabit);
        LOGGER.info("El hábito con id {} ha sido actualizado exitosamente", updatedHabit.getId());

        return updatedHabit;
    }

    private void validateHabit(Habit habit) {
        try {
            HabitValidator.validate(habit);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Algunos de los datos del hábito están incompletos, debe rellenarlos todos.");
        }
    }

    //Método DELETE

    /**
     * Eliminamos un hábito en base a su ID
     *
     * @param habitId El ID del hábito.
     * @return Eliminamos el hábito de la Base de Datos
     */
    public void deleteHabit(Long habitId) {
        Long userId = getAuthenticatedUser().getId();
        Habit habit = habitRepository.findByIdAndUserId(habitId, userId);
        if (habit == null) {
            throw new HabitNotFoundException("Hábito no encontrado o no tienes autorización para eliminarlo");
        }

        habitRepository.delete(habit);
        LOGGER.info("El hábito con id {} ha sido eliminado exitosamente", habitId);
    }
}

