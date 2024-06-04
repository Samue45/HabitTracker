import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { Login } from '../../interfaces/login';
import { AuthService } from '../../services/auth.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [RouterModule, FormsModule,CommonModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  login: Login = new Login();
  errorMessage: string = '';

  constructor(private authService: AuthService, private router: Router) { }

  loginUser(): void {
    this.authService.login(this.login.username, this.login.password).subscribe({
      next: (response) => {
        // Aquí se maneja la respuesta exitosa
        console.log('Login successful', response);
        // Supongamos que el token está en response.token
        localStorage.setItem('authToken', response.token);
        // Navegar a otra ruta
        this.router.navigate(['/list-habits']);
      },
      error: (error) => {
        // Aquí se maneja un error
        console.error('Login failed', error);
        this.errorMessage = 'Login failed. Please check your credentials and try again.';
      }
    });
  }
}
