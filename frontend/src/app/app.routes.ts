import { inject } from '@angular/core';
import { Routes } from '@angular/router';
import { AuthGuard } from '../api/auth.guard';

export const routes: Routes = [
  {
    path: '',
    loadComponent: () => import('./home/home.component').then(c => c.HomeComponent),
    canActivate: [AuthGuard],
    title: 'Feeds',
  },
  {
    path: 'login',
    loadComponent: () => import('./login/login.component').then(c => c.LoginComponent),
    title: 'Login',
  },
  {
    path: '**',
    redirectTo: '',
  }
];
