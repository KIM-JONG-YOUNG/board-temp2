create table if not exists tb_member (
   	member_no bigint not null auto_increment,
    member_username varchar(30) not null,
    member_password varchar(60) not null,
    member_name varchar(30) not null,
    member_gender char(1) not null,
    member_email varchar(60) not null,
    member_group varchar(255) not null,
    created_date_time datetime(6) default current_timestamp,
    updated_date_time datetime(6) default current_timestamp,
    state integer default 1,
    primary key (member_no),
    unique (member_username)
);
