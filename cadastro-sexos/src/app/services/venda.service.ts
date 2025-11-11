import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Venda, VendaForm } from '../models/venda.model';

@Injectable({
  providedIn: 'root'
})
export class VendaService {
  private apiUrl = 'http://localhost:8090/vendas';

  constructor(private http: HttpClient) { }

  getAll(): Observable<Venda[]> {
    return this.http.get<Venda[]>(this.apiUrl);
  }

  getById(id: number): Observable<Venda> {
    return this.http.get<Venda>(`${this.apiUrl}/${id}`);
  }

  create(vendaForm: VendaForm): Observable<Venda> {
    return this.http.post<Venda>(this.apiUrl, vendaForm);
  }

  // ✅ NOVO MÉTODO ADICIONADO
  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
