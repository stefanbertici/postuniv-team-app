import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Reservation } from '../model/reservation';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ReservationService {
  private reservationApiLink: string = "http://localhost:8080/reservation";

  constructor(private httpClient: HttpClient) { }

  getAll(): Observable<Reservation[]> {
    return this.httpClient
      .get<Reservation[]>(this.reservationApiLink);
  }

  delete(reservationId: number): Observable<Reservation> {
    const reservationRestUrlDelete = `${this.reservationApiLink}/${reservationId}`;
    return this.httpClient
      .delete<Reservation>(reservationRestUrlDelete);
  }
  
}

