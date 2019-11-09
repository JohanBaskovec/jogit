import {PropertyConstraintsOptions, PropertyConstraints} from "./property-constraints";

export interface ObjectConstraintsOptions {
  propertyConstraints: PropertyConstraintsOptions[];

}

export class ObjectConstraints {
  constraints: {[fieldName: string]: PropertyConstraints} = {};
  constructor(options: ObjectConstraintsOptions) {
    for (const propertyConstraintOptions of options.propertyConstraints) {
      this.constraints[propertyConstraintOptions.name] = new PropertyConstraints(propertyConstraintOptions);
    }
  }
}
