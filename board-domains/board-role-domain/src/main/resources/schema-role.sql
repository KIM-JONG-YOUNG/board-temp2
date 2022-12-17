create table if not exists tb_authority (
   	authority_no bigint not null auto_increment,
    authority_access_method varchar(3) not null,
    authority_access_url_pattern varchar(60) not null,
    created_date_time datetime(6),
    updated_date_time datetime(6),
    state integer,
    primary key (authority_no)
);

create table if not exists tb_role (
   	role_no bigint not null auto_increment,
    role_name varchar(255) not null,
    primary key (role_no)
);

create table if not exists tb_role_authority (
    role_no bigint not null,
   	authority_no bigint not null,
    created_date_time datetime(6),
    updated_date_time datetime(6),
    state integer,
    primary key (authority_no, role_no)
);


alter table tb_role 
   	add if not exists constraint UK_role_name 
		unique (role_name);

alter table tb_role_authority 
   add if not exists constraint FK_tb_role_authority_tb_role_role_no 
   foreign key (role_no) 
   	  references tb_role (role_no)
   on delete cascade;

alter table tb_role_authority 
	add if not exists constraint FK_tb_role_authority_tb_authority_authority_no
   	foreign key (authority_no) 
		references tb_authority (authority_no)
   	on delete cascade;