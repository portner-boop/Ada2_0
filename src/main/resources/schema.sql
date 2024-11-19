create schema if not exists tasklist;

create table if not exists groups(
    id Integer primary key,
    group_name varchar(255)
);


create table if not exists users(
    id bigserial primary key,
    username varchar(255),
    password varchar(255),
    group_id integer

);
create table if not exists resources(
    id bigserial primary key,
    group_id integer,
    name varchar(255),
    constraint fk_resources foreign key (id) references groups (id)
);