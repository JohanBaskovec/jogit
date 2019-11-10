select name git_repository_name, user_username git_repository_user_username
from git_repository
where user_username = $1
