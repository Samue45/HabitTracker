import { Component, OnInit } from '@angular/core';  // Añade OnInit aquí
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms'; 
import { Habit } from '../../interfaces/habit';
import { HabitService } from '../../services/habit.service';

@Component({
  selector: 'app-habit-stats',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './habit-stats.component.html',
  styleUrls: ['./habit-stats.component.css']
})
export class HabitStatsComponent implements OnInit {  // Implementa OnInit aquí
  listHabits: Habit[] = [];
  errorMessage: string = '';
  selectedDay: string | null = null;
  daysOfWeek = ['Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'Sábado', 'Domingo'];

  constructor(private habitService: HabitService) { }

  ngOnInit(): void {
    // Cargar todos los hábitos inicialmente
    this.loadHabits();
  }

  loadHabits(nameDay?: string): void {  // nameDay es opcional aquí
    this.habitService.getHabitsByNameDay(nameDay || '').subscribe(
      (data: Habit[]) => {
        this.listHabits = nameDay ? data.filter(habit => habit.nameDay === nameDay) : data;
      },
      (error) => {
        this.errorMessage = error.message || 'An error occurred while fetching habits';
      }
    );
  }

  getFilteredHabits(): Habit[] {
    return this.selectedDay ? this.listHabits.filter(habit => habit.nameDay === this.selectedDay) : [];
  }

  getHabitsForDay(day: string): Habit[] {
    return this.listHabits.filter(habit => habit.nameDay.includes(day));
  }

  hasHabits(day: string): boolean {
    return this.listHabits.some(habit => habit.nameDay.includes(day));
  }

  onDayChange(event: Event): void {
    this.selectedDay = (event.target as HTMLSelectElement).value;
    this.loadHabits(this.selectedDay);  // Cargar los hábitos para el día seleccionado
  }
}

