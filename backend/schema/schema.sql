create schema public;

comment on schema public is 'standard public schema';

alter schema public owner to postgres;

create table "user" (
    username      varchar(255) not null
        constraint user_username_pk
            primary key,
    password      varchar(255) not null,
    password_salt varchar(255) not null
);

alter table "user"
    owner to postgres;

create unique index user_username_uindex
    on "user"(username);

create table roles_perms (
    role varchar(255) not null
        constraint pk_roles_perms
            primary key,
    perm varchar(255) not null
);

alter table roles_perms
    owner to postgres;

create table user_roles (
    username varchar(255) not null
        constraint fk_username
            references "user",
    role     varchar(255) not null
        constraint fk_roles
            references roles_perms,
    constraint pk_user_roles
        primary key (username, role)
);

alter table user_roles
    owner to postgres;

