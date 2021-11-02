import { TestBed } from '@angular/core/testing';

import { InicioGuardService } from './inicio-guard.service';

describe('InicioGuardService', () => {
  let service: InicioGuardService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(InicioGuardService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
