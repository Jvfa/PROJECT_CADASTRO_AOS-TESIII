import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClienteManagementComponent } from './cliente-management.component';

describe('ClienteManagementComponent', () => {
  let component: ClienteManagementComponent;
  let fixture: ComponentFixture<ClienteManagementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ClienteManagementComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ClienteManagementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
