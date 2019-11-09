import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {NgForm} from "@angular/forms";

@Component({
  selector: 'app-input-like',
  templateUrl: './input-like.component.html',
  styleUrls: ['./input-like.component.sass']
})
export class InputLikeComponent implements OnInit {
  @Input()
  form: NgForm;
  @Input()
  value: string;
  @Input()
  name: string;
  @Output()
  valueChange = new EventEmitter<string>();
  @Input()
  required: boolean;
  @Input()
  minLength: number;
  @Input()
  maxLength: number;
  @Input()
  label: string = "";

  onChange(value: string) {
    this.valueChange.emit(value);
  }

  ngOnInit(): void {
    if (this.form == null) {
      throw new Error("form required");
    }
    if (this.label == null) {
      throw new Error("label required");
    }
    this.name = this.label.toLowerCase().replace(/ /g, '_');
  }
}
