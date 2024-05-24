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

import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Service
@Builder
@RequiredArgsConstructor
public class HabitService {

    private static final Logger LOGGER = LoggerFactory.getLogger(HabitService.class);

    @Autowired
    private final HabitRepository habitRepository;
    private final UserRepository userRepository;
   
    
    //GETTER
    public List<Habit> getHabitsByUserId() {

        // Obtenemos el ID del usuario autenticado
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User userDetails = (User) authentication.getPrincipal();
        Long userId = userDetails.getId();

        //Buscamos los hábitos asociados al ID del usuario
        return habitRepository.findHabitByUserId(userId);
    }

    //POST
    public Long saveHabit(Habit habit) {
        
        // Verificamos la identidad del usuario autenticado
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User userDetails = (User) authentication.getPrincipal();
        Long userId = userDetails.getId();


        // Buscamos al usuario en la base de datos
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        LOGGER.info("Creando un nuevo hábito: {}", habit);

        //Comprobamos que haya rellenado todos los campos obligatorios del hábito
        try {
            HabitValidator.validate(habit);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Alguno de los datos del hábito está incompleto, debe rellenarlos todos");
        }

        //Creamos el nuevo hábito
        Habit newHabit = Habit.builder()
                .name(habit.getName())
                .description(habit.getDescription())
                .level_priority(habit.getLevel_priority())
                .type(habit.getType())
                .date(habit.getDate())
                .build();
        
        //Establecemos el id que relaciona al usuario con su hábito
        newHabit.setUser(user);

        //Finalmente guardamos el hábito en la DB
        Long id = habitRepository.save(newHabit).getId();
        LOGGER.info("El hábito ha sido creado exitosamente");
        return id;
    }

    //UPDATE
    public Habit updateHabit(Long habitId, Habit newHabit) {
        
        // Verificamos la identidad del usuario autenticado
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User userDetails = (User) authentication.getPrincipal();
        Long userId = userDetails.getId();
        
        // Buscamos al usuario en la base de datos
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        // Buscamos el hábito en la base de datos
        Habit oldHabit = habitRepository.findById(habitId).orElseThrow(() -> new RuntimeException("Habit not found"));

        LOGGER.info("Busqueda con éxito, habit:{} user{}", oldHabit , user);

        //Comprobamos que haya rellenado todos los campos obligatorios del hábito
        try {
            HabitValidator.validate(newHabit);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Alguno de los datos del hábito está incompleto, debe rellenarlos todos");
        }

         //Actualizamos los datos del viejo hábito
         LOGGER.info("Acualizando hábito: {}", oldHabit);

         oldHabit = Habit.builder()
                .name(newHabit.getName())
                .description(newHabit.getDescription())
                .type(newHabit.getType())
                .level_priority(newHabit.getLevel_priority())
                .date(newHabit.getDate())
                .state(newHabit.isState())
                .build();

        //Finalmente guardamos el hábito en la DB
         LOGGER.info("El hábito ha sido actualizado exitosamente");
         return habitRepository.save(oldHabit);

    }

    //DELETE
    public void deleteHabit(Long habitId) {

        // Verificamos la identidad del usuario autenticado
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User userDetails = (User) authentication.getPrincipal();
        Long userId = userDetails.getId();
      
        //Comprobamos si existe el hábito y si pertenece al usuario
        Habit habit = habitRepository.findByIdAndUserId(habitId, userId);
        if (habit == null) {
            throw new RuntimeException("Hábito no encontrado o no tienes autorización para eliminarlo");
        }

        //Finalmente eliminamos el hábito de la DB
        habitRepository.delete(habit);
    }
    
}
