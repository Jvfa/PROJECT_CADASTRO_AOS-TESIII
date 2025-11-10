import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CepManagementComponent } from './cep-management.component';

describe('CepManagementComponent', () => {
  let component: CepManagementComponent;
  let fixture: ComponentFixture<CepManagementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CepManagementComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CepManagementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
