import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RuaManagementComponent } from './rua-management.component';

describe('RuaManagementComponent', () => {
  let component: RuaManagementComponent;
  let fixture: ComponentFixture<RuaManagementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RuaManagementComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RuaManagementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
