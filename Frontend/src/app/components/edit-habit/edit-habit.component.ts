import { Component, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';
import { ReactiveFormsModule, FormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HabitService } from '../../services/habit.service';
import { Habit } from '../../interfaces/habit';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';



@Component({
  selector: 'app-edit-habit',
  standalone: true,
  imports: [RouterModule, ReactiveFormsModule, CommonModule, FormsModule],
  templateUrl: './edit-habit.component.html',
  styleUrls: ['./edit-habit.component.css']
})
export class EditHabitComponent implements OnInit {
  habitForm: FormGroup;
  habitId: number | undefined ;
  errorMessage: string = '';
  successMessage: string = ''; 

  constructor(private fb: FormBuilder, private habitService: HabitService , private router: Router,  private route: ActivatedRoute,) {
    this.habitForm = this.fb.group({
      name: ['', Validators.required],
      description: ['', Validators.required],
      type: ['', Validators.required],
      level_priority: ['', Validators.required],
      nameDay: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.habitId = +params['id'];
      this.loadHabit();
    });
  }

  loadHabit(): void {
    if (this.habitId === undefined) {
      this.errorMessage = 'Habit ID is undefined';
      return;
    }

    this.habitService.getHabitById(this.habitId).subscribe(
      (habit: Habit) => {
        this.habitForm.patchValue(habit);
      },
      error => {
        console.error('Error loading habit:', error);
      }
    );
  }

  onSaveChanges(): void {
    if (this.habitForm.valid) {
      const updatedHabit = { id: this.habitId, ...this.habitForm.value };
      console.log('Updated Habit:', updatedHabit);
      this.habitService.updateHabit(updatedHabit).subscribe(
        response => {
          console.log('Hábito editado:', response);
          this.successMessage = 'Hábito editado exitosamente!';
          setTimeout(() => {
            this.router.navigate(['/list-habits']);
          }, 2000); // Redirige después de 2 segundos
        },
        error => {
          console.error('Error editando habit:', error);
          this.errorMessage = 'Fallo al editar el hábito. Por favor, intentelo de nuevo.';
        }
      );
    }
  }
  
}