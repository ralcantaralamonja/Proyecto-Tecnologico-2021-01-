import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CuartosEditarComponent } from './cuartos-editar.component';

describe('CuartosEditarComponent', () => {
  let component: CuartosEditarComponent;
  let fixture: ComponentFixture<CuartosEditarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CuartosEditarComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CuartosEditarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
