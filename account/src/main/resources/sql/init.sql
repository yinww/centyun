insert into ac_manager (id, login_name, password, display_name, phone, email, status, create_time, password_time)
    values('1', 'sysadmin', 'XJNT1NR72h9XuktvXZgJ1emY+rXGyAHzSDeaFXGIKk+86WVnXpbtaf24xsz9PWDq9Ber6aKK2FzlDX/Eof4nErrqV4EUo3nncofzBhxwqyU=', '系统管理员', '13718346788', '2402060178@qq.com', 1, now(), now());

insert into ac_module (id, code, name, english_name, icon, url, order_no, status, create_time)
    values('1', 'tenant', '租户管理', 'Tenant Management', 'fa fa-users', '/tenant/index.html', 1, 1, now());
insert into ac_module (id, code, name, english_name, icon, url, order_no, status, create_time)
    values('2', 'account', '账号管理', 'Account Management', 'fa fa-user', '/account/index.html', 2, 1, now());
insert into ac_module (id, code, name, english_name, icon, url, order_no, status, create_time)
    values('3', 'product', '产品管理', 'Product Management', 'fa fa-diamond', '/product/index.html', 3, 1, now());
insert into ac_module (id, code, name, english_name, icon, url, order_no, status, create_time)
    values('4', 'charge', '充值管理', 'Charge Management', 'fa fa-rmb', '/charge/index.html', 4, 1, now());
insert into ac_module (id, code, name, english_name, icon, url, order_no, status, create_time)
    values('5', 'audit', '日志管理', 'Audit Management', 'fa fa-file-text-o', '/audit/index.html', 5, 1, now());
