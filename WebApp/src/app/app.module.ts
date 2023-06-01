import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { MatFormFieldModule } from '@angular/material/form-field';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatCardModule } from '@angular/material/card';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { UserListComponent } from './pages/user/user-list/user-list.component';
import { UserDetailsComponent } from './pages/user/user-details/user-details.component';
import { AuthInterceptorProvider } from './auth.interceptor';
import { MatTableModule } from '@angular/material/table';
import { ReservationListComponent } from './pages/reservation/reservation-list/reservation-list.component';
import { ReservationDetailsComponent } from './pages/reservation/reservation-details/reservation-details.component';
import { MatDialogModule } from '@angular/material/dialog';
import { ReservationAddComponent } from './pages/reservation/reservation-add/reservation-add.component';
import { BeautyListComponent } from './pages/beauty/beauty-list/beauty-list.component';
import { BeautyAddComponent } from './pages/beauty/beauty-add/beauty-add.component';
import { CategoryAddComponent } from './pages/category/category-add/category-add.component';
import { CategoryListComponent } from './pages/category/category-list/category-list.component';
import { CategoryDetailsComponent } from './pages/category/category-details/category-details.component';
import { BeautyDetailsComponent } from './pages/beauty/beauty-details/beauty-details.component';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatSelectModule } from '@angular/material/select';
import { DatePipe } from '@angular/common';
import { NgxMatTimepickerModule } from 'ngx-mat-timepicker';
import { ReservationStatusComponent } from './pages/reservation/reservation-status/reservation-status.component';
import { MatMenuModule } from '@angular/material/menu';
import {MatPaginatorModule} from '@angular/material/paginator';
import { HomepageComponent } from './pages/homepage/homepage.component';
import { HeaderComponent } from './pages/header/header.component';
import { FooterComponent } from './pages/footer/footer.component';
import { FlexLayoutModule } from '@angular/flex-layout';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    UserListComponent,
    UserDetailsComponent,
    ReservationListComponent,
    ReservationDetailsComponent,
    ReservationAddComponent,
    BeautyListComponent,
    BeautyAddComponent,
    CategoryAddComponent,
    CategoryListComponent,
    CategoryDetailsComponent,
    BeautyDetailsComponent,
    ReservationStatusComponent,
    HomepageComponent,
    HeaderComponent,
    FooterComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,

    MatFormFieldModule,
    MatInputModule,
    ReactiveFormsModule,
    MatToolbarModule,
    MatCardModule,
    MatButtonModule,
    MatTableModule,
    MatDialogModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatSelectModule,
    DatePipe,
    FormsModule,
    NgxMatTimepickerModule,
    MatMenuModule,
    MatPaginatorModule,

    FlexLayoutModule
  ],
  providers: [AuthInterceptorProvider],
  bootstrap: [AppComponent]
})
export class AppModule { }
