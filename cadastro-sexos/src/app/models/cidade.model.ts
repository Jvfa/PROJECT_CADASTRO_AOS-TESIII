import { Uf } from './uf.model';

export interface Cidade {
  codcidade: number;
  nomecidade: string;
  uf?: Uf;
}
