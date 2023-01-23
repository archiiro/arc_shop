import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PopulationGroupComponent } from './population-group.component';

describe('PopulationGroupComponent', () => {
  let component: PopulationGroupComponent;
  let fixture: ComponentFixture<PopulationGroupComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PopulationGroupComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PopulationGroupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
