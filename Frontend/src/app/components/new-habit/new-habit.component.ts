import { Component, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';
import { ReactiveFormsModule, FormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HabitService } from '../../services/habit.service';
import { Habit } from '../../interfaces/habit';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-new-habit',
  standalone: true,
  imports: [RouterModule, ReactiveFormsModule, CommonModule, FormsModule],
  templateUrl: './new-habit.component.html',
  styleUrls: ['./new-habit.component.css']
})
export class NewHabitComponent implements OnInit {
  habits: Habit[] = [];
  habitForm: FormGroup;
  errorMessage: string = '';

  constructor(private fb: FormBuilder, private habitService: HabitService) {
    this.habitForm = this.fb.group({
      name: ['', Validators.required],
      description: ['', Validators.required],
      type: ['', Validators.required],
      level_priority: ['', Validators.required],
      nameDay: ['', Validators.required]
    });
  }

  ngOnInit(): void {}

  addHabit(): void {
    if (this.habitForm.valid) {
      const newHabit: Habit = this.habitForm.value;
      this.habitService.postHabits(newHabit).subscribe(
        (habit: Habit) => {
          this.habits.push(habit);
          this.habitForm.reset(); // Resetea el formulario
        },
        (error) => {
          this.errorMessage = error.message || 'An error occurred while adding a new habit';
        }
      );
    } else {
      this.errorMessage = 'Please fill in all required fields';
    }
  }
}