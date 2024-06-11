import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-navbar',
  standalone: true,
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {

  constructor(private authService: AuthService, private router: Router) {}

  logout() {
    this.authService.logout().subscribe({
      next: (response) => {
        console.log('Logout exitoso', response);
        
      },
      error: (error) => {
        console.error('Error al cerrar sesión:', error);
      },
      complete: () => {
        console.log('Logout completado');
      }
    });
  }

}


