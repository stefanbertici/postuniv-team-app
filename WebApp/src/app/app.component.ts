import { Component } from '@angular/core';
import { IdentityService } from './service/identity.service';
import { Router, withDebugTracing } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { ReservationAddComponent } from './pages/reservation/reservation-add/reservation-add.component';
import { CategoryAddComponent } from './pages/category/category-add/category-add.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'Saloon';

  constructor(public identityService: IdentityService, public router: Router, private matDialog: MatDialog) { }
  
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

}
