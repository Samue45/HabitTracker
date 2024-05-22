package com.HabitTracker.HabitTracker.HabitTracker;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class HabitController {
    
    @Autowired
    private final HabitService habitService;
    

    @GetMapping("list-habits")
    public List<Habit> getHabitsByUserId() 
    {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = Long.valueOf(userDetails.getUsername()); // Asegúrate de que el username sea el ID del usuario
        return habitService.getHabitsByUserId(userId);
    }

    @PostMapping("new-habit")
    public ResponseEntity<Long> saveHabit( @RequestBody Habit habit) 
    {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = Long.valueOf(userDetails.getUsername()); // Asegúrate de que el username sea el ID del usuario
        habit.setUserId(userId);
        Long idHabit = habitService.saveHabit(habit);

        return new ResponseEntity<>(idHabit,HttpStatus.CREATED);
    }

    @PutMapping("habit/{id}")
    public Habit updateHabit(@PathVariable Long id, @RequestBody Habit newHabit) 
    {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = Long.valueOf(userDetails.getUsername()); // Asegúrate de que el username sea el ID del usuario
        return habitService.updateHabit(id, newHabit, userId);
    }

    @DeleteMapping("habit/{id}")
    public ResponseEntity<?>  deleteHabit(@PathVariable Long id)
    {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = Long.valueOf(userDetails.getUsername()); // Asegúrate de que el username sea el ID del usuario
        habitService.deleteHabit(id, userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
}
