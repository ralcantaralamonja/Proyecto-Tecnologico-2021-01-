import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListaHuespedComponent } from './lista-huesped.component';

describe('ListaHuespedComponent', () => {
  let component: ListaHuespedComponent;
  let fixture: ComponentFixture<ListaHuespedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListaHuespedComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListaHuespedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
