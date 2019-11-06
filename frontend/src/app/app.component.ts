import {Component} from '@angular/core';
import {SessionService} from "./session.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.sass']
})
export class AppComponent {
  constructor(private sessionService: SessionService) {
    sessionService.refreshSession();
  }
}
