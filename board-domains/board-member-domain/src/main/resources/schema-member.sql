create table if not exists tb_member (
   member_no bigint not null auto_increment,
    created_date_time datetime(6),
    state integer,
    updated_date_time datetime(6),
    member_email varchar(60) not null,
    member_gender char(1) not null,
    member_name varchar(30) not null,
    member_password varchar(60) not null,
    member_username varchar(30) not null,
    primary key (member_no),
    unique (member_username)
);