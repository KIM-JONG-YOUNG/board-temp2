create table tb_member_role (
   	member_no bigint not null,
    role_no bigint not null,
    created_date_time datetime(6),
    updated_date_time datetime(6),
    state integer,
    primary key (member_no, role_no)
);

alter table tb_member_role 
   	add constraint UK_member_no 
   		unique (member_no);

alter table tb_member_role 
   	add constraint UK_role_no 
   		unique (role_no);