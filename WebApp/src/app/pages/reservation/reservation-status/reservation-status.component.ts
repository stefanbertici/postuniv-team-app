import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialog, MatDialogRef } from '@angular/material/dialog';
import { Reservation } from 'src/app/model/reservation';
import { Status } from 'src/app/model/status';
import { ReservationService } from 'src/app/service/reservation.service';

@Component({
  selector: 'app-reservation-status',
  templateUrl: './reservation-status.component.html',
  styleUrls: ['./reservation-status.component.scss']
})
export class ReservationStatusComponent {
  selectedReservation: Reservation = this.data;

  statuses: Status[] = [
    { id: 1, status: 'Accept', active: false },
    { id: 2, status: 'Cancel', active: false },
    { id: 3, status: 'Complet', active: false },
  ];

  constructor(private reservationService: ReservationService,
    @Inject(MAT_DIALOG_DATA) public data: any, private matDialogRef: MatDialogRef<ReservationStatusComponent>) { }

  reservationStatus(status: number) {
    if (status == 1) {
      this.reservationService.accept(this.selectedReservation.id)
        .subscribe(_ => console.log("Accept ok!"));
    }
    if (status == 2) {
      this.reservationService.cancel(this.selectedReservation.id)
        .subscribe(_ => console.log("Cancel ok!"));
    }
    if (status == 3) {
      this.reservationService.complete(this.selectedReservation.id)
        .subscribe(_ => console.log("Complete ok!"));
    }
    location.reload();
  }

  closeModalComponent(){
    this.matDialogRef.close();
  }

}
