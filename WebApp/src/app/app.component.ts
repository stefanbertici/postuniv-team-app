import { Component } from '@angular/core';
import { IdentityService } from './service/identity.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'Saloon';

  constructor(public identityService: IdentityService, public router: Router){}
}
