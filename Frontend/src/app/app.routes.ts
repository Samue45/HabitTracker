import { Routes } from '@angular/router';
import { ListHabitsComponent } from './components/list-habits/list-habits.component';
import { NewHabitComponent } from './components/new-habit/new-habit.component';
import { LoginComponent } from './components/login/login.component';
import { SingUpComponent } from './components/sing-up/sing-up.component';
import { HabitStatsComponent } from './components/habit-stats/habit-stats.component';
import { HomeComponent } from './components/home/home.component';

export const routes: Routes =
 [
    {path: '', component: HomeComponent},
    {path: 'list', component: ListHabitsComponent},
    {path: 'add', component: NewHabitComponent},
    {path: 'edit/:id', component: NewHabitComponent},
    {path: 'login', component: LoginComponent},
    {path: 'register', component: SingUpComponent},
    {path: 'stats', component: HabitStatsComponent },
    {path: '**', redirectTo: '', pathMatch: 'full'}


 ];
