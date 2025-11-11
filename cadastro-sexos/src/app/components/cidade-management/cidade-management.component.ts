import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms'; // Importe ReactiveFormsModule
import { CidadeService, CidadeForm } from '../../services/cidade.service';
import { UfService } from '../../services/uf.service';
import { Cidade } from '../../models/cidade.model';
import { Uf } from '../../models/uf.model';

@Component({
  selector: 'app-cidade-management',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule // Adicione aqui
  ],
  templateUrl: './cidade-management.component.html',
  styleUrls: ['./cidade-management.component.scss']
})
export class CidadeManagementComponent implements OnInit {

  cidades: Cidade[] = [];
  ufs: Uf[] = [];
  cidadeForm: FormGroup;
  isEditing = false;
  currentCidadeId: number | null = null;

  constructor(
    private fb: FormBuilder,
    private cidadeService: CidadeService,
    private ufService: UfService
  ) {
    this.cidadeForm = this.fb.group({
      nomecidade: ['', Validators.required],
      nomeuf: ['', Validators.required] // O form usa 'nomeuf', não o objeto Uf
    });
  }

  ngOnInit(): void {
    this.loadCidades();
    this.loadUfs();
  }

  loadCidades(): void {
    this.cidadeService.getAll().subscribe(data => this.cidades = data);
  }

  loadUfs(): void {
    this.ufService.getAll().subscribe(data => this.ufs = data);
  }

  onSubmit(): void {
    if (this.cidadeForm.invalid) {
      alert('Preencha todos os campos');
      return;
    }

    const formData: CidadeForm = this.cidadeForm.value;

    if (this.isEditing && this.currentCidadeId) {
      this.cidadeService.update(this.currentCidadeId, formData).subscribe(() => {
        this.resetForm();
        this.loadCidades();
      });
    } else {
      this.cidadeService.create(formData).subscribe(() => {
        this.resetForm();
        this.loadCidades();
      });
    }
  }

  onEdit(cidade: Cidade): void {
    this.isEditing = true;
    this.currentCidadeId = cidade.codcidade;

    this.cidadeForm.patchValue({
      nomecidade: cidade.nomecidade,
      nomeuf: cidade.uf?.nomeuf // O form espera o NOME da UF
    });
  }

  onDelete(id: number): void {
    if (confirm('Tem certeza que deseja deletar esta cidade?')) {
      this.cidadeService.delete(id).subscribe(() => {
        this.loadCidades();
      });
    }
  }

  resetForm(): void {
    this.cidadeForm.reset();
    this.isEditing = false;
    this.currentCidadeId = null;
  }
}
