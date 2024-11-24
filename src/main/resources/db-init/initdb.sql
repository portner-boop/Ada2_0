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
                                        group_id Integer primary key,
                                        name varchar(255),
                                        constraint fk_resources foreign key (group_id) references groups (id)
);
insert into groups (id, group_name) values(1,'pi11')