import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { ProdutoService } from '../../services/produto.service';
import { MarcaService } from '../../services/marca.service';
import { TipoService } from '../../services/tipo.service';
import { Produto } from '../../models/produto.model';
import { Marca } from '../../models/marca.model';
import { Tipo } from '../../models/tipo.model';

@Component({
  selector: 'app-produto-management',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule
  ],
  templateUrl: './produto-management.component.html',
  styleUrls: ['./produto-management.component.scss']
})
export class ProdutoManagementComponent implements OnInit {

  produtos: Produto[] = [];
  produtoForm: FormGroup;
  isEditing = false;
  currentProdutoId: number | null = null;

  // Listas para dropdowns
  marcas: Marca[] = [];
  tipos: Tipo[] = [];

  constructor(
    private fb: FormBuilder,
    private produtoService: ProdutoService,
    private marcaService: MarcaService,
    private tipoService: TipoService
  ) {
    this.produtoForm = this.fb.group({
      nomeproduto: ['', Validators.required],
      valor: [0, [Validators.required, Validators.min(0.01)]],
      quantidade: [0, [Validators.required, Validators.min(0)]],
      marca: [null, Validators.required],
      tipo: [null, Validators.required]
    });
  }

  ngOnInit(): void {
    this.loadProdutos();
    this.loadDropdowns();
  }

  loadProdutos(): void {
    this.produtoService.getAll().subscribe(data => this.produtos = data);
  }

  loadDropdowns(): void {
    this.marcaService.getAll().subscribe(data => this.marcas = data);
    this.tipoService.getAll().subscribe(data => this.tipos = data);
  }

  onSubmit(): void {
    if (this.produtoForm.invalid) {
      alert('Por favor, preencha todos os campos corretamente.');
      return;
    }

    const formValue = this.produtoForm.value;
    const payload = {
      ...formValue,
      marca: { codmarca: formValue.marca },
      tipo: { codtipo: formValue.tipo }
    };

    if (this.isEditing && this.currentProdutoId) {
      // Atualizar
      this.produtoService.update(this.currentProdutoId, payload).subscribe(() => {
        this.resetForm();
        this.loadProdutos();
      });
    } else {
      // Criar
      this.produtoService.create(payload).subscribe(() => {
        this.resetForm();
        this.loadProdutos();
      });
    }
  }

  onEdit(produto: Produto): void {
    this.isEditing = true;
    this.currentProdutoId = produto.codproduto;

    this.produtoForm.patchValue({
      nomeproduto: produto.nomeproduto,
      valor: produto.valor,
      quantidade: produto.quantidade,
      marca: produto.marca?.codmarca,
      tipo: produto.tipo?.codtipo
    });
  }

  onDelete(id: number): void {
    if (confirm('Tem certeza que deseja deletar este produto?')) {
      this.produtoService.delete(id).subscribe(() => {
        this.loadProdutos();
      });
    }
  }

  resetForm(): void {
    this.produtoForm.reset({ valor: 0, quantidade: 0 });
    this.isEditing = false;
    this.currentProdutoId = null;
  }
}
