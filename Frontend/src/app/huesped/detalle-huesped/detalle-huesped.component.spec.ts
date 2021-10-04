import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetalleHuespedComponent } from './detalle-huesped.component';

describe('DetalleHuespedComponent', () => {
  let component: DetalleHuespedComponent;
  let fixture: ComponentFixture<DetalleHuespedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DetalleHuespedComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DetalleHuespedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
