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

  update(reservationUpdated: Reservation): Observable<Reservation> {
    const reservationRestUrlUpdate= `${this.reservationApiLink}/${reservationUpdated.id}`;
    //Temporary object for update: goes in line of the JSON provided via swagger for update;
    let reservationUpdateEntityDto = {resDate: reservationUpdated.resDate, resHour: reservationUpdated.resHour};

    return this.httpClient
      .put<Reservation>(reservationRestUrlUpdate, reservationUpdateEntityDto);
  }

}

