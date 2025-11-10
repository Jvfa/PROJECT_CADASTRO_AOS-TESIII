import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CidadeManagementComponent } from './cidade-management.component';

describe('CidadeManagementComponent', () => {
  let component: CidadeManagementComponent;
  let fixture: ComponentFixture<CidadeManagementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CidadeManagementComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CidadeManagementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
