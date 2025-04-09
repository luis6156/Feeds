import { inject, Injectable, signal } from '@angular/core';
import {
  Auth,
  signInWithPopup,
  GoogleAuthProvider,
  OAuthProvider,
  signOut,
  onAuthStateChanged,
  User,
} from '@angular/fire/auth';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private auth = inject(Auth);
  private router = inject(Router);

  readonly currentUser = signal<User | null>(null);
  readonly isAuthenticated = signal<boolean>(false);
  readonly authError = signal<string | null>(null);
  readonly isLoading = signal<boolean>(false);

  constructor() {
    this.initializeAuth();
  }

  private initializeAuth(): void {
    this.isLoading.set(true);

    // Set up auth state listener - this will fire on initial load AND any auth state changes
    onAuthStateChanged(
      this.auth,
      (user) => {
        console.log('Auth state changed:', user ? `User ${user.uid} logged in` : 'No user');
        this.currentUser.set(user);
        this.isAuthenticated.set(!!user);
        this.isLoading.set(false);

        // If user exists, ensure they're on the main page
        if (user && window.location.pathname === '/login') {
          this.router.navigate(['/']);
        }
        // If no user and not on login page, redirect to login
        else if (!user && window.location.pathname !== '/login') {
          this.router.navigate(['/login']);
        }
      },
      (error) => {
        console.error('Auth state change error:', error);
        this.authError.set(error.message);
        this.isLoading.set(false);
      }
    );
  }

  async signInWithGoogle(): Promise<void> {
    this.isLoading.set(true);
    this.authError.set(null);

    try {
      const provider = new GoogleAuthProvider();
      await signInWithPopup(this.auth, provider);
      this.router.navigate(['/']);
    } catch (error) {
      this.authError.set((error as Error).message);
    } finally {
      this.isLoading.set(false);
    }
  }

  async signInWithApple(): Promise<void> {
    this.isLoading.set(true);
    this.authError.set(null);

    try {
      const provider = new OAuthProvider('apple.com');
      await signInWithPopup(this.auth, provider);
      this.router.navigate(['/']);
    } catch (error) {
      this.authError.set((error as Error).message);
    } finally {
      this.isLoading.set(false);
    }
  }

  async signOut(): Promise<void> {
    this.isLoading.set(true);
    this.authError.set(null);

    try {
      await signOut(this.auth);
      this.router.navigate(['/login']);
    } catch (error) {
      this.authError.set((error as Error).message);
    } finally {
      this.isLoading.set(false);
    }
  }

  getUserDisplayName(): string | null {
    return this.currentUser()?.displayName || null;
  }

  getUserEmail(): string | null {
    return this.currentUser()?.email || null;
  }

  getUserPhotoURL(): string | null {
    return this.currentUser()?.photoURL || null;
  }
}
