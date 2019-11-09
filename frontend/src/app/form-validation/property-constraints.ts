export interface PropertyConstraintsOptions {
  name: string;
  minLength: number;
  maxLength: number;
  required: boolean;
}

export class PropertyConstraints implements PropertyConstraintsOptions {
  constructor(options: PropertyConstraintsOptions) {
    this._name = options.name;
    this._minLength = options.minLength;
    this._maxLength = options.maxLength;
    this._required = options.required;
  }

  private readonly _maxLength: number;
  private readonly _minLength: number;
  private readonly _name: string;
  private readonly _required: boolean;

  get maxLength(): number {
    return this._maxLength;
  }

  get minLength(): number {
    return this._minLength;
  }

  get name(): string {
    return this._name;
  }

  get required(): boolean {
    return this._required;
  }
}
