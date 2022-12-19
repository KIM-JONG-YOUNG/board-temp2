insert ignore into tb_member (
	member_username,
    member_password,
    member_name,
    member_gender,
    member_email, 
    member_group, 
) values (
	'super_admin',
	'$2a$10$maVtWQbJsJ17g4C7MGA6aeB3WFgd5ZR75rWYLuEthexkIICerkLRW',
	'admin',
	'M',
	'admin@example.com',
	'SUPER_ADMIN'
);
