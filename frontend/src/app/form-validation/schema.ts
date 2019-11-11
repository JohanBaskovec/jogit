import {ObjectConstraints} from './object-contraints';
import {PropertyConstraints} from './property-constraints';

export interface PropertyMap { [fieldName: string]: PropertyConstraints; }

export interface Schema {
  requests: {[requestName: string]: PropertyMap};
  definitions: {[requestName: string]: PropertyMap};
}
