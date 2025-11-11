import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, NgForm } from '@angular/forms';
import { CepService } from '../../services/cep.service';
import { Cep } from '../../models/cep.model';

@Component({
  selector: 'app-cep-management',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './cep-management.component.html',
  styleUrls: ['./cep-management.component.scss']
})
export class CepManagementComponent implements OnInit {

  ceps: Cep[] = [];
  cepAtual: Cep = this.getEmptyCep();
  isEditing = false;

  constructor(private cepService: CepService) { }

  ngOnInit(): void {
    this.loadCeps();
  }

  getEmptyCep(): Cep {
    return { codcep: 0, numerocep: '' };
  }

  loadCeps(): void {
    this.cepService.getAll().subscribe(data => {
      this.ceps = data;
    });
  }

  onSubmit(form: NgForm): void {
    if (!form.value.numerocep) return;

    if (this.isEditing && this.cepAtual.codcep) {
      const cepAtualizado: Cep = { codcep: this.cepAtual.codcep, numerocep: form.value.numerocep };
      this.cepService.update(this.cepAtual.codcep, cepAtualizado).subscribe(() => {
        this.resetForm(form);
        this.loadCeps();
      });
    } else {
      const novoCep: Omit<Cep, 'codcep'> = { numerocep: form.value.numerocep };
      this.cepService.create(novoCep).subscribe(() => {
        this.resetForm(form);
        this.loadCeps();
      });
    }
  }

  onEdit(cep: Cep): void {
    this.cepAtual = { ...cep };
    this.isEditing = true;
  }

  onDelete(id: number): void {
    if (confirm('Tem certeza?')) {
      this.cepService.delete(id).subscribe(() => {
        this.loadCeps();
      });
    }
  }

  resetForm(form: NgForm): void {
    form.resetForm();
    this.cepAtual = this.getEmptyCep();
    this.isEditing = false;
  }
}
