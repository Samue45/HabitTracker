import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { Register } from '../../interfaces/register';
import { NgForm } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';


@Component({
  selector: 'app-register',
  standalone: true,
  imports: [RouterModule,FormsModule,CommonModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})

export class RegisterComponent {

  register: Register = new Register()
  confirmPassword: string = '';
  errorMessage: string = '';
  successMessage: string = '';
  

  constructor(private http : HttpClient, private router: Router){}

  newRegister(form: NgForm) {
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
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      })
    };

    this.http.post("/auth/register", user, httpOptions)
      .subscribe(
        (data) => {
          this.successMessage = 'Registro exitoso';
          this.errorMessage = '';
          form.resetForm();
          this.router.navigate(['/list-habits']);
        },
        (error) => {
          this.errorMessage = 'Error en el registro';
          this.successMessage = '';
        }
      );
 
  }

}
