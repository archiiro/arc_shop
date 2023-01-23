import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RiskBehaviorComponent } from './risk-behavior.component';

describe('RiskBehaviorComponent', () => {
  let component: RiskBehaviorComponent;
  let fixture: ComponentFixture<RiskBehaviorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RiskBehaviorComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RiskBehaviorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
