import { TestBed, inject } from '@angular/core/testing';

import { MovietitleapiService } from './movietitleapi.service';

describe('MovietitleapiService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [MovietitleapiService]
    });
  });

  it('should be created', inject([MovietitleapiService], (service: MovietitleapiService) => {
    expect(service).toBeTruthy();
  }));
});
