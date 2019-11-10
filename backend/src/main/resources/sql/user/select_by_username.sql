select username user_username, password user_password, password_salt user_password_salt from appuser
where username=$1;
