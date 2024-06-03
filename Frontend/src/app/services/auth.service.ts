import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private loginUrl = 'http://localhost:8080/auth/login';

  constructor(private http: HttpClient) { }

  login(username: string, password: string): Observable<{ token: string }> {
    return this.http.post<{ token: string }>(this.loginUrl, { username, password })
      .pipe(
        tap(response => {
          localStorage.setItem('authToken', response.token);
        })
      );
  }

  getToken(): string | null {
    if (typeof window !== 'undefined' && localStorage.getItem('token')) {
      return localStorage.getItem('token');
    } else {
      return null; // O manejar el caso donde localStorage no está disponible
    }
  }

  logout(): void {
    localStorage.removeItem('authToken');
  }
}