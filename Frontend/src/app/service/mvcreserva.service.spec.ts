import { TestBed } from '@angular/core/testing';

import { MvcreservaService } from './mvcreserva.service';

describe('MvcreservaService', () => {
  let service: MvcreservaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MvcreservaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
