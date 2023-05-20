import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { AuthApiService } from './auth-api.service';
import { FormGroup } from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class IdentityService {
  private _isLoggedIn$ = new BehaviorSubject<boolean>(false);
  isLoggedIn$ = this._isLoggedIn$.asObservable();

  get token() {
    return localStorage.getItem('saloon auth');
  }

  constructor(private authApiService: AuthApiService) {
    this._isLoggedIn$.next(!!this.token);
  }

  login(formGroup: FormGroup) {
    this.authApiService.getAuthorized(formGroup.value)
      .subscribe(result => {
        localStorage.setItem('saloon auth', result.token);
        this._isLoggedIn$.next(true);
      })
  }

  register(formGroup: FormGroup) {
    this.authApiService.getRegistered(formGroup.value)
      .subscribe(_ => console.log("ok!"));
  }


}
