package com.HabitTracker.HabitTracker.HabitTracker;

import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HabitService {
    
    @Autowired
    private static final Logger LOGGER = LoggerFactory.getLogger(HabitService.class);
    private final HabitRepository habitRepository;
    
    
    public List<Habit> getHabitsByUserId(Long userId) {
        return habitRepository.findUserById(userId);
    }

   
    public Long saveHabit(Habit habit) {
        
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

        Long id = habitRepository.save(newHabit).getId();
        LOGGER.info("El hábito ha sido creado exitosamente");
        return id;
    }

    public Habit updateHabit(Long id, Habit newHabit, Long userId) {
        Habit oldHabit = habitRepository.findById(id).orElseThrow(() -> new RuntimeException("Habit not found"));
        if (!oldHabit.getUserId().equals(userId)) {
            throw new RuntimeException("Unauthorized");
        }

        LOGGER.info("Encontrado hábito: {}", oldHabit);

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

    public void deleteHabit(Long id, Long userId) {
        Habit habit = habitRepository.findById(id).orElseThrow(() -> new RuntimeException("Habit not found"));
        if (!habit.getUserId().equals(userId)) {
            throw new RuntimeException("Unauthorized");
        }
        habitRepository.deleteById(id);
    }


   
    
}
