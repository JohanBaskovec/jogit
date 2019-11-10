import { Component, OnInit } from '@angular/core';
import {SessionService} from "../session.service";

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.sass']
})
export class AccountComponent {

  constructor(public sessionService: SessionService) { }
}
