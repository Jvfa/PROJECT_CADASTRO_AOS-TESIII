import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UfManagementComponent } from './uf-management.component';

describe('UfManagementComponent', () => {
  let component: UfManagementComponent;
  let fixture: ComponentFixture<UfManagementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UfManagementComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UfManagementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
