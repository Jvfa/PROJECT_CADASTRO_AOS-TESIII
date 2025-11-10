import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RuaFormComponent } from './rua-form.component';

describe('RuaFormComponent', () => {
  let component: RuaFormComponent;
  let fixture: ComponentFixture<RuaFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RuaFormComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RuaFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
