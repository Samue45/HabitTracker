package com.HabitTracker.HabitTracker.HabitTracker;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HabitRepository extends JpaRepository<Habit, Long> {

    /**
     * Encuentra hábitos por el ID del usuario.
     *
     * @param userId El ID del usuario.
     * @return Lista de hábitos del usuario.
     */
    List<Habit> findHabitByUserId(Long userId);

    /**
     * Encuentra hábitos por el ID del usuario y el nombre del día.
     *
     * @param userId El ID del usuario.
     * @param nameDay El nombre del día.
     * @return Lista de hábitos del usuario para el día especificado.
     */
    List<Habit> findHabitByUserIdAndNameDay(Long userId, String nameDay);

    /**
     * Encuentra un hábito por su ID y el ID del usuario.
     *
     * @param habitId El ID del hábito.
     * @param userId El ID del usuario.
     * @return El hábito correspondiente al ID y usuario especificados.
     */
    Habit findByIdAndUserId(Long habitId, Long userId);
}