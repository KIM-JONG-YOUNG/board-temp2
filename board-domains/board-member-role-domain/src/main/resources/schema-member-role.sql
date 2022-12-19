create table if not exists tb_member_role (
   	member_no bigint not null,
    role_no bigint not null,
    created_date_time datetime(6),
    updated_date_time datetime(6),
    state integer,
    primary key (member_no, role_no),
    unique (member_no),
    unique (role_no)
);