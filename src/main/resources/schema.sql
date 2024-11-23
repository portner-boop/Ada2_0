create schema if not exists tasklist;
-- update groups set id =118100217 where id =1;
--  alter table groups rename column resources to res;
-- drop table groups;
-- drop table users;
 drop table resources;
-- drop table groups;
create table if not exists groups(
    id Integer primary key,
    group_name varchar(255),
    res varchar(255)
);


create table if not exists users(
    id bigserial primary key,
    username varchar(255),
    password varchar(255),
    group_id integer

);
create table if not exists resources(
    id serial primary key ,
    group_id Integer ,
    name varchar(255),
    constraint fk_resources foreign key (group_id) references groups (id)
);
insert into groups (id, group_name) values(1,'pi11');
delete  from resources where group_id is null;
