import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { Beauty } from 'src/app/model/beauty';
import { User } from 'src/app/model/user';
import { BeautyService } from 'src/app/service/beauty.service';
import { IdentityService } from 'src/app/service/identity.service';
import { ReservationService } from 'src/app/service/reservation.service';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-reservation-add',
  templateUrl: './reservation-add.component.html',
  styleUrls: ['./reservation-add.component.scss'],
})

export class ReservationAddComponent implements OnInit {
  beautyServiceRecived: Beauty[] = [];
  employeeList: User[] = [];
  currentDate: Date = new Date();

  constructor(private reservationService: ReservationService,
    private matDialog: MatDialogRef<ReservationAddComponent>,
    private beautyService: BeautyService, private useService: UserService,
    private identityService: IdentityService) { }

  ngOnInit(): void {
    this.fetchAvailableServices();
    this.fetchEmployees();
  }

  saveReservation(beautyServiceId: number, employeeId: number, date: string, hour: string) {
    let formattedDate: string = new Date(date).toLocaleDateString('fr-CA').toString(); //YYYY-MM-DD
    //Todo: ENUMS for hardcoded values;
    let reservationToBeSaved = {
      customerId: this.identityService.loggedUser.id,
      beautyServiceId, employeeId, status: 'PENDING', date: formattedDate, hour
    };

    this.reservationService.save(reservationToBeSaved)
      .subscribe(_ => console.log("Reservation saved!"));

  }

  fetchAvailableServices() {
    return this.beautyService.getAll()
      .subscribe(x => this.beautyServiceRecived = x);
  }

  fetchEmployees() {
    return this.useService.getAll()
      .subscribe((x: User[]) => {
        this.employeeList = x.filter((y: User) => y.role == 'EMPLOYEE');
      });
  }
}
