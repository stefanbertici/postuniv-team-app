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
    status: string, resDate: string, resHour: number) {
    let reservationToBeSaved = { customerId, beautyServiceID, status, resDate, resHour };

    this.reservationService.save(reservationToBeSaved)
      .subscribe(_ => console.log("Reservation saved!"));
    location.reload();
  }

}
