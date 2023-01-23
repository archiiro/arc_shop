import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HIVRiskPersonComponent } from './hivrisk-person.component';

describe('HIVRiskPersonComponent', () => {
  let component: HIVRiskPersonComponent;
  let fixture: ComponentFixture<HIVRiskPersonComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HIVRiskPersonComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HIVRiskPersonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
