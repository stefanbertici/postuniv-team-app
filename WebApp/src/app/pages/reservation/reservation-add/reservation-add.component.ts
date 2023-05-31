import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { Beauty } from 'src/app/model/beauty';
import { User } from 'src/app/model/user';
import { UserLogged } from 'src/app/model/user-logged';
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
  hasRole: UserLogged = Object.create(null);

  constructor(private reservationService: ReservationService,
    private matDialog: MatDialogRef<ReservationAddComponent>,
    private beautyService: BeautyService, private useService: UserService,
    private identityService: IdentityService) { }

  ngOnInit(): void {
    this.fetchAvailableServices();
    this.fetchEmployees();
    this.hasRole = this.identityService.loggedUser;
  }

  saveReservation(beautyServiceId: number, employeeId: number, date: string, hour: string) {
    let formattedDate: string = new Date(date).toLocaleDateString('fr-CA').toString(); //YYYY-MM-DD
    let reservationToBeSaved = {
      customerId: this.hasRole.id, beautyServiceId, employeeId,
      status: 'PENDING', date: formattedDate, hour
    };

    this.reservationService.save(reservationToBeSaved)
      .subscribe(_ => console.log("Reservation saved!"));
      location.reload();
  }

  saveReservationCustomerById(beautyServiceId: number, customerId: number, employeeId: number, date: string, hour: string) {
    let formattedDate: string = new Date(date).toLocaleDateString('fr-CA').toString(); //YYYY-MM-DD
    let reservationToBeSaved = {
      customerId, beautyServiceId, employeeId,
      status: 'PENDING', date: formattedDate, hour
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
