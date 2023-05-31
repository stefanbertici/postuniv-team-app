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
    const reservationRestUrlUpdate = `${this.reservationApiLink}/${reservationUpdated.id}`;
    //Temporary object for update: goes in line of the JSON provided via swagger for update;
    let reservationUpdateEntityDto = { date: reservationUpdated.date, hour: reservationUpdated.hour };

    return this.httpClient
      .put<Reservation>(reservationRestUrlUpdate, reservationUpdateEntityDto);
  }

  //TODO: Type should be defined after we are getting a clear image of how a reservation will look as JSON
  save(reservation: any): Observable<any> {
    return this.httpClient
      .post<any>(this.reservationApiLink, reservation);
  }

  complete(reservationId: number): Observable<Reservation> {
    const reservationRestUrlComplete = `${this.reservationApiLink}/${reservationId}/complete`;
    return this.httpClient
      .get<Reservation>(reservationRestUrlComplete);
  }

  cancel(reservationId: number): Observable<Reservation> {
    const reservationRestUrlCancel = `${this.reservationApiLink}/${reservationId}/cancel`;
    return this.httpClient
      .get<Reservation>(reservationRestUrlCancel);
  }

  accept(reservationId: number): Observable<Reservation> {
    const reservationRestUrlAccept = `${this.reservationApiLink}/${reservationId}/accept`;
    return this.httpClient
      .get<Reservation>(reservationRestUrlAccept);
  }

}

