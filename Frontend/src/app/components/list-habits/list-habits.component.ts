import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { HabitService } from '../../services/habit.service';
import { Habit } from '../../interfaces/habit';
import { FormsModule } from '@angular/forms';


@Component({
  selector: 'app-list-habits',
  standalone: true,
  imports: [CommonModule,RouterModule,FormsModule],
  templateUrl: './list-habits.component.html',
  styleUrls: ['./list-habits.component.css']
})
export class ListHabitsComponent implements OnInit {
  habits: Habit[] = [];
  errorMessage: string = '';

  constructor(private habitService: HabitService) {}

  ngOnInit(): void {
    this.loadHabits();
  }

  loadHabits(): void {
    this.habitService.getHabits().subscribe(
      (data: Habit[]) => {
        this.habits = data;
      },
      (error) => {
        this.errorMessage = error.message || 'An error occurred while fetching habits';
      }
    );
  }

  deleteHabit(habitId: number | undefined): void {
    if (habitId === undefined) {
      this.errorMessage = 'Habit ID is undefined';
      return;
    }

    this.habitService.deleteHabit(habitId).subscribe(
      (deletedHabit: Habit) => {
        this.habits = this.habits.filter(habit => habit.id !== habitId);
      },
      (error) => {
        this.errorMessage = error.message || 'An error occurred while deleting the habit';
      }
    );
  }

}
