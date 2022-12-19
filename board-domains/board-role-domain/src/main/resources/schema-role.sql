create table if not exists tb_role (
   	role_no bigint not null auto_increment,
    role_name varchar(30) not null,
    role_access_method varchar(10) not null,
    role_access_url_pattern varchar(60) not null,
    created_date_time datetime(6) default current_timestamp,
    updated_date_time datetime(6) default current_timestamp,
    state integer default 0,
    primary key (role_no),
    unique (role_name)
);

