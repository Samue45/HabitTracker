import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { NavbarComponent } from './components/navbar/navbar.component';
import {ListHabitsComponent} from './components/list-habits/list-habits.component';
import { RegisterComponent } from './components/register/register.component';
import { LoginComponent } from './components/login/login.component';
import { ReactiveFormsModule } from '@angular/forms';
import { EditHabitComponent } from './components/edit-habit/edit-habit.component';
import {HabitStatsComponent} from './components/habit-stats/habit-stats.component';
import { HomeComponent } from './components/home/home.component';


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet,NavbarComponent,ListHabitsComponent, ReactiveFormsModule,RegisterComponent,LoginComponent,EditHabitComponent,HomeComponent,HabitStatsComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'codedreamersApp';
}



