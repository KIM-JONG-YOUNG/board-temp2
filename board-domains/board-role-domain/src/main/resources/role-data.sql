insert ignore into tb_role (
    role_name,
    role_access_method,
    role_access_url_pattern
) values (
	'ROLE_SUPER_ADMIN',
	'ALL',
	'http://*'
);
