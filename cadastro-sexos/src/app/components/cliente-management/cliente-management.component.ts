import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { ClienteService } from '../../services/cliente.service';
import { BairroService } from '../../services/bairro.service';
import { CepService } from '../../services/cep.service';
import { CidadeService } from '../../services/cidade.service';
import { RuaService } from '../../services/rua.service';
import { SexoService } from '../../services/sexo.service';
import { Cliente } from '../../models/cliente.model';
import { Bairro } from '../../models/bairro.model';
import { Cep } from '../../models/cep.model';
import { Cidade } from '../../models/cidade.model';
import { Rua } from '../../models/rua.model';
import { Sexo } from '../../models/sexo.model';

@Component({
  selector: 'app-cliente-management',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule
  ],
  templateUrl: './cliente-management.component.html',
  styleUrls: ['./cliente-management.component.scss']
})
export class ClienteManagementComponent implements OnInit {

  clientes: Cliente[] = [];
  clienteForm: FormGroup;
  isEditing = false;
  currentClienteId: number | null = null;

  // Listas para os dropdowns
  bairros: Bairro[] = [];
  ceps: Cep[] = [];
  cidades: Cidade[] = [];
  ruas: Rua[] = [];
  sexos: Sexo[] = [];

  constructor(
    private fb: FormBuilder,
    private clienteService: ClienteService,
    private bairroService: BairroService,
    private cepService: CepService,
    private cidadeService: CidadeService,
    private ruaService: RuaService,
    private sexoService: SexoService
  ) {
    this.clienteForm = this.fb.group({
      nomecliente: ['', Validators.required],
      cpf: ['', Validators.required],
      datanasc: ['', Validators.required],
      numerocasa: ['', Validators.required],
      sexo: [null, Validators.required],
      cidade: [null, Validators.required],
      cep: [null, Validators.required],
      bairro: [null, Validators.required],
      rua: [null, Validators.required]
    });
  }

  ngOnInit(): void {
    this.loadClientes();
    this.loadDropdowns();
  }

  loadClientes(): void {
    this.clienteService.getAll().subscribe(data => this.clientes = data);
  }

  loadDropdowns(): void {
    this.bairroService.getAll().subscribe(data => this.bairros = data);
    this.cepService.getAll().subscribe(data => this.ceps = data);
    this.cidadeService.getAll().subscribe(data => this.cidades = data);
    this.ruaService.getAll().subscribe(data => this.ruas = data);
    this.sexoService.getAll().subscribe(data => this.sexos = data);
  }

  onSubmit(): void {
    if (this.clienteForm.invalid) {
      alert('Por favor, preencha todos os campos obrigatórios.');
      return;
    }

    // O back-end espera objetos aninhados com o ID
    const formValue = this.clienteForm.value;
    const payload = {
      ...formValue,
      sexo: { codsexo: formValue.sexo },
      cidade: { codcidade: formValue.cidade },
      cep: { codcep: formValue.cep },
      bairro: { codbairro: formValue.bairro },
      rua: { codrua: formValue.rua }
    };

    if (this.isEditing && this.currentClienteId) {
      // Atualizar
      this.clienteService.update(this.currentClienteId, payload).subscribe(() => {
        this.resetForm();
        this.loadClientes();
      });
    } else {
      // Criar
      this.clienteService.create(payload).subscribe(() => {
        this.resetForm();
        this.loadClientes();
      });
    }
  }

  onEdit(cliente: Cliente): void {
    this.isEditing = true;
    this.currentClienteId = cliente.codcliente;

    // Precisamos "achatar" os dados para o formulário
    this.clienteForm.patchValue({
      nomecliente: cliente.nomecliente,
      cpf: cliente.cpf,
      datanasc: new Date(cliente.datanasc).toISOString().split('T')[0], // Formata a data
      numerocasa: cliente.numerocasa,
      sexo: cliente.sexo.codsexo,
      cidade: cliente.cidade.codcidade,
      cep: cliente.cep.codcep,
      bairro: cliente.bairro.codbairro,
      rua: cliente.rua.codrua
    });
  }

  onDelete(id: number): void {
    if (confirm('Tem certeza que deseja deletar este cliente?')) {
      this.clienteService.delete(id).subscribe(() => {
        this.loadClientes();
      });
    }
  }

  resetForm(): void {
    this.clienteForm.reset();
    this.isEditing = false;
    this.currentClienteId = null;
  }
}
