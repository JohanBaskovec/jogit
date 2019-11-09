import {Injectable} from '@angular/core';
import {ObjectConstraints} from "../object-contraints";

@Injectable({
  providedIn: 'root'
})
export class FormValidationService {
  constructor(
    public registration: ObjectConstraints,
    public login: ObjectConstraints,
  ) {
    console.log(registration);
  }
}
