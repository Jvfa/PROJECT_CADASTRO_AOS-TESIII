import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MarcaManagementComponent } from './marca-management.component';

describe('MarcaManagementComponent', () => {
  let component: MarcaManagementComponent;
  let fixture: ComponentFixture<MarcaManagementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MarcaManagementComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MarcaManagementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
