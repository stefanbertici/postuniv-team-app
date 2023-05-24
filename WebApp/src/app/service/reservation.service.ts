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
    let reservationUpdateEntityDto = { resDate: reservationUpdated.resDate, resHour: reservationUpdated.resHour };
    return this.httpClient
      .put<Reservation>(reservationRestUrlUpdate, reservationUpdateEntityDto);
  }

  //TODO: Type should be defined after we are getting a clear image of how a reservation will look as JSON
  save(reservation: any): Observable<any> {
    //Suggession: Refactoring the url based on BackendPotential Suggesion: "/save not necessary";
    const reservationRestUrlSave = `${this.reservationApiLink}/save`;

    return this.httpClient
      .post<any>(reservationRestUrlSave, reservation);
  }

}

