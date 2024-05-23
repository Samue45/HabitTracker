package com.HabitTracker.HabitTracker.HabitTracker;

import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.HabitTracker.HabitTracker.User.User;
import com.HabitTracker.HabitTracker.User.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HabitService {
    
    @Autowired
    private static final Logger LOGGER = LoggerFactory.getLogger(HabitService.class);
    private final HabitRepository habitRepository;
    private final UserRepository userRepository;
   
    
    
    public List<Habit> getHabitsByUserId() {

        // Obtenemos el ID del usuario autenticado
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User userDetails = (User) authentication.getPrincipal();
        Long userId = userDetails.getId();

        return habitRepository.findHabitByUserId(userId);
    }

   
    public Long saveHabit(Habit habit) {
        
        // Verificamos la identidad del usuario autenticado
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User userDetails = (User) authentication.getPrincipal();
        Long userId = userDetails.getId();


        // Buscar el usuario en la base de datos
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        LOGGER.info("Creando un nuevo hábito: {}", habit);

         //Comprobamos que haya rellenado todos los campos obligatorios
         if (habit.getName() == null) {
            LOGGER.warn("Debe ingresar el nombre del hábito");
            throw new IllegalArgumentException("El hábito necesita un nombre");
        }
        if (habit.getDescription() == null) {
            LOGGER.warn("Debe ingresar una descripción sobre el hábito");
            throw new IllegalArgumentException("El hábito necesita una descripción");
        }
        if (habit.getType() == null) {
            LOGGER.warn("Debe ingresar el tipo de hábito");
            throw new IllegalArgumentException("El hábito necesita un tipo");
        }

        if (habit.getDate() == null) {
            LOGGER.warn("Debe ingresar una fecha para el hábito");
            throw new IllegalArgumentException("El hábito necesita una fecha");
        }

        Habit newHabit = Habit.builder()
                .name(habit.getName())
                .description(habit.getDescription())
                .level_priority(habit.getLevel_priority())
                .type(habit.getType())
                .date(habit.getDate())
                .build();
        
        //Establecemos el id que relaciona al Usuario con su hábito
        newHabit.setUser(user);

        Long id = habitRepository.save(newHabit).getId();
        LOGGER.info("El hábito ha sido creado exitosamente");
        return id;
    }

    public Habit updateHabit(Long habitId, Habit newHabit) {
        
        // Verificamos la identidad del usuario autenticado
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User userDetails = (User) authentication.getPrincipal();
        Long userId = userDetails.getId();
        
        // Buscar el usuario en la base de datos
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        // Buscar el hábito en la base de datos
        Habit oldHabit = habitRepository.findById(habitId).orElseThrow(() -> new RuntimeException("Habit not found"));

        LOGGER.info("Found habit and user: {}", oldHabit , user);

        //Comprobamos que haya rellenado todos los campos obligatorios
        if (newHabit.getName() == null) {
            LOGGER.warn("Debe ingresar el nombre del hábito");
            throw new IllegalArgumentException("El hábito necesita un nombre");
        }
        if (newHabit.getDescription() == null) {
            LOGGER.warn("Debe ingresar una descripción sobre el hábito");
            throw new IllegalArgumentException("El hábito necesita una descripción");
        }
        if (newHabit.getType() == null) {
            LOGGER.warn("Debe ingresar el tipo de hábito");
            throw new IllegalArgumentException("El hábito necesita un tipo");
        }
        if (newHabit.getDate() == null) {
            LOGGER.warn("Debe ingresar una fecha para el hábito");
            throw new IllegalArgumentException("El hábito necesita una fecha");
        }

         //Actualizamos los datos del viejo hábito
         LOGGER.info("Acualizando hábito: {}", oldHabit);
         oldHabit.setName(newHabit.getName());
         oldHabit.setDescription(newHabit.getDescription());
         oldHabit.setDate(newHabit.getDate());
         oldHabit.setState(newHabit.isState());
         oldHabit.setLevel_priority(newHabit.getLevel_priority());
         oldHabit.setType(newHabit.getType());

         LOGGER.info("El hábito ha sido actualizado exitosamente");
         return habitRepository.save(oldHabit);

    }

    public void deleteHabit(Long habitId) {

        // Verificamos la identidad del usuario autenticado
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User userDetails = (User) authentication.getPrincipal();
        Long userId = userDetails.getId();
      
        Habit habit = habitRepository.findByIdAndUserId(habitId, userId);
        if (habit != null) {
            habitRepository.delete(habit);
        } else {
            throw new RuntimeException("Habit not found or you are not authorized to delete this habit");
        }

        habitRepository.deleteById(habitId);

    }
    
}
