import { Component} from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms'; 
import { habit } from '../../interfaces/habit';





@Component({
  selector: 'app-habit-stats',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './habit-stats.component.html',
  styleUrls: ['./habit-stats.component.css']
})
export class HabitStatsComponent  {
  listHabits: habit[] = [
    // Datos de ejemplo
    {
      name: 'Correr',
      description: 'Salir a correr por la mañana',
      category: 'Salud',
      priority: 'Alta',
      daysPerWeek:3,
      daysOfWeek: ['Lunes', 'Miércoles', 'Viernes'],
      completed: false
    },
    {
      name: 'Leer',
      description: 'Leer un libro por 30 minutos',
      category: 'Bienestar',
      priority: 'Media',
      daysPerWeek:2,
      daysOfWeek: ['Martes', 'Jueves'],
      completed: false
    },

    {
      name: 'Caminar',
      description: '10.000 pasos al día',
      category: 'Salud',
      priority: 'Media',
      daysPerWeek:4,
      daysOfWeek: ['Lunes','Martes','Miércoles', 'Jueves'],
      completed: false
    }
  ];

  selectedDay: string | null = null;
  daysOfWeek = ['Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'Sábado', 'Domingo'];

  constructor() { }

  ngOnInit(): void { }

  getHabitsForDay(day: string): habit[] {
    return this.listHabits.filter(habit => habit.daysOfWeek.includes(day));
  }

  hasHabits(day: string): boolean {
    return this.listHabits.some(habit => habit.daysOfWeek.includes(day));
  }

  onDayChange(event: Event): void {
    this.selectedDay = (event.target as HTMLSelectElement).value;
  }
}
