import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {ButtonComponent} from './button/button.component';
import {FormGroupComponent} from './form-group/form-group.component';
import {InputComponent} from './input/input.component';
import {InputLikeComponent} from './input-like/input-like.component';
import {TextAreaFormGroupComponent} from './textarea-form-group/text-area-form-group.component';
import {TextAreaComponent} from './textarea/text-area.component';
import {FormsModule} from '@angular/forms';

@NgModule({
  declarations: [
    ButtonComponent,
    FormGroupComponent,
    InputComponent,
    InputLikeComponent,
    TextAreaFormGroupComponent,
    TextAreaComponent,
  ],
  imports: [
    CommonModule,
    FormsModule,
  ],
  exports: [
    ButtonComponent,
    FormGroupComponent,
    InputComponent,
    InputLikeComponent,
    TextAreaFormGroupComponent,
    TextAreaComponent,
  ]
})
export class AppFormModule { }
