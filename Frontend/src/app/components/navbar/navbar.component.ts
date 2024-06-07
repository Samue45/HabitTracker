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
    this.authService.logout().subscribe(
      () => {
        // Éxito: el logout se ha completado exitosamente
        console.log('Logout exitoso');
        // Limpiar el token de autenticación local
        this.authService.clearToken();
        // Redirigir al componente de login u otra página
        this.router.navigate(['/login']);
      },
      error => {
        // Error: no se pudo completar el logout
        console.error('Error al cerrar sesión:', error);
        // Aquí puedes mostrar un mensaje de error al usuario si lo deseas
      },
      () => {
        // Completado: el logout ha finalizado, puede ser útil para realizar alguna acción adicional si es necesario
        console.log('Logout completado');
      }
    );
  }
  
}


