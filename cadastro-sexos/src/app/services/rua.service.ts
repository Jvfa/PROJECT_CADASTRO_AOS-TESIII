import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Rua } from '../models/rua.model';

@Injectable({
  providedIn: 'root'
})
export class RuaService {
  private apiUrl = 'http://localhost:8090/ruas';

  constructor(private http: HttpClient) { }

  getAll(): Observable<Rua[]> {
    return this.http.get<Rua[]>(this.apiUrl);
  }

  getById(id: number): Observable<Rua> {
    return this.http.get<Rua>(`${this.apiUrl}/${id}`);
  }

  create(rua: Omit<Rua, 'codrua'>): Observable<Rua> {
    return this.http.post<Rua>(this.apiUrl, rua);
  }

  update(id: number, rua: Rua): Observable<Rua> {
    return this.http.put<Rua>(`${this.apiUrl}/${id}`, rua);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
