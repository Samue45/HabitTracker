import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { NavbarComponent } from './components/navbar/navbar.component';
import{ListHabitsComponent} from './components/list-habits/list-habits.component';
import{HabitStatsComponent} from './components/habit-stats/habit-stats.component';
import { HomeComponent } from './components/home/home.component';


import { ReactiveFormsModule } from '@angular/forms';




@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet,NavbarComponent,ListHabitsComponent, ReactiveFormsModule,HabitStatsComponent,HomeComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'codedreamersApp';
}
