import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SexoListComponent } from './sexo-list.component';

describe('SexoListComponent', () => {
  let component: SexoListComponent;
  let fixture: ComponentFixture<SexoListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SexoListComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SexoListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
