import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SexoFormComponent } from './sexo-form.component';

describe('SexoFormComponent', () => {
  let component: SexoFormComponent;
  let fixture: ComponentFixture<SexoFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SexoFormComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SexoFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
