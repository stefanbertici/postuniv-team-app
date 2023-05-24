import { Component, OnInit } from '@angular/core';
import { Reservation } from 'src/app/model/reservation';
import { ReservationService } from 'src/app/service/reservation.service';

@Component({
  selector: 'app-reservation-list',
  templateUrl: './reservation-list.component.html',
  styleUrls: ['./reservation-list.component.scss']
})

export class ReservationListComponent implements OnInit {
  reservations: Reservation[] = [];
  displayedColumns: string[] = ['customer', 'serviciu', 'status', 'data', 'ora', 'actions'];

  constructor(private reservationService: ReservationService) { }

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

  //Todo: Temporary method, it will be part of a header
  logout() {
    localStorage.removeItem('saloon auth');
    location.reload();
  }


}
