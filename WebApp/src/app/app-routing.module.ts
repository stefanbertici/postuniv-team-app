import { NgModule, inject } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegisterComponent } from './pages/register/register.component';
import { LoginComponent } from './pages/login/login.component';
import { ReservationListComponent } from './pages/reservation/reservation-list/reservation-list.component';
import { hasRoleGuard } from './has-role.guard';
import { guardianGuard } from './guardian.guard';
import { BeautyListComponent } from './pages/beauty/beauty-list/beauty-list.component';
import { CategoryListComponent } from './pages/category/category-list/category-list.component';
import { UserListComponent } from './pages/user/user-list/user-list.component';

const routes: Routes = [
  { path: 'register', component: RegisterComponent },
  { path: 'login', component: LoginComponent },
  {
    path: 'reservations', component: ReservationListComponent,
    canActivate: [guardianGuard],

  },
  {
    path: 'services', component: BeautyListComponent,
    canActivate: [guardianGuard, hasRoleGuard],
    data: { role: 'ADMIN' }
  },
  {
    path: 'categories', component: CategoryListComponent,
    canActivate: [guardianGuard, hasRoleGuard],
    data: { role: 'ADMIN' }
  },
  {
    path: 'users', component: UserListComponent,
    canActivate: [guardianGuard, hasRoleGuard],
    data: { role: 'ADMIN' }
  },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
