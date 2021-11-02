import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NuevoHuespedComponent } from './nuevo-huesped.component';

describe('NuevoHuespedComponent', () => {
  let component: NuevoHuespedComponent;
  let fixture: ComponentFixture<NuevoHuespedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NuevoHuespedComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NuevoHuespedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
