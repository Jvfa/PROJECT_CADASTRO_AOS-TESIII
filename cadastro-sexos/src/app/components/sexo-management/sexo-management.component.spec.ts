import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SexoManagementComponent } from './sexo-management.component';

describe('SexoManagementComponent', () => {
  let component: SexoManagementComponent;
  let fixture: ComponentFixture<SexoManagementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SexoManagementComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SexoManagementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
