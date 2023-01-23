import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HirSummaryReportsComponent } from './hir-summary-reports.component';

describe('HirSummaryReportsComponent', () => {
  let component: HirSummaryReportsComponent;
  let fixture: ComponentFixture<HirSummaryReportsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HirSummaryReportsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HirSummaryReportsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
