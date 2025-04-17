import { TestBed } from '@angular/core/testing';

import { AssessmentDetailsService } from './assessment-details.service';

describe('AssessmentDetailsService', () => {
  let service: AssessmentDetailsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AssessmentDetailsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
