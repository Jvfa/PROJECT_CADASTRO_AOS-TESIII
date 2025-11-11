import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Sexo } from '../models/sexo.model';

@Injectable({
  providedIn: 'root'
})
export class SexoService {
  private apiUrl = 'http://localhost:8090/sexos';

  constructor(private http: HttpClient) { }

  getAll(): Observable<Sexo[]> {
    return this.http.get<Sexo[]>(this.apiUrl);
  }

  getById(id: number): Observable<Sexo> {
    return this.http.get<Sexo>(`${this.apiUrl}/${id}`);
  }

  create(sexo: Omit<Sexo, 'codsexo'>): Observable<Sexo> {
    return this.http.post<Sexo>(this.apiUrl, sexo);
  }

  update(id: number, sexo: Sexo): Observable<Sexo> {
    return this.http.put<Sexo>(`${this.apiUrl}/${id}`, sexo);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
