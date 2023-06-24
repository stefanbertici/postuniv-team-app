import { Component } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { UserDetailsComponent } from '../../user/user-details/user-details.component';
import { Inject } from '@angular/core';
import { Reservation } from 'src/app/model/reservation';
import { ReservationService } from 'src/app/service/reservation.service';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-reservation-details',
  templateUrl: './reservation-details.component.html',
  styleUrls: ['./reservation-details.component.scss']
})

export class ReservationDetailsComponent {
  selectedReservation: Reservation = this.data;
  currentDate: Date = new Date();
  availableSpots: string[] = [];

  constructor(private matDialogRef: MatDialogRef<UserDetailsComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any, private reservationService: ReservationService,
    private userService: UserService) { }

  updateReservation(id: number, clientName: string, clientEmail: string, employeeName: string,
    status: string, data: string, hour: string) {
    let formattedDate: string = new Date(data).toLocaleDateString('fr-CA').toString(); //YYYY-MM-DD

    let reservationUpdated: Reservation = { id, clientName, clientEmail, employeeName, employeeId: this.selectedReservation.employeeId, status, date: formattedDate, hour };

    this.reservationService.update(reservationUpdated)
      .subscribe(_ => {
        console.log("Reservation updated!")
        location.reload();
      });
  }

  fetchAvailableSpots(reservationDate: string) {
    if (this.selectedReservation.employeeId != null && reservationDate != null) {
      let formattedDateFromEvent: string = new Date(reservationDate)
        .toLocaleDateString('fr-CA')
        .toString();

      this.userService.getAllAvailableSpots(this.selectedReservation.employeeId, formattedDateFromEvent)
        .subscribe(x => this.availableSpots = x);
    }
  }

  closeModalComponent() {
    this.matDialogRef.close();
  }

}
