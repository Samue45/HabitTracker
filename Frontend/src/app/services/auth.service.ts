import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  
  private loginUrl = 'http://localhost:8080/auth/login';
  private logoutUrl = 'http://localhost:8080/auth/logout';

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
    return localStorage.getItem('authToken');
  }

  logout(): Observable<any> {
    return this.http.post(this.logoutUrl, null, {
      headers: {
        Authorization: `Bearer ${this.getToken()}`
      }
    }).pipe(
      tap(() => {
        this.clearToken();
      })
    );
  }

  clearToken() {
    localStorage.removeItem('authToken');
  }
}


