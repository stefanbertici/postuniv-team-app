import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthApiService {
  private authApiLink: string = "http://localhost:8080/api/auth/login";
  private authApiLinkRegister: string = "http://localhost:8080/api/auth/register";

  constructor(private httpClient: HttpClient) { }

  getAuthorized(loginInfo: any): Observable<any> {
    return this.httpClient.post(this.authApiLink, loginInfo);
  }

  getRegistered(loginInfo: any): Observable<any> {
    return this.httpClient.post(this.authApiLinkRegister, loginInfo);
  }
}
