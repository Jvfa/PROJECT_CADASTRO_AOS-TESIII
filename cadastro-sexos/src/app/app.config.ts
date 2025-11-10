import { ApplicationConfig, importProvidersFrom } from '@angular/core';
import { provideRouter } from '@angular/router';
import { provideHttpClient } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { routes } from './app.routes';

export const appConfig: ApplicationConfig = {
  providers: [
    // 1. Registra as rotas
    provideRouter(routes),

    // 2. Registra o HttpClient para todos os Services
    provideHttpClient(),

    // 3. Registra os módulos de Formulário (Template e Reativo)
    importProvidersFrom(FormsModule, ReactiveFormsModule)
  ]
};
