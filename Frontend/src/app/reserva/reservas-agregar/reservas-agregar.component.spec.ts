import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReservasAgregarComponent } from './reservas-agregar.component';

describe('ReservasAgregarComponent', () => {
  let component: ReservasAgregarComponent;
  let fixture: ComponentFixture<ReservasAgregarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReservasAgregarComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ReservasAgregarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
