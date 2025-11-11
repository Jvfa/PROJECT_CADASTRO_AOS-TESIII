import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, NgForm } from '@angular/forms';
import { RuaService } from '../../services/rua.service';
import { Rua } from '../../models/rua.model';

@Component({
  selector: 'app-rua-management',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './rua-management.component.html',
  styleUrls: ['./rua-management.component.scss']
})
export class RuaManagementComponent implements OnInit {

  ruas: Rua[] = [];
  ruaAtual: Rua = this.getEmptyRua();
  isEditing = false;

  constructor(private ruaService: RuaService) { }

  ngOnInit(): void {
    this.loadRuas();
  }

  getEmptyRua(): Rua {
    return { codrua: 0, nomerua: '' };
  }

  loadRuas(): void {
    this.ruaService.getAll().subscribe(data => {
      this.ruas = data;
    });
  }

  onSubmit(form: NgForm): void {
    if (!form.value.nomerua) return;

    if (this.isEditing && this.ruaAtual.codrua) {
      const ruaAtualizada: Rua = { codrua: this.ruaAtual.codrua, nomerua: form.value.nomerua };
      this.ruaService.update(this.ruaAtual.codrua, ruaAtualizada).subscribe(() => {
        this.resetForm(form);
        this.loadRuas();
      });
    } else {
      const novaRua: Omit<Rua, 'codrua'> = { nomerua: form.value.nomerua };
      this.ruaService.create(novaRua).subscribe(() => {
        this.resetForm(form);
        this.loadRuas();
      });
    }
  }

  onEdit(rua: Rua): void {
    this.ruaAtual = { ...rua };
    this.isEditing = true;
  }

  onDelete(id: number): void {
    if (confirm('Tem certeza?')) {
      this.ruaService.delete(id).subscribe(() => {
        this.loadRuas();
      });
    }
  }

  resetForm(form: NgForm): void {
    form.resetForm();
    this.ruaAtual = this.getEmptyRua();
    this.isEditing = false;
  }
}
