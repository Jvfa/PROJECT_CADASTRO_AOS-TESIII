import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RuaListComponent } from './rua-list.component';

describe('RuaListComponent', () => {
  let component: RuaListComponent;
  let fixture: ComponentFixture<RuaListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RuaListComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RuaListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
