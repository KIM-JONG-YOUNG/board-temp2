-- Password is 1234

insert ignore into tb_member (
	member_username,
	member_password, 
	member_name, 
	member_gender, 
	member_email, 
	created_date_time, 
	updated_date_time, 
	state
) values (
	'super_admin',
	'$2a$10$4cCWwNWxBJAlcKnGn3CSJOvLko.Jet2Avxesjl/XNYnFuEbENOfsy',
	'admin',
	'M',
	'admin@example.com',
	'2022-12-18',
	'2022-12-18',
	1
) on duplicate key update
member_password = '$2a$10$4cCWwNWxBJAlcKnGn3CSJOvLko.Jet2Avxesjl/XNYnFuEbENOfsy',
state = 1;

    