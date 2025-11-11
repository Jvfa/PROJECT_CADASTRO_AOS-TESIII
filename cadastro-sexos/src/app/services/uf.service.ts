import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Uf } from '../models/uf.model';

@Injectable({
  providedIn: 'root'
})
export class UfService {
  private apiUrl = 'http://localhost:8090/ufs';

  constructor(private http: HttpClient) { }

  getAll(): Observable<Uf[]> {
    return this.http.get<Uf[]>(this.apiUrl);
  }

  getById(id: number): Observable<Uf> {
    return this.http.get<Uf>(`${this.apiUrl}/${id}`);
  }

  create(uf: Omit<Uf, 'coduf'>): Observable<Uf> {
    return this.http.post<Uf>(this.apiUrl, uf);
  }

  update(id: number, uf: Uf): Observable<Uf> {
    return this.http.put<Uf>(`${this.apiUrl}/${id}`, uf);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
