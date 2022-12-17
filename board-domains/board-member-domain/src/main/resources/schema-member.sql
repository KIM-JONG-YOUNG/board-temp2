create table if not exists tb_member (
   	member_no bigint not null auto_increment,
    member_email varchar(60) not null,
    member_gender char(1) not null,
    member_name varchar(30) not null,
    member_password varchar(60) not null,
    member_username varchar(30) not null,
    created_date_time datetime(6),
    updated_date_time datetime(6),
    state integer,
    primary key (member_no)
);

alter table tb_member 
   add if not exists constraint UK_member_username 
      unique (member_username);
