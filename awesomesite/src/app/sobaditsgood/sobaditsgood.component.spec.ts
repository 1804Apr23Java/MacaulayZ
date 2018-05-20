import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SobaditsgoodComponent } from './sobaditsgood.component';

describe('SobaditsgoodComponent', () => {
  let component: SobaditsgoodComponent;
  let fixture: ComponentFixture<SobaditsgoodComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SobaditsgoodComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SobaditsgoodComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
