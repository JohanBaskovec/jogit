import {Injectable} from '@angular/core';
import {
  GetCurrentSessionReply,
  GetCurrentSessionRequest,
  Session
} from "./grpc/session_pb";
import {SessionServiceClient} from "./grpc/session_grpc_web_pb";

@Injectable({
  providedIn: 'root'
})
export class SessionService {
  private session: Session;

  constructor(private sessionServiceClient: SessionServiceClient) {
  }

  refreshSession() {
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
        console.log(response);
        if (response.hasSession()) {
          this.session = response.getSession()
        } else {
          this.session = null;
          this.setSessionToken(null);
        }
      }
    );
  }

  getSession(): Session {
    return this.session;
  }

  private getSessionToken() {
    return window.localStorage.sessionToken;
  }

  private setSessionToken(sessionToken: string) {
    window.localStorage.sessionToken = sessionToken;
  }

  setSession(session: Session) {
    this.session = session;
    this.setSessionToken(session.getId());
  }
}
