import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {AbstractControl, NgForm} from "@angular/forms";
import {PropertyConstraints} from "../form-validation/property-constraints";

@Component({
  selector: 'app-input-like',
  templateUrl: './input-like.component.html',
  styleUrls: ['./input-like.component.sass']
})
export class InputLikeComponent implements OnInit {
  private _control: AbstractControl;

  @Input()
  constraints: PropertyConstraints;
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
  @Input()
  pattern: string;

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
    if (this.constraints != null) {
      this.minLength = this.constraints.minLength;
      this.maxLength = this.constraints.maxLength;
      this.required = this.constraints.required;
      this.pattern = this.constraints.pattern;
    }
    this.name = this.label.toLowerCase().replace(/ /g, '_');
  }
}
