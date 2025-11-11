import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Cep } from '../models/cep.model';

@Injectable({
  providedIn: 'root'
})
export class CepService {
  private apiUrl = 'http://localhost:8090/ceps';

  constructor(private http: HttpClient) { }

  getAll(): Observable<Cep[]> {
    return this.http.get<Cep[]>(this.apiUrl);
  }

  getById(id: number): Observable<Cep> {
    return this.http.get<Cep>(`${this.apiUrl}/${id}`);
  }

  create(cep: Omit<Cep, 'codcep'>): Observable<Cep> {
    return this.http.post<Cep>(this.apiUrl, cep);
  }

  update(id: number, cep: Cep): Observable<Cep> {
    return this.http.put<Cep>(`${this.apiUrl}/${id}`, cep);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
