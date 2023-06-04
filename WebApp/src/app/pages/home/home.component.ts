import { Component } from '@angular/core';
import { IdentityService } from 'src/app/service/identity.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent {

  constructor(public identityService: IdentityService){}

}
