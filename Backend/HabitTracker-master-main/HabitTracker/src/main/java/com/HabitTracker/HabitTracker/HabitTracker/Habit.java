package com.HabitTracker.HabitTracker.HabitTracker;

import java.util.Date;


import com.HabitTracker.HabitTracker.User.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "habit")
public class Habit {

    @Id
    @GeneratedValue
    Long id;
    String name;
    String description;
    boolean state;
    String level_priority;
    String type;
    Date date;
   
    //Relación entre tabla 'habit' y tabla 'user_data'
    @ManyToOne
    @JsonIgnore
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "id"
    )
    private User user;
}
