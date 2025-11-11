import { Cliente } from './cliente.model';

// Para o formulário de criação
export interface ItemVendaForm {
  produtoId: number;
  quantidade: number;
}

export interface VendaForm {
  clienteId: number;
  itens: ItemVendaForm[];
}

// Para a resposta da API (quando listamos ou buscamos)
export interface Venda {
  codvenda: number;
  datavenda: Date;
  cliente?: Cliente;
  itens: any[]; // Pode detalhar isso como 'VendaProduto' se quiser
}
