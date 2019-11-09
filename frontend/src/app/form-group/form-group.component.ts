import {Component, Input, OnInit} from '@angular/core';
import {InputLikeComponent} from "../input-like/input-like.component";

@Component({
  selector: 'app-form-group',
  templateUrl: './form-group.component.html',
  styleUrls: ['./form-group.component.sass']
})
export class FormGroupComponent extends InputLikeComponent implements OnInit {
  constructor() {
    super();
  }

  ngOnInit() {
    super.ngOnInit();
  }
}
