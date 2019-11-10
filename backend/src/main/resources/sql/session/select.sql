select session.id session_id, session.username session_user_username, appuser.username user_username
from session
         join appuser on session.username = appuser.username
where session.id = $1;
