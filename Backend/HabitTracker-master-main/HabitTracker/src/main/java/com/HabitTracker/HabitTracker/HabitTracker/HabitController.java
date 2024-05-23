package com.HabitTracker.HabitTracker.HabitTracker;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.HabitTracker.HabitTracker.User.User;

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
        return habitService.getHabitsByUserId();
    }

    @PostMapping("new-habit")
    public ResponseEntity<Long> saveHabit( @RequestBody Habit habit) 
    {
        Long idHabit = habitService.saveHabit(habit);
        return new ResponseEntity<>(idHabit,HttpStatus.CREATED);
    }

    @PutMapping("habit/{id}")
    public Habit updateHabit(@PathVariable Long id, @RequestBody Habit newHabit) 
    {
        return habitService.updateHabit(id, newHabit);
    }

    @DeleteMapping("habit/{id}")
    public ResponseEntity<?>  deleteHabit(@PathVariable Long id)
    {
        habitService.deleteHabit(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
}
