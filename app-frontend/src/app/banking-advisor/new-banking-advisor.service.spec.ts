import { TestBed } from '@angular/core/testing';

import { NewBankingAdvisorService } from './new-banking-advisor.service';

describe('NewBankingAdvisorService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: NewBankingAdvisorService = TestBed.get(NewBankingAdvisorService);
    expect(service).toBeTruthy();
  });
});
