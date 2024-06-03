import { Component} from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms'; 
import { Habit } from '../../interfaces/habit';





@Component({
  selector: 'app-habit-stats',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './habit-stats.component.html',
  styleUrls: ['./habit-stats.component.css']
})
export class HabitStatsComponent  {
  listHabits: Habit[] = [
    // Datos de ejemplo
    {
      name: 'Correr',
      description: 'Salir a correr por la mañana',
      type: 'Salud',
      level_priority: 'Alta',
      state: false,
      nameDay:""
    },
    {
      name: 'Leer',
      description: 'Leer un libro por 30 minutos',
      type: 'Bienestar',
      level_priority: 'Media',
      state: false,
      nameDay:""
    },

    {
      name: 'Caminar',
      description: '10.000 pasos al día',
      type: 'Salud',
      level_priority: 'Media',
      state: false,
      nameDay:""
    }
  ];
/*
  selectedDay: string | null = null;
  daysOfWeek = ['Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'Sábado', 'Domingo'];*/

  constructor() { }

  ngOnInit(): void { }
/*
  getHabitsForDay(day: string): Habit[] {
    return this.listHabits.filter(habit => habit.daysOfWeek.includes(day));
  }

  hasHabits(day: string): boolean {
    return this.listHabits.some(habit => habit.daysOfWeek.includes(day));
  }

  onDayChange(event: Event): void {
    this.selectedDay = (event.target as HTMLSelectElement).value;
  }
  */
}
