import {Component, OnInit, ViewChild} from '@angular/core';
import {InputLikeComponent} from '../input-like/input-like.component';
import {InputComponent} from '../input/input.component';
import {AbstractControl} from '@angular/forms';

@Component({
  selector: 'app-textarea-form-group',
  templateUrl: './text-area-form-group.component.html',
  styleUrls: ['./form-group.component.sass']
})
export class TextAreaFormGroupComponent extends InputLikeComponent implements OnInit {
  @ViewChild('input')
  input: InputComponent;

  constructor() {
    super();
  }

  ngOnInit() {
    super.ngOnInit();
  }

  get control(): AbstractControl {
    return this.input.control;
  }
}
