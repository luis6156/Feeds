import { ApplicationConfig, provideZoneChangeDetection } from '@angular/core';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { provideRouter } from '@angular/router';
import { providePrimeNG } from 'primeng/config';
import Aura from '@primeng/themes/aura';

import { routes } from './app.routes';
import { getAuth } from 'firebase/auth';
import { provideAuth } from '@angular/fire/auth';
import { initializeApp, provideFirebaseApp } from '@angular/fire/app';
import { environment } from '../environments/environment';

export const appConfig: ApplicationConfig = {
  providers: [
    provideAnimationsAsync(),
    providePrimeNG({
      theme: {
          preset: Aura
      }
    }),
    provideZoneChangeDetection({ eventCoalescing: true }),
    provideFirebaseApp(() => {
      return initializeApp(environment.firebaseConfig);
    }),
    provideAuth(() => getAuth()),
    provideRouter(routes)
  ]
};
