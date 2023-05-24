import { Component } from '@angular/core';
import { IdentityService } from './service/identity.service';
import { Router, withDebugTracing } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { ReservationAddComponent } from './pages/reservation/reservation-add/reservation-add.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'Saloon';

  constructor(public identityService: IdentityService, public router: Router, private matDialog: MatDialog) { }
  
  openSaveComponent() {
    this.matDialog.open(ReservationAddComponent, {
      height: '400px',
      width: '300px'
    });
  }
}
