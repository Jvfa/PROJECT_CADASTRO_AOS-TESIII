import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, NgForm } from '@angular/forms';
import { BairroService } from '../../services/bairro.service';
import { Bairro } from '../../models/bairro.model';

@Component({
  selector: 'app-bairro-management',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './bairro-management.component.html',
  styleUrls: ['./bairro-management.component.scss']
})
export class BairroManagementComponent implements OnInit {

  bairros: Bairro[] = [];
  bairroAtual: Bairro = this.getEmptyBairro();
  isEditing = false;

  constructor(private bairroService: BairroService) { }

  ngOnInit(): void {
    this.loadBairros();
  }

  getEmptyBairro(): Bairro {
    return { codbairro: 0, nomebairro: '' };
  }

  loadBairros(): void {
    this.bairroService.getAll().subscribe(data => {
      this.bairros = data;
    });
  }

  onSubmit(form: NgForm): void {
    if (!form.value.nomebairro) return;

    if (this.isEditing && this.bairroAtual.codbairro) {
      const bairroAtualizado: Bairro = { codbairro: this.bairroAtual.codbairro, nomebairro: form.value.nomebairro };
      this.bairroService.update(this.bairroAtual.codbairro, bairroAtualizado).subscribe(() => {
        this.resetForm(form);
        this.loadBairros();
      });
    } else {
      const novoBairro: Omit<Bairro, 'codbairro'> = { nomebairro: form.value.nomebairro };
      this.bairroService.create(novoBairro).subscribe(() => {
        this.resetForm(form);
        this.loadBairros();
      });
    }
  }

  onEdit(bairro: Bairro): void {
    this.bairroAtual = { ...bairro };
    this.isEditing = true;
  }

  onDelete(id: number): void {
    if (confirm('Tem certeza?')) {
      this.bairroService.delete(id).subscribe(() => {
        this.loadBairros();
      });
    }
  }

  resetForm(form: NgForm): void {
    form.resetForm();
    this.bairroAtual = this.getEmptyBairro();
    this.isEditing = false;
  }
}
