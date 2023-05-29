import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { Beauty } from 'src/app/model/beauty';
import { BeautyService } from 'src/app/service/beauty.service';
import { IdentityService } from 'src/app/service/identity.service';
import { ReservationService } from 'src/app/service/reservation.service';

@Component({
  selector: 'app-reservation-add',
  templateUrl: './reservation-add.component.html',
  styleUrls: ['./reservation-add.component.scss'],
})

export class ReservationAddComponent implements OnInit {
  beautyServiceRecived: Beauty[] = [];


  constructor(private reservationService: ReservationService,
    private matDialog: MatDialogRef<ReservationAddComponent>,
    private beautyService: BeautyService, private identityService: IdentityService) { }
    
  ngOnInit(): void {
    this.fetchAvailableServices();
  }

  saveReservation(beautyServiceId: number, employeeId: number, date: string, hour: string) {
    let formattedDate: string = new Date(date).toLocaleDateString('fr-CA').toString(); //YYYY-MM-DD
    //Todo: ENUMS for hardcoded values;
    let reservationToBeSaved = { customerId: this.identityService.loggedUser.id, 
      beautyServiceId, employeeId, status: 'PENDING', date: formattedDate, hour };

    console.log(reservationToBeSaved);
    this.reservationService.save(reservationToBeSaved)
      .subscribe(_ => console.log("Reservation saved!"));

  }

  fetchAvailableServices() {
    return this.beautyService.getAll()
      .subscribe(x => this.beautyServiceRecived = x);

  }
}
