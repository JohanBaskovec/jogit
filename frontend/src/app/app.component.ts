import {Component, ViewChild} from '@angular/core';
import {SessionService} from "./session.service";
import {Session} from "./grpc/session_pb";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.sass']
})
export class AppComponent {
  @ViewChild("myAccountLink")
  myAccountLink: any;
  session: Session;

  constructor(private sessionService: SessionService) {
    sessionService.getSession().subscribe((session) => {
      this.session = session;
    });
    sessionService.refreshSession();
  }

}
