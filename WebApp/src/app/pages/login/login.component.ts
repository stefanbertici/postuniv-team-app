import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Route, Router } from '@angular/router';
import { delay } from 'rxjs';
import { IdentityService } from 'src/app/service/identity.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  formGroup!: FormGroup;

  constructor(private identityService: IdentityService, private router: Router) { }

  ngOnInit(): void {
    this.initForm();
  }

  initForm() {
    this.formGroup = new FormGroup({
      username: new FormControl('', [Validators.required]),
      password: new FormControl('', [Validators.required])
    })

  }

  login() {
    if (this.formGroup.valid) {
      const login = this.identityService.login(this.formGroup);
    }
  }

}
