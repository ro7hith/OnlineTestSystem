import { TestBed } from '@angular/core/testing';

import { QuestionRequestService } from './question-request.service';

describe('QuestionRequestService', () => {
  let service: QuestionRequestService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(QuestionRequestService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
