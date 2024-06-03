import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Habit } from '../interfaces/habit';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class HabitService {
  
  private readonly BASE_URL = 'http://localhost:8080/api/v1';
  private readonly HABITS_URL_GET = `${this.BASE_URL}/list-habits`;
  private readonly HABITS_URL_GET_ID = `${this.BASE_URL}/habit`;
  private readonly HABITS_URL_POST = `${this.BASE_URL}/new-habit`;
  private readonly HABITS_URL_DELETE = `${this.BASE_URL}/habit`;
  private readonly HABITS_URL_UPDATE = `${this.BASE_URL}/habit`;

  constructor(private http: HttpClient, private authService: AuthService) { }

  private getHeaders(): HttpHeaders {
    const token = this.authService.getToken() || localStorage.getItem('authToken');
    return new HttpHeaders({
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json'
    });
  }

  private handleError(error: HttpErrorResponse): Observable<never> {
    console.error('An error occurred:', error);
    // Customize this to provide more detailed error information if necessary
    return throwError('Something went wrong; please try again later.');
  }

  getHabits(): Observable<Habit[]> {
    const headers = this.getHeaders();
    return this.http.get<Habit[]>(this.HABITS_URL_GET, { headers })
      .pipe(
        catchError(this.handleError)
      );
  }

  postHabits(habit: Habit): Observable<Habit> {
    const headers = this.getHeaders();
    return this.http.post<Habit>(this.HABITS_URL_POST, habit, { headers })
      .pipe(
        catchError(this.handleError)
      );
  }

  deleteHabit(habitId: number): Observable<Habit> {
    const headers = this.getHeaders();
    return this.http.delete<Habit>(`${this.HABITS_URL_DELETE}/${habitId}`,{ headers })
    .pipe(
      catchError(this.handleError)
    );
  }

  updateHabit(habit: Habit): Observable<Habit> {
    const headers = this.getHeaders();
    return this.http.put<Habit>(`${this.HABITS_URL_UPDATE}/${habit.id}`, habit, { headers })
      .pipe(
        catchError(this.handleError)
      );
    }
  
  getHabitById(habitId: number): Observable<Habit> {
    const headers = this.getHeaders();
    return this.http.get<Habit>(`${this.HABITS_URL_GET_ID}/${habitId}`, { headers })
      .pipe(
        catchError(this.handleError)
      );
  }  

}
