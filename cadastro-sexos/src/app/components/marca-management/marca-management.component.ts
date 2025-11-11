import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, NgForm } from '@angular/forms'; // Importe FormsModule
import { MarcaService } from '../../services/marca.service';
import { Marca } from '../../models/marca.model';

@Component({
  selector: 'app-marca-management',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule // Adicione aqui
  ],
  templateUrl: './marca-management.component.html',
  styleUrls: ['./marca-management.component.scss']
})
export class MarcaManagementComponent implements OnInit {

  marcas: Marca[] = [];
  marcaAtual: Marca = this.getEmptyMarca();
  isEditing = false;

  constructor(private marcaService: MarcaService) { }

  ngOnInit(): void {
    this.loadMarcas();
  }

  getEmptyMarca(): Marca {
    return { codmarca: 0, nomemarca: '' };
  }

  loadMarcas(): void {
    this.marcaService.getAll().subscribe(data => {
      this.marcas = data;
    });
  }

  onSubmit(form: NgForm): void {
    if (!form.value.nomemarca) {
      alert('O nome da marca é obrigatório.');
      return;
    }

    if (this.isEditing && this.marcaAtual.codmarca) {
      // Atualizar
      const marcaAtualizada: Marca = { codmarca: this.marcaAtual.codmarca, nomemarca: form.value.nomemarca };
      this.marcaService.update(this.marcaAtual.codmarca, marcaAtualizada).subscribe(() => {
        this.resetForm(form);
        this.loadMarcas();
      });
    } else {
      // Criar
      const novaMarca: Omit<Marca, 'codmarca'> = { nomemarca: form.value.nomemarca };
      this.marcaService.create(novaMarca).subscribe(() => {
        this.resetForm(form);
        this.loadMarcas();
      });
    }
  }

  onEdit(marca: Marca): void {
    this.marcaAtual = { ...marca };
    this.isEditing = true;
  }

  onDelete(id: number): void {
    if (confirm('Tem certeza que deseja deletar esta marca?')) {
      this.marcaService.delete(id).subscribe(() => {
        this.loadMarcas();
      });
    }
  }

  resetForm(form: NgForm): void {
    form.resetForm();
    this.marcaAtual = this.getEmptyMarca();
    this.isEditing = false;
  }
}
