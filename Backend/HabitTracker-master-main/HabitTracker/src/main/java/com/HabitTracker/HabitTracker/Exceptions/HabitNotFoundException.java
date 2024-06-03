package com.HabitTracker.HabitTracker.Exceptions;

public class HabitNotFoundException extends RuntimeException {
    public HabitNotFoundException(String message) {
        super(message);
    }
}
