import {Component, OnInit} from '@angular/core';
import {SessionService} from '../session.service';
import {Session} from '../grpc/session_pb';
import {User} from '../grpc/user_pb';
import {ActivatedRoute, ParamMap} from '@angular/router';

@Component({
  selector: 'app-my-repositories',
  templateUrl: './user-page.component.html',
  styleUrls: ['./user-page.component.sass']
})
export class UserPageComponent implements OnInit {
  private session: Session;
  loggedUser: User;
  showConfigurationMenu: boolean;
  private username: string;

  constructor(
    private sessionService: SessionService,
    private route: ActivatedRoute,
  ) {
    this.route.paramMap.subscribe((params: ParamMap) => {
      this.username = params.get('username');
      sessionService.getSession().subscribe((session) => {
        if (session == null) {
          return;
        }
        this.session = session;
        this.loggedUser = this.session.getUser();
        this.showConfigurationMenu = this.loggedUser.getUsername() === this.username;
      });
    });
  }

  ngOnInit(): void {
  }

}
