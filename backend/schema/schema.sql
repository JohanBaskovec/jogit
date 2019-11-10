create table appuser
(
    username varchar(255) not null
        constraint user_username_pk
            primary key,
    password varchar(255) not null,
    password_salt varchar(255) not null
);

alter table appuser owner to postgres;

create unique index user_username_uindex
    on appuser (username);

create table roles_perms
(
    role varchar(255) not null
        constraint pk_roles_perms
            primary key,
    perm varchar(255) not null
);

alter table roles_perms owner to postgres;

create table user_roles
(
    username varchar(255) not null
        constraint fk_username
            references appuser,
    role varchar(255) not null
        constraint fk_roles
            references roles_perms,
    constraint pk_user_roles
        primary key (username, role)
);

alter table user_roles owner to postgres;

create table session
(
    username varchar(255) not null
        constraint session_user_username_fk
            references appuser,
    id text not null
        constraint session_id_pk
            primary key
);

alter table session owner to postgres;

create unique index session_id_uindex
    on session (id);

