import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, NgForm } from '@angular/forms';
import { SexoService } from '../../services/sexo.service';
import { Sexo } from '../../models/sexo.model';

@Component({
  selector: 'app-sexo-management',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './sexo-management.component.html',
  styleUrls: ['./sexo-management.component.scss']
})
export class SexoManagementComponent implements OnInit {

  sexos: Sexo[] = [];
  sexoAtual: Sexo = this.getEmptySexo();
  isEditing = false;

  constructor(private sexoService: SexoService) { }

  ngOnInit(): void {
    this.loadSexos();
  }

  getEmptySexo(): Sexo {
    return { codsexo: 0, nomesexo: '' };
  }

  loadSexos(): void {
    this.sexoService.getAll().subscribe(data => {
      this.sexos = data;
    });
  }

  onSubmit(form: NgForm): void {
    if (!form.value.nomesexo) return;

    if (this.isEditing && this.sexoAtual.codsexo) {
      const sexoAtualizado: Sexo = { codsexo: this.sexoAtual.codsexo, nomesexo: form.value.nomesexo };
      this.sexoService.update(this.sexoAtual.codsexo, sexoAtualizado).subscribe(() => {
        this.resetForm(form);
        this.loadSexos();
      });
    } else {
      const novoSexo: Omit<Sexo, 'codsexo'> = { nomesexo: form.value.nomesexo };
      this.sexoService.create(novoSexo).subscribe(() => {
        this.resetForm(form);
        this.loadSexos();
      });
    }
  }

  onEdit(sexo: Sexo): void {
    this.sexoAtual = { ...sexo };
    this.isEditing = true;
  }

  onDelete(id: number): void {
    if (confirm('Tem certeza?')) {
      this.sexoService.delete(id).subscribe(() => {
        this.loadSexos();
      });
    }
  }

  resetForm(form: NgForm): void {
    form.resetForm();
    this.sexoAtual = this.getEmptySexo();
    this.isEditing = false;
  }
}
