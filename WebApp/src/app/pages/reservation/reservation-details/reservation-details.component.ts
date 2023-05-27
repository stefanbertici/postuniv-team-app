import { Component } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { UserDetailsComponent } from '../../user/user-details/user-details.component';
import { Inject } from '@angular/core';
import { Reservation } from 'src/app/model/reservation';
import { ReservationService } from 'src/app/service/reservation.service';

@Component({
  selector: 'app-reservation-details',
  templateUrl: './reservation-details.component.html',
  styleUrls: ['./reservation-details.component.scss']
})

export class ReservationDetailsComponent {
  selectedReservation: Reservation = this.data;

  constructor(private matDialogRef: MatDialogRef<UserDetailsComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any, private reservationService: ReservationService) { }

  // {
  //   "customerId": 1,
  //   "beautyServiceID": 1,
  //   "status": "PENDING",
  //   "resDate": "2023-05-02",
  //   "resHour": "00:00:00"
  // }
  //Todo: We miss the beautyServiceId from the GET-JSON.
  updateReservation(id: number, clientName: string, clientEmail: string, employeeName: string,
    status: string, date: string, hour: string) {
    let reservationUpdated: Reservation = { id, clientName, clientEmail, employeeName, status, date, hour };

    this.reservationService.update(reservationUpdated)
      .subscribe(_ => console.log("Reservation updated!"));
    location.reload();
  }

}
