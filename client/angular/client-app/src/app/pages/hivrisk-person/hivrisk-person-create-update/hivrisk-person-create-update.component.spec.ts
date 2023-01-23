import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HIVRiskPersonCreateUpdateComponent } from './hivrisk-person-create-update.component';

describe('HIVRiskPersonCreateUpdateComponent', () => {
  let component: HIVRiskPersonCreateUpdateComponent;
  let fixture: ComponentFixture<HIVRiskPersonCreateUpdateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HIVRiskPersonCreateUpdateComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HIVRiskPersonCreateUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
