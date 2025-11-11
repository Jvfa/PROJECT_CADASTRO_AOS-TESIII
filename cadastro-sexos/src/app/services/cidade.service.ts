import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Cidade } from '../models/cidade.model';

// Interface para o Form de Cidade
export interface CidadeForm {
  nomecidade: string;
  nomeuf: string;
}

@Injectable({
  providedIn: 'root'
})
export class CidadeService {
  private apiUrl = 'http://localhost:8090/cidades';

  constructor(private http: HttpClient) { }

  getAll(): Observable<Cidade[]> {
    return this.http.get<Cidade[]>(this.apiUrl);
  }

  getById(id: number): Observable<Cidade> {
    return this.http.get<Cidade>(`${this.apiUrl}/${id}`);
  }

  create(cidadeForm: CidadeForm): Observable<Cidade> {
    return this.http.post<Cidade>(this.apiUrl, cidadeForm);
  }

  update(id: number, cidadeForm: CidadeForm): Observable<Cidade> {
    return this.http.put<Cidade>(`${this.apiUrl}/${id}`, cidadeForm);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
