import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BairroListComponent } from './bairro-list.component';

describe('BairroListComponent', () => {
  let component: BairroListComponent;
  let fixture: ComponentFixture<BairroListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BairroListComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BairroListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
