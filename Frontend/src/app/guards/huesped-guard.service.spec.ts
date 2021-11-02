import { TestBed } from '@angular/core/testing';

import { HuespedGuardService } from './huesped-guard.service';

describe('HuespedGuardService', () => {
  let service: HuespedGuardService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HuespedGuardService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
