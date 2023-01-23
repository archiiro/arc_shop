import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HirSummaryReportsCreateEditComponent } from './hir-summary-reports-create-edit.component';

describe('HirSummaryReportsCreateEditComponent', () => {
  let component: HirSummaryReportsCreateEditComponent;
  let fixture: ComponentFixture<HirSummaryReportsCreateEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HirSummaryReportsCreateEditComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HirSummaryReportsCreateEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
