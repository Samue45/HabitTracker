import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { FormsModule, NgForm } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { Register } from '../../interfaces/register';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [RouterModule, FormsModule, CommonModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  register: Register = new Register();
  confirmPassword: string = '';
  errorMessage: string = '';
  successMessage: string = '';

  constructor(private http: HttpClient, private router: Router) {}

  private static isUsernameValid(username: string): boolean {
    const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
    return emailPattern.test(username);
  }

  private static checkPasswordRequirements(password: string): string {
    const requirements = [
      { test: password.length >= 8, message: 'La contraseña debe tener al menos 8 caracteres' },
      { test: /[a-z]/.test(password), message: 'La contraseña debe incluir al menos una letra minúscula' },
      { test: /[A-Z]/.test(password), message: 'La contraseña debe incluir al menos una letra mayúscula' },
      { test: /\d/.test(password), message: 'La contraseña debe incluir al menos un número' },
      { test: /[@$!%*?&]/.test(password), message: 'La contraseña debe incluir al menos un carácter especial (@$!%*?&)' }
    ];

    for (const requirement of requirements) {
      if (!requirement.test) {
        return requirement.message;
      }
    }
    return '';
  }

  newRegister(form: NgForm) {
    if (!RegisterComponent.isUsernameValid(this.register.username)) {
      this.errorMessage = 'El nombre de usuario debe ser un correo electrónico válido';
      return;
    }

    const passwordError = RegisterComponent.checkPasswordRequirements(this.register.password);
    if (passwordError) {
      this.errorMessage = passwordError;
      return;
    }

    if (this.register.password !== this.confirmPassword) {
      this.errorMessage = 'Las contraseñas no coinciden';
      return;
    }

    const user = {
      username: this.register.username,
      password: this.register.password,
      firstname: this.register.firstname,
      lastname: this.register.lastname
    };

    const httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    };

    this.http.post("/auth/register", user, httpOptions).subscribe(
      () => {
        this.successMessage = 'Registro exitoso';
        this.errorMessage = '';
        form.resetForm();
        this.router.navigate(['/login']);
      },
      () => {
        this.errorMessage = 'Error en el registro';
        this.successMessage = '';
      }
    );
  }
}
