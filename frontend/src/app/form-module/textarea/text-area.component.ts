import {Component, OnInit, ViewChild} from '@angular/core';
import {AbstractControl, NgModel} from '@angular/forms';
import {InputLikeComponent} from '../input-like/input-like.component';

@Component({
  selector: 'app-textarea',
  templateUrl: './text-area.component.html',
})
export class TextAreaComponent extends InputLikeComponent implements OnInit {
  @ViewChild('textarea')
  textArea: NgModel;

  constructor() {
    super();
  }

  ngOnInit(): void {
    super.ngOnInit();
    this.form.addControl(this.textArea);
  }

  get control(): AbstractControl {
    return this.textArea.control;
  }
}
