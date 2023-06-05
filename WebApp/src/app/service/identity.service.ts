import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { AuthApiService } from './auth-api.service';
import { FormGroup } from '@angular/forms';
import { UserLogged } from '../model/user-logged';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class IdentityService {
  private _isLoggedIn$ = new BehaviorSubject<boolean>(false);
  isLoggedIn$ = this._isLoggedIn$.asObservable();
  loggedUser!: UserLogged;

  get token(): any {
    return localStorage.getItem('saloon auth');
  }

  constructor(private authApiService: AuthApiService, private route: Router) {
    this._isLoggedIn$.next(!!this.token);
    if (this.token) {
      this.loggedUser = this.getLoggedUser(this.token);
    }
  }

  login(formGroup: FormGroup) {
    let userForLogin: { email: string, password: string } = {
      email: formGroup.get('username')?.value,
      password: formGroup.get('password')?.value
    }

    this.authApiService.getAuthorized(userForLogin)
      .subscribe({
        next: result => {
          localStorage.setItem('saloon auth', result.token);
          this.loggedUser = this.getLoggedUser(result.token);
          this._isLoggedIn$.next(true);
          this.route.navigate(['/reservations']);
        },
        error: () => {
          alert("Bad credentials!");
          location.reload();
        }
      });
  }

  register(formGroup: FormGroup) {
    this.authApiService.getRegistered(formGroup.value)
      .subscribe({
        next: _ => {
          alert("done");
          location.reload();
        },
        error: (err: any) => {
          alert("Email existent!");
        },
      });
  }

  private getLoggedUser(token: string): UserLogged {
    return JSON.parse(atob(token.split('.')[1])) as UserLogged;
  }

}
