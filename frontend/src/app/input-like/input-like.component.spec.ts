import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InputLikeComponent } from './input-like.component';

describe('InputLikeComponent', () => {
  let component: InputLikeComponent;
  let fixture: ComponentFixture<InputLikeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InputLikeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InputLikeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
