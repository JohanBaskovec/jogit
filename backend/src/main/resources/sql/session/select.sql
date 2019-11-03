select session.id session_id, appuser.username user_username
from session
         join appuser on session.username = appuser.username
where session.id = $1;
