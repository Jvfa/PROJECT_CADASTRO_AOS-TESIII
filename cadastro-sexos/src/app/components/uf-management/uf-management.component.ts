import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, NgForm } from '@angular/forms';
import { UfService } from '../../services/uf.service';
import { Uf } from '../../models/uf.model';

@Component({
  selector: 'app-uf-management',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './uf-management.component.html',
  styleUrls: ['./uf-management.component.scss']
})
export class UfManagementComponent implements OnInit {

  ufs: Uf[] = [];
  ufAtual: Uf = this.getEmptyUf();
  isEditing = false;

  constructor(private ufService: UfService) { }

  ngOnInit(): void {
    this.loadUfs();
  }

  getEmptyUf(): Uf {
    return { coduf: 0, nomeuf: '', sigla: '' };
  }

  loadUfs(): void {
    this.ufService.getAll().subscribe(data => {
      this.ufs = data;
    });
  }

  onSubmit(form: NgForm): void {
    if (!form.value.nomeuf || !form.value.sigla) return;

    if (this.isEditing && this.ufAtual.coduf) {
      const ufAtualizada: Uf = { ...form.value, coduf: this.ufAtual.coduf };
      this.ufService.update(this.ufAtual.coduf, ufAtualizada).subscribe(() => {
        this.resetForm(form);
        this.loadUfs();
      });
    } else {
      const novaUf: Omit<Uf, 'coduf'> = { nomeuf: form.value.nomeuf, sigla: form.value.sigla };
      this.ufService.create(novaUf).subscribe(() => {
        this.resetForm(form);
        this.loadUfs();
      });
    }
  }

  onEdit(uf: Uf): void {
    this.ufAtual = { ...uf };
    this.isEditing = true;
  }

  onDelete(id: number): void {
    if (confirm('Tem certeza?')) {
      this.ufService.delete(id).subscribe(() => {
        this.loadUfs();
      });
    }
  }

  resetForm(form: NgForm): void {
    form.resetForm();
    this.ufAtual = this.getEmptyUf();
    this.isEditing = false;
  }
}
