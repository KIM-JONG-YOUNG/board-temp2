create table tb_post (
   	post_no bigint not null auto_increment,
    post_content TEXT not null,
    post_title varchar(300) not null,
    post_views bigint not null,
    post_writer_no bigint not null,
    created_date_time datetime(6),
    updated_date_time datetime(6),
    state integer,
    primary key (post_no)
);
