import { TestBed } from '@angular/core/testing';

import { AssessmentRequestService } from './assessment-request.service';

describe('AssessmentRequestService', () => {
  let service: AssessmentRequestService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AssessmentRequestService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
