import { Component } from '@angular/core';
import { IdentityService } from './service/identity.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'WebApp';

  constructor(public identityService: IdentityService){}
}
