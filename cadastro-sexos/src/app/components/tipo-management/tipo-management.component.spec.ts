import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TipoManagementComponent } from './tipo-management.component';

describe('TipoManagementComponent', () => {
  let component: TipoManagementComponent;
  let fixture: ComponentFixture<TipoManagementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TipoManagementComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TipoManagementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
