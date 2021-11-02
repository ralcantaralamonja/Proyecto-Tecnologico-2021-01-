import { TestBed } from '@angular/core/testing';

import { CuartosService } from './cuartos.service';

describe('CuartosService', () => {
  let service: CuartosService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CuartosService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
