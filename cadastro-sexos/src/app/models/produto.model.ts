import { Marca } from './marca.model';
import { Tipo } from './tipo.model';

export interface Produto {
  codproduto: number;
  nomeproduto: string;
  valor: number;
  quantidade: number;
  marca?: Marca;
  tipo?: Tipo;
}
