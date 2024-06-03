package com.HabitTracker.HabitTracker.HabitTracker;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class HabitController {

    private final HabitService habitService;

    /**
     * Obtiene una lista de hábitos por el ID del usuario.
     *
     * @return Lista de hábitos.
     */
    @GetMapping("list-habits")
    public List<Habit> getHabitsByUserId() {
        return habitService.getHabitsByUserId();
    }

    /**
     * Obtiene una lista de hábitos por el nombre del día.
     *
     * @param nameDay Nombre del día.
     * @return Lista de hábitos.
     */
    @GetMapping("habit/day/{nameDay}")
    public List<Habit> getHabitByNameDay(@PathVariable String nameDay) {
        return habitService.getHabitByNameDay(nameDay);
    }

    /**
     * Obtiene un hábito por su ID.
     *
     * @param habitId ID del hábito.
     * @return El hábito correspondiente.
     */
    @GetMapping("habit/{habitId}")
    public Habit getHabitByHabitId(@PathVariable Long habitId) {
        return habitService.getHabitByHabitId(habitId);
    }

    /**
     * Guarda un nuevo hábito.
     *
     * @param habit El nuevo hábito a guardar.
     * @return ResponseEntity con el ID del nuevo hábito.
     */
    @PostMapping("new-habit")
    public ResponseEntity<Long> saveHabit(@RequestBody Habit habit) {
        Long idHabit = habitService.saveHabit(habit);
        return new ResponseEntity<>(idHabit, HttpStatus.CREATED);
    }

    /**
     * Actualiza un hábito existente.
     *
     * @param habitId ID del hábito a actualizar.
     * @param newHabit Datos del nuevo hábito.
     * @return ResponseEntity con el hábito actualizado.
     */
    @PutMapping("habit/{habitId}")
    public ResponseEntity<Habit> updateHabit(@PathVariable Long habitId, @RequestBody Habit newHabit) {
        Habit updatedHabit = habitService.updateHabit(habitId, newHabit);
        return ResponseEntity.ok(updatedHabit);
    }

    /**
     * Elimina un hábito por su ID.
     *
     * @param habitId ID del hábito a eliminar.
     * @return ResponseEntity sin contenido.
     */
    @DeleteMapping("habit/{habitId}")
    public ResponseEntity<Void> deleteHabit(@PathVariable Long habitId) {
        habitService.deleteHabit(habitId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
