import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, NgForm } from '@angular/forms';
import { TipoService } from '../../services/tipo.service';
import { Tipo } from '../../models/tipo.model';

@Component({
  selector: 'app-tipo-management',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './tipo-management.component.html',
  styleUrls: ['./tipo-management.component.scss']
})
export class TipoManagementComponent implements OnInit {

  tipos: Tipo[] = [];
  tipoAtual: Tipo = this.getEmptyTipo();
  isEditing = false;

  constructor(private tipoService: TipoService) { }

  ngOnInit(): void {
    this.loadTipos();
  }

  getEmptyTipo(): Tipo {
    return { codtipo: 0, nometipo: '' };
  }

  loadTipos(): void {
    this.tipoService.getAll().subscribe(data => {
      this.tipos = data;
    });
  }

  onSubmit(form: NgForm): void {
    if (!form.value.nometipo) return;

    if (this.isEditing && this.tipoAtual.codtipo) {
      const tipoAtualizado: Tipo = { codtipo: this.tipoAtual.codtipo, nometipo: form.value.nometipo };
      this.tipoService.update(this.tipoAtual.codtipo, tipoAtualizado).subscribe(() => {
        this.resetForm(form);
        this.loadTipos();
      });
    } else {
      const novoTipo: Omit<Tipo, 'codtipo'> = { nometipo: form.value.nometipo };
      this.tipoService.create(novoTipo).subscribe(() => {
        this.resetForm(form);
        this.loadTipos();
      });
    }
  }

  onEdit(tipo: Tipo): void {
    this.tipoAtual = { ...tipo };
    this.isEditing = true;
  }

  onDelete(id: number): void {
    if (confirm('Tem certeza?')) {
      this.tipoService.delete(id).subscribe(() => {
        this.loadTipos();
      });
    }
  }

  resetForm(form: NgForm): void {
    form.resetForm();
    this.tipoAtual = this.getEmptyTipo();
    this.isEditing = false;
  }
}
