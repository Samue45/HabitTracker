import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Habit } from '../../interfaces/habit';
import { HabitService } from '../../services/habit.service';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-habit-stats',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './habit-stats.component.html',
  styleUrls: ['./habit-stats.component.css']
})
export class HabitStatsComponent implements OnInit {
  listHabits: Habit[] = [];
  errorMessage: string = '';
  selectedDay: string | null = null;
  daysOfWeek = ['Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'Sábado', 'Domingo'];

  constructor(private habitService: HabitService) { }

  ngOnInit(): void {
    this.loadHabits();
  }

  loadHabits(): void {
    this.habitService.getHabits().subscribe(
      (habits) => this.listHabits = habits,
      (error) => this.errorMessage = error.message || 'An error occurred while fetching habits'
    );
  }

  getFilteredHabits(): Habit[] {
    return this.selectedDay ? this.listHabits.filter(habit => habit.nameDay === this.selectedDay) : [];
  }

  getHabitsForDay(day: string): Habit[] {
    return this.listHabits.filter(habit => habit.nameDay === day);
  }

  hasHabits(day: string): boolean {
    return this.listHabits.some(habit => habit.nameDay === day);
  }

  onDayChange(event: Event): void {
    this.selectedDay = (event.target as HTMLSelectElement).value;
  }

  resetWeek(): void {
    this.habitService.deleteAllHabits().subscribe(
      () => {
        this.listHabits = []; // Vacía la lista de hábitos en el componente
        console.log('Todos los hábitos han sido eliminados');
      },
      (error) => {
        this.errorMessage = error.message || 'An error occurred while deleting the habit';
      }
    );
  }
}
