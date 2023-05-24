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

  updateReservation(id: number, customerId: number, beautyServiceId: number,
    status: string, resDate: string, resHour: number) {
    let reservationUpdated: Reservation = { id, customerId, beautyServiceId, status, resDate, resHour };

    this.reservationService.update(reservationUpdated)
      .subscribe(_ => console.log("Reservation updated!"));
    location.reload();
  }

}