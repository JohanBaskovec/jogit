import {Injectable} from '@angular/core';
import {
  GetCurrentSessionReply,
  GetCurrentSessionRequest,
  Session
} from './grpc/session_pb';
import {SessionServiceClient} from './grpc/session_grpc_web_pb';
import {ReplaySubject} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SessionService {
  public readonly anonymousSession: Session = new Session();
  private session = new ReplaySubject<Session>(1);

  constructor(private sessionServiceClient: SessionServiceClient) {
    this.anonymousSession.setId('anonymous');
  }

  refreshSession() {
    this.session.next(null);
    const request = new GetCurrentSessionRequest();
    request.setSessiontoken(this.getSessionToken());
    this.sessionServiceClient.getCurrentSession(
      request,
      {},
      (error, response: GetCurrentSessionReply) => {
        if (error) {
          //TODO: display error popup
          return;
        }
        if (response.hasSession()) {
          this.session.next(response.getSession());
        } else {
          this.session.next(this.anonymousSession);
          this.removeSessionToken();
        }
      }
    );
  }

  getSession(): ReplaySubject<Session> {
    return this.session;
  }

  getSessionToken() {
    return window.localStorage.sessionToken;
  }

  private setSessionToken(sessionToken: string) {
    window.localStorage.sessionToken = sessionToken;
  }

  setSession(session: Session) {
    this.session.next(session);
    this.setSessionToken(session.getId());
  }

  sessionIsAnonymous(session: Session): boolean {
    return session.getId() == 'anonymous';
  }

  private removeSessionToken() {
    window.localStorage.removeItem("sessionToken");
  }
}
