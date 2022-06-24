create table if not exists users
(
    login varchar(64) not null
        constraint users_pk
        primary key,
    username varchar(64),
    password varchar(64)
);

alter table users owner to postgres;

create table if not exists roles
(
    id bigserial
        constraint table_name_pk
        primary key,
    name varchar
);

alter table roles owner to postgres;

create table if not exists user_roles
(
    user_login varchar
        constraint user_roles_users_login_fk
        references users,
    role_id integer
        constraint user_roles_roles_id_fk
        references roles
);