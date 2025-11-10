import { Routes } from '@angular/router';

// Importe seus componentes
import { HomeComponent } from './components/home/home.component';
import { BairroManagementComponent } from './components/bairro-management/bairro-management.component';
import { CepManagementComponent } from './components/cep-management/cep-management.component';
import { CidadeManagementComponent } from './components/cidade-management/cidade-management.component';
import { ClienteManagementComponent } from './components/cliente-management/cliente-management.component';
import { MarcaManagementComponent } from './components/marca-management/marca-management.component';
import { ProdutoManagementComponent } from './components/produto-management/produto-management.component';
import { RuaManagementComponent } from './components/rua-management/rua-management.component';
import { SexoManagementComponent } from './components/sexo-management/sexo-management.component';
import { TipoManagementComponent } from './components/tipo-management/tipo-management.component';
import { UfManagementComponent } from './components/uf-management/uf-management.component';
import { VendaManagementComponent } from './components/venda-management/venda-management.component';

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'bairros', component: BairroManagementComponent },
  { path: 'ceps', component: CepManagementComponent },
  { path: 'cidades', component: CidadeManagementComponent },
  { path: 'clientes', component: ClienteManagementComponent },
  { path: 'marcas', component: MarcaManagementComponent },
  { path: 'produtos', component: ProdutoManagementComponent },
  { path: 'ruas', component: RuaManagementComponent },
  { path: 'sexos', component: SexoManagementComponent },
  { path: 'tipos', component: TipoManagementComponent },
  { path: 'ufs', component: UfManagementComponent },
  { path: 'vendas', component: VendaManagementComponent },
];
