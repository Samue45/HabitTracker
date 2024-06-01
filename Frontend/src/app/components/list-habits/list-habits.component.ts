import { Component, OnInit } from '@angular/core';
import { habit } from '../../interfaces/habit';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-list-habits',
  standalone: true,
  imports: [CommonModule,RouterModule],
  templateUrl: './list-habits.component.html',
  styleUrl: './list-habits.component.css'
})
export class ListHabitsComponent implements OnInit{
 
  listHabits: habit[] = [
      {
      id: 1,
      name: 'Drink Water',
      description: 'Drink 8 glasses of water a day',
      category: 'Health',
      priority: 'High',
      daysPerWeek: 7,
      daysOfWeek: ['Lunes','Martes'],
      completed: false
      },

      {
      id:2,
      name: 'walk',
      description: 'walk 30 minutes a day',
      category: 'Health',
      priority: 'High',
      daysPerWeek: 7,
      daysOfWeek: ['Lunes','Martes'],
      completed: false

      }

  ];

  ngOnInit(): void {
    
  }

}
