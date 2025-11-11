import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
// Importe o RouterOutlet e as diretivas de Rota
import { RouterOutlet, RouterLink, RouterLinkActive } from '@angular/router';

@Component({
  selector: 'app-root',
  standalone: true, // Confirma que é um componente standalone
  imports: [
    CommonModule,
    RouterOutlet,   // Adicione aqui
    RouterLink,     // Adicione aqui
    RouterLinkActive  // Adicione aqui
  ],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'] // Ou .scss se você estiver usando
})
export class AppComponent {
  title = 'comercio-angular-app';
}
