import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RiskBehaviorCreateUpdateComponent } from './risk-behavior-create-update.component';

describe('RiskBehaviorCreateUpdateComponent', () => {
  let component: RiskBehaviorCreateUpdateComponent;
  let fixture: ComponentFixture<RiskBehaviorCreateUpdateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RiskBehaviorCreateUpdateComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RiskBehaviorCreateUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
