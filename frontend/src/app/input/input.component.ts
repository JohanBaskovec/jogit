import {Component, OnInit, ViewChild} from '@angular/core';
import {NgModel} from "@angular/forms";
import {InputLikeComponent} from "../input-like/input-like.component";

@Component({
  selector: 'app-input',
  templateUrl: './input.component.html',
  styleUrls: ['./input.component.sass']
})
export class InputComponent extends InputLikeComponent implements OnInit {
  @ViewChild("input")
  input: NgModel;

  constructor() {
    super();
  }

  ngOnInit(): void {
    this.form.addControl(this.input);
  }
}
