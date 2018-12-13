create database ct_login;
use ct_login;

create table lg_module
(
   id                   bigint not null,
   parent_id            bigint,
   name                 varchar(64) not null,
   english_name         varchar(128),
   icon                 varchar(16),
   code                 varchar(32),
   url                  varchar(128),
   order_no             tinyint,
   status               tinyint,
   create_time          datetime,
   primary key (id)
)
ENGINE = InnoDB
DEFAULT CHARSET = utf8;

