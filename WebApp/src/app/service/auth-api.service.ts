import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthApiService {
  private authApiLink: String = "/";

  constructor(private httpClient: HttpClient) { }

  getAuthorized(loginInfo: any): Observable<any> {
    return this.httpClient.post('this.authApiLink', loginInfo);
  }
}
