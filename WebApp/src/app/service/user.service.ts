import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../model/user';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private userApiLink: string = "http://localhost:8080/user";

  constructor(private httpClient: HttpClient) { }

  getAll(): Observable<User[]> {
    return this.httpClient
      .get<User[]>(this.userApiLink);
  }

  getAllAvailableSpots(employeeId: number, reservationDate: string): Observable<string[]> {
    const availableSpotsApiLink = `${this.userApiLink}/${employeeId}/availability/${reservationDate}`;

    return this.httpClient
      .get<string[]>(availableSpotsApiLink);
  }

  updatePwd(userId: any, pwd: string): Observable<string> {
    const changePwdApiUrl = `${this.userApiLink}/${userId}/change-password`;
    return this.httpClient.put<string>(changePwdApiUrl, pwd);
  }

}
