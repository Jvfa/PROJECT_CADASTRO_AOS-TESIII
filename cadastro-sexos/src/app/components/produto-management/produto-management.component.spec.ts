import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProdutoManagementComponent } from './produto-management.component';

describe('ProdutoManagementComponent', () => {
  let component: ProdutoManagementComponent;
  let fixture: ComponentFixture<ProdutoManagementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProdutoManagementComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProdutoManagementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
