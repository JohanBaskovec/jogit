import {Injectable} from '@angular/core';
import {PropertyMap, Schema} from '../form-module/schema';


@Injectable({
  providedIn: 'root'
})
export class FormValidationService {
  public registration: PropertyMap = {};
  public login: PropertyMap = {};
  public createGitRepository: PropertyMap = {};
  public user: PropertyMap = {};

  constructor(
    public schema: Schema
  ) {
    this.registration = schema.requests.RegisterRequest;
    this.login = schema.requests.LoginRequest;
    this.createGitRepository = schema.requests.CreateGitRepositoryRequest;
    this.user = schema.definitions.User;
  }
}
