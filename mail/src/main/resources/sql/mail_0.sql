/*==============================================================
 Table: 邮件服务商
================================================================*/
create table ml_mail_provider
(
   id                   tinyint not null,
   name                 varchar(32) not null,
   note                 varchar(128),
   status               tinyint,
   primary key (id)
)
ENGINE = InnoDB
DEFAULT CHARSET = utf8;

/*==============================================================
 Table: 邮件服务商配置
================================================================*/
create table ml_provider_conf
(
   id                   bigint not null,
   tenant_id            bigint,
   provider_id          tinyint,
   provider_name        varchar(64),
   provider_passwd      varchar(64),
   sender_name          varchar(64),
   sender_address       varchar(64),
   reply_address        varchar(64),
   creator              bigint,
   creator_name         varchar(64),
   create_time          datetime,
   editor               bigint,
   editor_name          varchar(64),
   edit_time            datetime,
   primary key (id)
)
ENGINE = InnoDB
DEFAULT CHARSET = utf8;

/*==============================================================
 Table: 数据源
================================================================*/
create table ml_data_source
(
   id                   bigint not null,
   tenant_id            bigint not null,
   name                 varchar(64) not null,
   ds_type              tinyint comment '0 本地数据源,  1 外部数据源',
   fields               varchar(512),
   deleted              tinyint,
   creator              bigint,
   creator_name         char(10),
   create_time          datetime,
   editor               bigint,
   editor_name          char(10),
   edit_time            datetime,
   primary key (id)
)
ENGINE = InnoDB
DEFAULT CHARSET = utf8;

/*==============================================================
 Table: 发件人
================================================================*/
create table ml_sender
(
   id                   bigint not null,
   tenant_id            bigint not null,
   sender_name          varchar(64),
   sender_address       varchar(64),
   reply_address        varchar(64),
   creator              bigint,
   creator_name         varchar(64),
   create_time          datetime,
   editor               bigint,
   editor_name          varchar(64),
   edit_time            datetime,
   primary key (id)
)
ENGINE = InnoDB
DEFAULT CHARSET = utf8;

/*==============================================================
 Table: 黑名单
================================================================*/
create table ml_black_list
(
   id                   bigint not null,
   tenant_id            bigint not null,
   provider_id          tinyint comment '0 手动加入黑名单, 其他值为服务商的黑名单',
   recipient            varchar(256) not null,
   reason               varchar(256),
   creator              bigint,
   creator_name         varchar(64),
   create_time          datetime,
   primary key (id)
)
ENGINE = InnoDB
DEFAULT CHARSET = utf8;

/*==============================================================
 Table: 邮件模板
================================================================*/
create table ml_template
(
   id                   bigint not null,
   tenant_id            bigint not null,
   provider_id          tinyint not null,
   datasource_id        bigint,
   name                 varchar(128) not null,
   mail_field           varchar(64),
   replace_fields       varchar(256),
   sender               varchar(64),
   sender_name          varchar(64),
   reply_address        varchar(64),
   subject              varchar(128),
   content              longtext,
   status               tinyint comment '0关闭的模板, 1有效的模板',
   unsubscribe          tinyint,
   unsubscribe_language char(5),
   creator              bigint,
   creator_name         varchar(64),
   create_time          datetime,
   editor               bigint,
   editor_name          varchar(64),
   edit_time            datetime,
   primary key (id)
)
ENGINE = InnoDB
DEFAULT CHARSET = utf8;

/*==============================================================
 Table: 邮包
================================================================*/
create table ml_mail_package
(
   id                   bigint,
   tenant_id            bigint,
   template_id          bigint,
   provider_id          tinyint,
   sender               varchar(64),
   sender_name          varchar(64),
   reply_address        varchar(64),
   send_to              varchar(64),
   subject              varchar(160),
   content              longtext,
   has_attachment       tinyint,
   scheduled            tinyint,
   scheduled_time       datetime,
   status               tinyint,
   campaign_id          varchar(40),
   mailing_id           varchar(40),
   label_id             int,
   unsubscribe          tinyint,
   unsubscribe_language varchar(5),
   unsubscribe_count    int,
   total                int,
   request_count        int,
   deliver_count        int,
   bounce_count         int,
   soft_bounce_count    int,
   invalid_address_count int,
   spam_count           int,
   repeat_address_count int,
   open_count           int,
   open_unique_count    int,
   click_count          int,
   click_unique_count   int,
   provider_exclude_count int,
   creator              bigint,
   creator_name         varchar(64),
   create_time          datetime
)
ENGINE = InnoDB
DEFAULT CHARSET = utf8;

/*==============================================================
 Table: 邮件
================================================================*/
create table ml_mail_0
(
   id                   bigint not null,
   tenant_id            bigint,
   package_id           bigint,
   provider_id          tinyint,
   sharding_column      tinyint,
   recipient            varchar(64),
   template_name        varchar(128),
   subject              varchar(160),
   mail_type            tinyint,
   status               tinyint,
   event                varchar(20),
   scheduled            tinyint,
   scheduled_time       datetime,
   email_id             varchar(128),
   replace_json         char(10),
   unsubscribe          char(10),
   unsubscribe_time     datetime,
   open_count           smallint,
   last_open_time       datetime,
   last_open_ip         bigint,
   submit_fail_reason   varchar(256),
   create_time          datetime,
   update_time          datetime,
   primary key (id)
)
ENGINE = InnoDB
DEFAULT CHARSET = utf8;

create table ml_mail_1
(
   id                   bigint not null,
   tenant_id            bigint,
   package_id           bigint,
   provider_id          tinyint,
   sharding_column      tinyint,
   recipient            varchar(64),
   template_name        varchar(128),
   subject              varchar(160),
   mail_type            tinyint,
   status               tinyint,
   event                varchar(20),
   scheduled            tinyint,
   scheduled_time       datetime,
   email_id             varchar(128),
   replace_json         char(10),
   unsubscribe          char(10),
   unsubscribe_time     datetime,
   open_count           smallint,
   last_open_time       datetime,
   last_open_ip         bigint,
   submit_fail_reason   varchar(256),
   create_time          datetime,
   update_time          datetime,
   primary key (id)
)
ENGINE = InnoDB
DEFAULT CHARSET = utf8;

create table ml_mail_2
(
   id                   bigint not null,
   tenant_id            bigint,
   package_id           bigint,
   provider_id          tinyint,
   sharding_column      tinyint,
   recipient            varchar(64),
   template_name        varchar(128),
   subject              varchar(160),
   mail_type            tinyint,
   status               tinyint,
   event                varchar(20),
   scheduled            tinyint,
   scheduled_time       datetime,
   email_id             varchar(128),
   replace_json         char(10),
   unsubscribe          char(10),
   unsubscribe_time     datetime,
   open_count           smallint,
   last_open_time       datetime,
   last_open_ip         bigint,
   submit_fail_reason   varchar(256),
   create_time          datetime,
   update_time          datetime,
   primary key (id)
)
ENGINE = InnoDB
DEFAULT CHARSET = utf8;

create table ml_mail_3
(
   id                   bigint not null,
   tenant_id            bigint,
   package_id           bigint,
   provider_id          tinyint,
   sharding_column      tinyint,
   recipient            varchar(64),
   template_name        varchar(128),
   subject              varchar(160),
   mail_type            tinyint,
   status               tinyint,
   event                varchar(20),
   scheduled            tinyint,
   scheduled_time       datetime,
   email_id             varchar(128),
   replace_json         char(10),
   unsubscribe          char(10),
   unsubscribe_time     datetime,
   open_count           smallint,
   last_open_time       datetime,
   last_open_ip         bigint,
   submit_fail_reason   varchar(256),
   create_time          datetime,
   update_time          datetime,
   primary key (id)
)
ENGINE = InnoDB
DEFAULT CHARSET = utf8;

create table ml_mail_4
(
   id                   bigint not null,
   tenant_id            bigint,
   package_id           bigint,
   provider_id          tinyint,
   sharding_column      tinyint,
   recipient            varchar(64),
   template_name        varchar(128),
   subject              varchar(160),
   mail_type            tinyint,
   status               tinyint,
   event                varchar(20),
   scheduled            tinyint,
   scheduled_time       datetime,
   email_id             varchar(128),
   replace_json         char(10),
   unsubscribe          char(10),
   unsubscribe_time     datetime,
   open_count           smallint,
   last_open_time       datetime,
   last_open_ip         bigint,
   submit_fail_reason   varchar(256),
   create_time          datetime,
   update_time          datetime,
   primary key (id)
)
ENGINE = InnoDB
DEFAULT CHARSET = utf8;

create table ml_mail_5
(
   id                   bigint not null,
   tenant_id            bigint,
   package_id           bigint,
   provider_id          tinyint,
   sharding_column      tinyint,
   recipient            varchar(64),
   template_name        varchar(128),
   subject              varchar(160),
   mail_type            tinyint,
   status               tinyint,
   event                varchar(20),
   scheduled            tinyint,
   scheduled_time       datetime,
   email_id             varchar(128),
   replace_json         char(10),
   unsubscribe          char(10),
   unsubscribe_time     datetime,
   open_count           smallint,
   last_open_time       datetime,
   last_open_ip         bigint,
   submit_fail_reason   varchar(256),
   create_time          datetime,
   update_time          datetime,
   primary key (id)
)
ENGINE = InnoDB
DEFAULT CHARSET = utf8;

create table ml_mail_6
(
   id                   bigint not null,
   tenant_id            bigint,
   package_id           bigint,
   provider_id          tinyint,
   sharding_column      tinyint,
   recipient            varchar(64),
   template_name        varchar(128),
   subject              varchar(160),
   mail_type            tinyint,
   status               tinyint,
   event                varchar(20),
   scheduled            tinyint,
   scheduled_time       datetime,
   email_id             varchar(128),
   replace_json         char(10),
   unsubscribe          char(10),
   unsubscribe_time     datetime,
   open_count           smallint,
   last_open_time       datetime,
   last_open_ip         bigint,
   submit_fail_reason   varchar(256),
   create_time          datetime,
   update_time          datetime,
   primary key (id)
)
ENGINE = InnoDB
DEFAULT CHARSET = utf8;

create table ml_mail_7
(
   id                   bigint not null,
   tenant_id            bigint,
   package_id           bigint,
   provider_id          tinyint,
   sharding_column      tinyint,
   recipient            varchar(64),
   template_name        varchar(128),
   subject              varchar(160),
   mail_type            tinyint,
   status               tinyint,
   event                varchar(20),
   scheduled            tinyint,
   scheduled_time       datetime,
   email_id             varchar(128),
   replace_json         char(10),
   unsubscribe          char(10),
   unsubscribe_time     datetime,
   open_count           smallint,
   last_open_time       datetime,
   last_open_ip         bigint,
   submit_fail_reason   varchar(256),
   create_time          datetime,
   update_time          datetime,
   primary key (id)
)
ENGINE = InnoDB
DEFAULT CHARSET = utf8;

create table ml_mail_8
(
   id                   bigint not null,
   tenant_id            bigint,
   package_id           bigint,
   provider_id          tinyint,
   sharding_column      tinyint,
   recipient            varchar(64),
   template_name        varchar(128),
   subject              varchar(160),
   mail_type            tinyint,
   status               tinyint,
   event                varchar(20),
   scheduled            tinyint,
   scheduled_time       datetime,
   email_id             varchar(128),
   replace_json         char(10),
   unsubscribe          char(10),
   unsubscribe_time     datetime,
   open_count           smallint,
   last_open_time       datetime,
   last_open_ip         bigint,
   submit_fail_reason   varchar(256),
   create_time          datetime,
   update_time          datetime,
   primary key (id)
)
ENGINE = InnoDB
DEFAULT CHARSET = utf8;

create table ml_mail_9
(
   id                   bigint not null,
   tenant_id            bigint,
   package_id           bigint,
   provider_id          tinyint,
   sharding_column      tinyint,
   recipient            varchar(64),
   template_name        varchar(128),
   subject              varchar(160),
   mail_type            tinyint,
   status               tinyint,
   event                varchar(20),
   scheduled            tinyint,
   scheduled_time       datetime,
   email_id             varchar(128),
   replace_json         char(10),
   unsubscribe          char(10),
   unsubscribe_time     datetime,
   open_count           smallint,
   last_open_time       datetime,
   last_open_ip         bigint,
   submit_fail_reason   varchar(256),
   create_time          datetime,
   update_time          datetime,
   primary key (id)
)
ENGINE = InnoDB
DEFAULT CHARSET = utf8;

/*==============================================================
 Table: 排除邮件
================================================================*/
create table ml_exclude_mail
(
   id                   bigint not null,
   tenant_id            bigint,
   package_id           bigint,
   recipient            varchar(256) not null,
   reason               varchar(256),
   occur_time           datetime,
   primary key (id)
)
ENGINE = InnoDB
DEFAULT CHARSET = utf8;

/*==============================================================
 Table: 无效邮件
================================================================*/
create table ml_invalid_mail
(
   id                   bigint not null,
   tenant_id            bigint,
   pacage_id            bigint not null,
   recipient            varchar(256) not null,
   reason               varchar(256),
   occur_time           datetime,
   primary key (id)
)
ENGINE = InnoDB
DEFAULT CHARSET = utf8;

/*==============================================================
 Table: 邮件打开数据
================================================================*/
create table ml_mail_open
(
   id                   bigint not null,
   tenant_id            bigint,
   package_id           bigint not null,
   mail_id              bigint not null,
   recipient            varchar(256) not null,
   open_times           tinyint,
   ip                   int,
   occur_time           datetime,
   primary key (id)
)
ENGINE = InnoDB
DEFAULT CHARSET = utf8;

/*==============================================================
 Table: 邮件点击数据
================================================================*/
create table ml_mail_click
(
   id                   bigint not null,
   tenant_id            bigint not null,
   package_id           bigint not null,
   mail_id              bigint not null,
   recipient            varchar(256) not null,
   click_times          tinyint,
   ip                   int,
   occur_time           datetime,
   primary key (id)
)
ENGINE = InnoDB
DEFAULT CHARSET = utf8;

/*==============================================================
 Table: 邮件追踪数据
================================================================*/
create table ml_webhook
(
   id                   bigint not null,
   label_id             int,
   raw_data             text,
   status               tinyint comment '0未处理,1处理成功,2处理失败',
   occur_time           datetime,
   primary key (id)
)
ENGINE = InnoDB
DEFAULT CHARSET = utf8;

