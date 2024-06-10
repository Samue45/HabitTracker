package com.habittracker.habit;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
     * Encuentra hábitos por el ID del usuario.
     *
     * @param userId El ID del usuario.
     * @return ResponseEntity sin contenido.
     */
    @Modifying
    @Query("DELETE FROM Habit h WHERE h.user.id = :userId")
    void deleteAllHabitByUserId(@Param("userId") Long userId);


    /**
     * Encuentra un hábito por su ID y el ID del usuario.
     *
     * @param habitId El ID del hábito.
     * @param userId El ID del usuario.
     * @return El hábito correspondiente al ID y usuario especificados.
     */
    Habit findByIdAndUserId(Long habitId, Long userId);
}