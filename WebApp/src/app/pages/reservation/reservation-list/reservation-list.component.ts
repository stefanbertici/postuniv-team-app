import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Reservation } from 'src/app/model/reservation';
import { ReservationService } from 'src/app/service/reservation.service';
import { ReservationDetailsComponent } from '../reservation-details/reservation-details.component';
import { IdentityService } from 'src/app/service/identity.service';
import { ReservationStatusComponent } from '../reservation-status/reservation-status.component';

@Component({
  selector: 'app-reservation-list',
  templateUrl: './reservation-list.component.html',
  styleUrls: ['./reservation-list.component.scss']
})

export class ReservationListComponent implements OnInit {
  reservations: Reservation[] = [];
  displayedColumns: string[] = ['customer', 'email', 'serviciu', 'status', 'data', 'ora', 'actions'];
 
  
  constructor(private reservationService: ReservationService, private identityService: IdentityService,private matDialog: MatDialog) { }

  ngOnInit(): void {
    this.readAll();
  }

  readAll() {
    this.reservationService.getAll()
      .subscribe(x => this.reservations = x);
  }

  delete(reservationId: Reservation) {
    if (confirm("Are you sure?")) {
      this.reservationService.delete(reservationId.id)
        .subscribe(_ => console.log("Reservation deleted!"));
        location.reload();
    }
  }

  openUpdateComponent(reservation: Reservation) {
    this.matDialog.open(ReservationDetailsComponent, {
      data: reservation,
      height: '450px',
      width: '300px',
    });
  }

  openStatusComponent(reservation: Reservation) {
    this.matDialog.open(ReservationStatusComponent, {
      data: reservation,
      height: '170px',
      width: '300px',
    });
  }

  //Todo: Temporary method, it will be part of a header
  logout() {
    localStorage.removeItem('saloon auth');
    location.reload();
  }

}
