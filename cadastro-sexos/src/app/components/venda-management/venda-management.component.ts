import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, FormArray, Validators, ReactiveFormsModule } from '@angular/forms';

import { VendaService } from '../../services/venda.service';
import { ClienteService } from '../../services/cliente.service';
import { ProdutoService } from '../../services/produto.service';

import { Venda, VendaForm } from '../../models/venda.model';
import { Cliente } from '../../models/cliente.model';
import { Produto } from '../../models/produto.model';

@Component({
  selector: 'app-venda-management',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule
  ],
  templateUrl: './venda-management.component.html',
  styleUrls: ['./venda-management.component.scss']
})
export class VendaManagementComponent implements OnInit {

  vendas: Venda[] = [];
  clientes: Cliente[] = [];
  produtos: Produto[] = [];

  vendaForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private vendaService: VendaService,
    private clienteService: ClienteService,
    private produtoService: ProdutoService
  ) {
    this.vendaForm = this.fb.group({
      clienteId: [null, Validators.required],
      itens: this.fb.array([ this.criarItemForm() ]) // Começa com um item
    });
  }

  ngOnInit(): void {
    this.loadVendas();
    this.loadClientes();
    this.loadProdutos();
  }

  loadVendas(): void {
    this.vendaService.getAll().subscribe(data => this.vendas = data);
  }

  loadClientes(): void {
    this.clienteService.getAll().subscribe(data => this.clientes = data);
  }

  loadProdutos(): void {
    this.produtoService.getAll().subscribe(data => this.produtos = data);
  }

  // --- Controle do FormArray de Itens ---

  get itens(): FormArray {
    return this.vendaForm.get('itens') as FormArray;
  }

  criarItemForm(): FormGroup {
    return this.fb.group({
      produtoId: [null, Validators.required],
      quantidade: [1, [Validators.required, Validators.min(1)]]
    });
  }

  adicionarItem(): void {
    this.itens.push(this.criarItemForm());
  }

  removerItem(index: number): void {
    if (this.itens.length > 1) { // Não deixa remover o último item
      this.itens.removeAt(index);
    } else {
      alert('A venda precisa ter pelo menos um item.');
    }
  }

  // --- Ações de CRUD ---

  onSubmit(): void {
    if (this.vendaForm.invalid) {
      alert('Formulário inválido. Verifique o cliente e os itens.');
      return;
    }

    const formData: VendaForm = this.vendaForm.value;

    this.vendaService.create(formData).subscribe({
      next: () => {
        alert('Venda salva com sucesso! Estoque foi debitado.');
        this.loadVendas();
        this.loadProdutos(); // Recarrega produtos para ver estoque atualizado
        this.resetForm();
      },
      error: (err) => {
        console.error(err);
        // Exibe o erro do back-end (ex: Estoque insuficiente)
        alert('Erro ao salvar venda: ' + err.error);
      }
    });
  }

  onDelete(id: number): void {
    if (confirm('TEM CERTEZA?\nDeletar esta venda irá estornar os produtos para o estoque.')) {
      this.vendaService.delete(id).subscribe({
        next: () => {
          alert('Venda deletada! Os produtos foram estornados ao estoque.');
          this.loadVendas();
          this.loadProdutos(); // Recarrega produtos para ver estoque atualizado
        },
        error: (err) => {
          console.error(err);
          alert('Erro ao deletar venda: ' + err.error);
        }
      });
    }
  }

  resetForm(): void {
    this.vendaForm.reset();
    this.itens.clear();
    this.adicionarItem(); // Adiciona um item em branco
  }
}
