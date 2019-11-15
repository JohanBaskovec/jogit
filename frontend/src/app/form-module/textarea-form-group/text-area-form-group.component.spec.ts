import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TextAreaFormGroupComponent } from './text-area-form-group.component';

describe('FormGroupComponent', () => {
  let component: TextAreaFormGroupComponent;
  let fixture: ComponentFixture<TextAreaFormGroupComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TextAreaFormGroupComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TextAreaFormGroupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
