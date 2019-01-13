insert into lg_module (id, parent_id, name, english_name, icon, code, url, order_no, status, create_time)
    values(1, 0, '邮件推送', 'Direct dm', 'fa-envelope', 'dm', 'http://dm.test.com', 1, 1, now());
insert into lg_module (id, parent_id, name, english_name, icon, code, url, order_no, status, create_time)
    values(2, 1, '邮包', 'dm Package', 'fa-send', 'dm-mp', 'http://dm.test.com/package/index.html', 1, 1, now());
insert into lg_module (id, parent_id, name, english_name, icon, code, url, order_no, status, create_time)
    values(3, 1, '模板', 'dm Template', 'fa-star', 'dm-mt', 'http://dm.test.com/template/index.html', 2, 1, now());
insert into lg_module (id, parent_id, name, english_name, icon, code, url, order_no, status, create_time)
    values(4, 1, '排除列表', 'Exclude Address', 'fa-hand-stop-o', 'dm-ea', 'http://dm.test.com/exclude/index.html', 3, 1, now());
insert into lg_module (id, parent_id, name, english_name, icon, code, url, order_no, status, create_time)
    values(5, 1, '配置', 'Config', 'fa-gear', 'dm-cf', 'http://dm.test.com/config/index.html', 4, 1, now());

insert into lg_module (id, parent_id, name, english_name, icon, code, url, order_no, status, create_time)
    values(21, 0, '行为追踪', 'Tracer', 'fa-binoculars', 'tr', 'http://tracer.test.com', 2, 1, now());
insert into lg_module (id, parent_id, name, english_name, icon, code, url, order_no, status, create_time)
    values(22, 21, '站点', 'Site Management', ' fa-file-code-o', 'tr-site', 'http://tracer.test.com/site/index.html', 1, 1, now());
insert into lg_module (id, parent_id, name, english_name, icon, code, url, order_no, status, create_time)
    values(23, 21, '报告', 'Report', 'fa-bar-chart', 'tr-rep', 'http://tracer.test.com/report/index.html', 2, 1, now());


insert into lg_module (id, parent_id, name, english_name, icon, code, url, order_no, status, create_time)
    values(41, 0, '建站', 'Web', 'fa-binoculars', 'wb', 'http://cms.test.com', 3, 1, now());
insert into lg_module (id, parent_id, name, english_name, icon, code, url, order_no, status, create_time)
    values(42, 41, '站点', 'Site', 'fa-bar-chart', 'wb-rep', 'http://cms.test.com/site/index.html', 1, 1, now());
insert into lg_module (id, parent_id, name, english_name, icon, code, url, order_no, status, create_time)
    values(43, 41, '栏目', 'Category', ' fa-file-code-o', 'wb-site', 'http://cms.test.com/category/index.html', 2, 1, now());
insert into lg_module (id, parent_id, name, english_name, icon, code, url, order_no, status, create_time)
    values(44, 41, '文章', 'Article', 'fa-bar-chart', 'wb-rep', 'http://cms.test.com/article/index.html', 3, 1, now());
insert into lg_module (id, parent_id, name, english_name, icon, code, url, order_no, status, create_time)
    values(45, 41, '轮播图', 'Carousel', 'fa-bar-chart', 'wb-rep', 'http://cms.test.com/carousel/index.html', 4, 1, now());
insert into lg_module (id, parent_id, name, english_name, icon, code, url, order_no, status, create_time)
    values(46, 41, '友情链接', 'Friend Links', 'fa-bar-chart', 'wb-rep', 'http://cms.test.com/friendlink/index.html', 5, 1, now());
insert into lg_module (id, parent_id, name, english_name, icon, code, url, order_no, status, create_time)
    values(47, 41, '问卷', 'Questionnaire', 'fa-bar-chart', 'wb-rep', 'http://cms.test.com/question/index.html', 6, 1, now());
insert into lg_module (id, parent_id, name, english_name, icon, code, url, order_no, status, create_time)
    values(48, 41, '产品', 'product', 'fa-bar-chart', 'wb-rep', 'http://cms.test.com/product/index.html', 7, 1, now());

insert into lg_module (id, parent_id, name, english_name, icon, code, url, order_no, status, create_time)
    values(61, 0, '记账', 'Book', 'fa-binoculars', 'wb', 'http://book.test.com', 3, 1, now());
insert into lg_module (id, parent_id, name, english_name, icon, code, url, order_no, status, create_time)
    values(62, 61, '客户', 'Customer', 'fa-bar-chart', 'wb-rep', 'http://book.test.com/customer/index.html', 1, 1, now());
insert into lg_module (id, parent_id, name, english_name, icon, code, url, order_no, status, create_time)
    values(63, 61, '联系人', 'Contact', ' fa-file-code-o', 'wb-site', 'http://book.test.com/contact/index.html', 2, 1, now());
insert into lg_module (id, parent_id, name, english_name, icon, code, url, order_no, status, create_time)
    values(64, 61, '合同', 'Contract', 'fa-bar-chart', 'wb-rep', 'http://book.test.com/contract/index.html', 3, 1, now());
insert into lg_module (id, parent_id, name, english_name, icon, code, url, order_no, status, create_time)
    values(65, 61, '应收账款', 'Debt', 'fa-bar-chart', 'wb-rep', 'http://book.test.com/debt/index.html', 4, 1, now());

/**
insert into lg_module (id, parent_id, name, english_name, icon, code, url, order_no, status, create_time)
    values(31, 21, '追踪码', 'dm Template', 'fa-star', 'dm-mt', 'http://tracer.test.com/template/index.html', 1, now());
insert into lg_module (id, parent_id, name, english_name, icon, code, url, order_no, status, create_time)
    values(32, 21, '站点事件', 'Exclude Address', 'fa-hand-stop-o', 'dm-ea', 'http://tracer.test.com/exclude/index.html', 1, now());
insert into lg_module (id, parent_id, name, english_name, icon, code, url, order_no, status, create_time)
    values(33, 21, '追踪日志', 'Config', 'fa-gear', 'dm-cf', 'http://tracer.test.com/config/index.html', 1, now());
insert into lg_module (id, parent_id, name, english_name, icon, code, url, order_no, status, create_time)
    values(34, 21, '广告渠道', 'dm Template', 'fa-star', 'dm-mt', 'http://tracer.test.com/template/index.html', 1, now());
insert into lg_module (id, parent_id, name, english_name, icon, code, url, order_no, status, create_time)
    values(35, 21, '落地页', 'Exclude Address', 'fa-hand-stop-o', 'dm-ea', 'http://tracer.test.com/exclude/index.html', 1, now());
insert into lg_module (id, parent_id, name, english_name, icon, code, url, order_no, status, create_time)
    values(36, 21, '落地页推广', 'Config', 'fa-gear', 'dm-cf', 'http://tracer.test.com/config/index.html', 1, now());
**/
