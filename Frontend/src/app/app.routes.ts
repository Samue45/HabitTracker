import { Routes } from '@angular/router';
import { ListHabitsComponent } from './components/list-habits/list-habits.component';
import { NewHabitComponent } from './components/new-habit/new-habit.component';
import { LoginComponent } from './components/login/login.component';
import { SingUpComponent } from './components/sing-up/sing-up.component';

export const routes: Routes =
 [
    {path: '', component: ListHabitsComponent},
    {path: 'add', component: NewHabitComponent},
    {path: 'edit/:id', component: NewHabitComponent},
    {path: 'login', component: LoginComponent},
    {path: 'register', component: SingUpComponent},
    {path: '**', redirectTo: '', pathMatch: 'full'}


 ];
