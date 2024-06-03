import { Routes } from '@angular/router';
import { ListHabitsComponent } from './components/list-habits/list-habits.component';
import { NewHabitComponent } from './components/new-habit/new-habit.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { EditHabitComponent } from './components/edit-habit/edit-habit.component';
import { HabitStatsComponent } from './components/habit-stats/habit-stats.component';
import { HomeComponent } from './components/home/home.component';

export const routes: Routes =
 [
    {path: '', component: HomeComponent},
    {path: 'login', component: LoginComponent},
    {path: 'add', component: NewHabitComponent},
    {path: "edit/:id", component: EditHabitComponent},
    {path: 'list-habits', component: ListHabitsComponent},
    {path: 'register', component: RegisterComponent},
    {path: 'stats', component: HabitStatsComponent },
    {path: '**', redirectTo: '', pathMatch: 'full'}
 ];




