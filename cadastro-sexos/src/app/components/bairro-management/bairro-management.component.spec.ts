import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BairroManagementComponent } from './bairro-management.component';

describe('BairroManagementComponent', () => {
  let component: BairroManagementComponent;
  let fixture: ComponentFixture<BairroManagementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BairroManagementComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BairroManagementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
