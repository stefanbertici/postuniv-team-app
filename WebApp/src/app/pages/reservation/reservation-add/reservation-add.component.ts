import { Component } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { ReservationService } from 'src/app/service/reservation.service';

@Component({
  selector: 'app-reservation-add',
  templateUrl: './reservation-add.component.html',
  styleUrls: ['./reservation-add.component.scss']
})

export class ReservationAddComponent {

  constructor(private reservationService: ReservationService,
    private matDialog: MatDialogRef<ReservationAddComponent>) { }

  saveReservation(customerId: number, beautyServiceID: number,
    status: string, date: string, hour: string) {
    let reservationToBeSaved = { customerId, beautyServiceID, status, date, hour };

    this.reservationService.save(reservationToBeSaved)
      .subscribe(_ => console.log("Reservation saved!"));
    location.reload();
  }

}
