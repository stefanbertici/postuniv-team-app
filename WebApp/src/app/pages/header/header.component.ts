import { Component } from '@angular/core';
import { IdentityService } from 'src/app/service/identity.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent {
  constructor(public identityService: IdentityService){}

  logout() {
    if (confirm("Logging out... Are you sure?")) {
      localStorage.removeItem('saloon auth');
      location.reload();
    }
  }

}
