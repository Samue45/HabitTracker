import { Component, OnInit } from '@angular/core';
import { Form, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';
import { habit } from '../../interfaces/habit';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-new-habit',
  standalone: true,
  imports: [RouterModule, ReactiveFormsModule,CommonModule],
  templateUrl: './new-habit.component.html',
  styleUrl: './new-habit.component.css'
})
export class NewHabitComponent implements OnInit{
 formNewHabit: FormGroup;

 constructor(private formBuilder: FormBuilder) {
      this.formNewHabit = this.formBuilder.group(
        {
          name: ['', Validators.required],
          description: ['', Validators.required],
          category: ['', Validators.required],
          priority: ['', Validators.required],
          daysPerWeek: [null, [Validators.required, Validators.min(1), Validators.max(7)]]
        }
      )



 }
 



  addHabit(){
    const newHabit : habit = {
      
      name: this.formNewHabit.value.name,
      description: this.formNewHabit.value.description,
      category: this.formNewHabit.value.category,
      priority: this.formNewHabit.value.priority,
      daysPerWeek: this.formNewHabit.value.daysPerWeek
    }


    console.log(newHabit);  

  }


  ngOnInit(): void {
   
  }
}
