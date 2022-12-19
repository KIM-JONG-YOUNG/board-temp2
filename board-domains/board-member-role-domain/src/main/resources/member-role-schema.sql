create table if not exists tb_member_role (
   	member_no bigint not null,
    role_no bigint not null,
    created_date_time datetime(6) default current_timestamp,
    updated_date_time datetime(6) default current_timestamp,
    state integer default 0,
    primary key (member_no, role_no),
    unique (member_no),
    unique (role_no)
);