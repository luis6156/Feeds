import { Component, inject } from '@angular/core';
import { NgIf } from '@angular/common';
import { AuthService } from '../../api/auth.service';

@Component({
  selector: 'login',
  standalone: true,
  template: `
    <div class="flex flex-col items-center justify-center min-h-screen bg-gray-50">
      <div class="w-full max-w-md p-8 space-y-8 bg-white rounded-xl shadow-lg">
        <h1 class="text-2xl font-bold text-center text-gray-900">Sign in to Feeds</h1>
        
        @if (authService.isLoading()) {
          <div class="flex justify-center">
            <div class="w-10 h-10 border-4 border-primary border-t-transparent rounded-full animate-spin"></div>
          </div>
        }
        
        @if (authService.authError()) {
          <div class="p-4 text-sm text-red-700 bg-red-100 rounded-lg">
            {{ authService.authError() }}
          </div>
        }
        
        <div class="space-y-4">
          <button 
            (click)="signInWithGoogle()" 
            [disabled]="authService.isLoading()"
            class="flex items-center justify-center w-full px-4 py-2 text-white bg-red-500 rounded-lg hover:bg-red-600 disabled:opacity-50 disabled:cursor-not-allowed transition">
            <svg xmlns="http://www.w3.org/2000/svg" class="w-5 h-5 mr-2" viewBox="0 0 24 24">
              <path fill="currentColor" d="M12.545 10.239v3.821h5.445c-.712 2.315-2.647 3.972-5.445 3.972a6.033 6.033 0 0 1 0-12.064c1.498 0 2.866.549 3.921 1.453l2.814-2.814A9.969 9.969 0 0 0 12.545 2C7.021 2 2.543 6.477 2.543 12s4.478 10 10.002 10c8.396 0 10.249-7.85 9.426-11.748l-9.426-.013z"/>
            </svg>
            Sign in with Google
          </button>
          
          <button 
            (click)="signInWithApple()" 
            [disabled]="authService.isLoading()"
            class="flex items-center justify-center w-full px-4 py-2 text-white bg-gray-800 rounded-lg hover:bg-black disabled:opacity-50 disabled:cursor-not-allowed transition">
            <svg xmlns="http://www.w3.org/2000/svg" class="w-5 h-5 mr-2" viewBox="0 0 24 24">
              <path fill="currentColor" d="M17.05 20.28c-.98.95-2.05.8-3.08.35-1.09-.46-2.09-.48-3.24 0-1.44.62-2.2.44-3.06-.35C2.79 15.25 3.51 7.59 9.05 7.31c1.35.07 2.29.74 3.08.79 1.18-.28 2.3-.93 3.57-.84 1.36.12 2.4.54 3.06 1.34-2.48 1.52-1.88 4.76.95 5.61-1.01 2.04-2.18 4.07-2.66 5.07zM9.3 7.12c-.1-2.48 2.12-4.51 4.42-4.51.18 2.17-1.88 4.51-4.42 4.51z"/>
            </svg>
            Sign in with Apple
          </button>
        </div>
      </div>
    </div>
  `,
})
export class LoginComponent {
  protected authService = inject(AuthService);

  async signInWithGoogle(): Promise<void> {
    await this.authService.signInWithGoogle();
  }

  async signInWithApple(): Promise<void> {
    await this.authService.signInWithApple();
  }
}
