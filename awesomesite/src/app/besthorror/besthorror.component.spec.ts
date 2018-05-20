import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BesthorrorComponent } from './besthorror.component';

describe('BesthorrorComponent', () => {
  let component: BesthorrorComponent;
  let fixture: ComponentFixture<BesthorrorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BesthorrorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BesthorrorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
