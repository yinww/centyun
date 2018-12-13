insert into lg_module (id, parent_id, name, english_name, icon, code, url, order_no, status, create_time)
    values(1, 0, '邮件推送', 'Direct Mail', 'fa-envelope', 'dm', 'http://mail.test.com', 1, 1, now());
insert into lg_module (id, parent_id, name, english_name, icon, code, url, order_no, status, create_time)
    values(2, 1, '邮包', 'Mail Package', 'fa-send', 'dm-mp', 'http://mail.test.com/package/index.html', 1, 1, now());
insert into lg_module (id, parent_id, name, english_name, icon, code, url, order_no, status, create_time)
    values(3, 1, '模板', 'Mail Template', 'fa-star', 'dm-mt', 'http://mail.test.com/template/index.html', 2, 1, now());
insert into lg_module (id, parent_id, name, english_name, icon, code, url, order_no, status, create_time)
    values(4, 1, '排除列表', 'Exclude Address', 'fa-hand-stop-o', 'dm-ea', 'http://mail.test.com/exclude/index.html', 3, 1, now());
insert into lg_module (id, parent_id, name, english_name, icon, code, url, order_no, status, create_time)
    values(5, 1, '配置', 'Config', 'fa-gear', 'dm-cf', 'http://mail.test.com/config/index.html', 4, 1, now());

insert into lg_module (id, parent_id, name, english_name, icon, code, url, order_no, status, create_time)
    values(21, 0, '行为追踪', 'Tracer', 'fa-binoculars', 'tr', 'http://tracer.test.com', 2, 1, now());
insert into lg_module (id, parent_id, name, english_name, icon, code, url, order_no, status, create_time)
    values(22, 21, '站点', 'Site Management', ' fa-file-code-o', 'tr-site', 'http://tracer.test.com/site/index.html', 1, 1, now());
insert into lg_module (id, parent_id, name, english_name, icon, code, url, order_no, status, create_time)
    values(23, 21, '报告', 'Report', 'fa-bar-chart', 'tr-rep', 'http://tracer.test.com/report/index.html', 2, 1, now());
/**
insert into lg_module (id, parent_id, name, english_name, icon, code, url, order_no, status, create_time)
    values(31, 21, '追踪码', 'Mail Template', 'fa-star', 'dm-mt', 'http://tracer.test.com/template/index.html', 1, now());
insert into lg_module (id, parent_id, name, english_name, icon, code, url, order_no, status, create_time)
    values(32, 21, '站点事件', 'Exclude Address', 'fa-hand-stop-o', 'dm-ea', 'http://tracer.test.com/exclude/index.html', 1, now());
insert into lg_module (id, parent_id, name, english_name, icon, code, url, order_no, status, create_time)
    values(33, 21, '追踪日志', 'Config', 'fa-gear', 'dm-cf', 'http://tracer.test.com/config/index.html', 1, now());
insert into lg_module (id, parent_id, name, english_name, icon, code, url, order_no, status, create_time)
    values(34, 21, '广告渠道', 'Mail Template', 'fa-star', 'dm-mt', 'http://tracer.test.com/template/index.html', 1, now());
insert into lg_module (id, parent_id, name, english_name, icon, code, url, order_no, status, create_time)
    values(35, 21, '落地页', 'Exclude Address', 'fa-hand-stop-o', 'dm-ea', 'http://tracer.test.com/exclude/index.html', 1, now());
insert into lg_module (id, parent_id, name, english_name, icon, code, url, order_no, status, create_time)
    values(36, 21, '落地页推广', 'Config', 'fa-gear', 'dm-cf', 'http://tracer.test.com/config/index.html', 1, now());
**/
