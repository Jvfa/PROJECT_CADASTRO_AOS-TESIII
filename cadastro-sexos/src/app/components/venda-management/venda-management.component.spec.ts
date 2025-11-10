import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VendaManagementComponent } from './venda-management.component';

describe('VendaManagementComponent', () => {
  let component: VendaManagementComponent;
  let fixture: ComponentFixture<VendaManagementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VendaManagementComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(VendaManagementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
