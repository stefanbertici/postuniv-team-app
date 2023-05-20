import { NgModule, inject } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegisterComponent } from './pages/register/register.component';
import { LoginComponent } from './pages/login/login.component';
import { IdentityService } from './service/identity.service';

const routes: Routes = [
  {path: 'register', component: RegisterComponent, canActivate:[()=>inject(IdentityService).isLoggedIn$]},
  {path: 'login', component: LoginComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
