import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EthnicsCreateUpdateComponent } from './ethnics-create-update.component';

describe('EnthnicsCreateUpdateComponent', () => {
  let component: EthnicsCreateUpdateComponent;
  let fixture: ComponentFixture<EthnicsCreateUpdateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EthnicsCreateUpdateComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EthnicsCreateUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
