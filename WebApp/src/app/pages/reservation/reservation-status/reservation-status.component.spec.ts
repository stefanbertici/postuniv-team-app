import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReservationStatusComponent } from './reservation-status.component';

describe('ReservationStatusComponent', () => {
  let component: ReservationStatusComponent;
  let fixture: ComponentFixture<ReservationStatusComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ReservationStatusComponent]
    });
    fixture = TestBed.createComponent(ReservationStatusComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
