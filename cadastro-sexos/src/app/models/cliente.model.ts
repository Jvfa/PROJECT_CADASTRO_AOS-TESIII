import { Bairro } from './bairro.model';
import { Cep } from './cep.model';
import { Cidade } from './cidade.model';
import { Rua } from './rua.model';
import { Sexo } from './sexo.model';

export interface Cliente {
  codcliente: number;
  nomecliente: string;
  cpf: string;
  datanasc: Date;
  numerocasa: string;
  sexo: Sexo;
  cidade: Cidade;
  cep: Cep;
  bairro: Bairro;
  rua: Rua;
}
