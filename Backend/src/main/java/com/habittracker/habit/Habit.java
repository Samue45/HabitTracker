package com.habittracker.habit;

import com.habittracker.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Clase que representa un hábito
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "habit")
public class Habit {

    // Identificador único del hábito
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nombre del hábito
    private String name;

    // Descripción del hábito
    private String description;

    // Estado del hábito (completado o no)
    private boolean state;

    // Nivel de prioridad del hábito
    private String level_priority;

    // Tipo de hábito
    private String type;

    // Nombre del día asociado al hábito
    private String nameDay;

    // Relación entre la tabla 'habit' y la tabla 'user_data'
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}