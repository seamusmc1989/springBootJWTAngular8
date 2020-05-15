import { TestBed, inject } from '@angular/core/testing';
import {CarDetailService} from "./car-detail.service";

describe('CarDetailService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [CarDetailService]
    });
  });

  it('should be created', inject([CarDetailService], (service: CarDetailService) => {
    expect(service).toBeTruthy();
  }));
});
