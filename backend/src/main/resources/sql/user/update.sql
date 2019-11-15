update appuser
    set password = $1,
        password_salt = $2,
        username = $3
