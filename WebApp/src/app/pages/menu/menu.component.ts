import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { IdentityService } from 'src/app/service/identity.service';
import { ReservationAddComponent } from '../reservation/reservation-add/reservation-add.component';
import { CategoryAddComponent } from '../category/category-add/category-add.component';
import { BeautyAddComponent } from '../beauty/beauty-add/beauty-add.component';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss']
})
export class MenuComponent {
  constructor(public identityService: IdentityService, private matDialog: MatDialog){}


  openSaveReservationComponent() {
    this.matDialog.open(ReservationAddComponent, {
      height: '400px',
      width: '300px'
    });
  }

  openSaveCategoryComponent() {
    this.matDialog.open(CategoryAddComponent, {
      height: '170px',
      width: '300px'
    });
  }

  openSaveServiceComponent() {
    this.matDialog.open(BeautyAddComponent, {
      height: '350px',
      width: '300px'
    });
  }
}
