import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListadoReservasMvcComponent } from './listado-reservas-mvc.component';

describe('ListadoReservasMvcComponent', () => {
  let component: ListadoReservasMvcComponent;
  let fixture: ComponentFixture<ListadoReservasMvcComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListadoReservasMvcComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListadoReservasMvcComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
