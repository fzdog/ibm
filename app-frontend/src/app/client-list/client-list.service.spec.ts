import { TestBed } from '@angular/core/testing';

import { ClientListService } from './client-list.service';

describe('ClientListService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ClientListService = TestBed.get(ClientListService);
    expect(service).toBeTruthy();
  });
});
