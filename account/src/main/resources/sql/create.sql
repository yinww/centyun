
/*==============================================================*/
/* Table: ac_tenant                                             */
/*==============================================================*/
create table ac_tenant
(
   id                   bigint not null,
   name                 varchar(128) not null,
   code                 varchar(64),
   main_account         varchar(32),
   contact              varchar(64),
   mobile               varchar(32),
   phone                varchar(32),
   email                varchar(64),
   address              varchar(128),
   logo                 varchar(128),
   type                 tinyint comment '0个人, 1企业, 2个体工商户, 3政府, 4媒体, 5其他组织',
   status               tinyint comment '0已注册, 1已审核, 2已认证, 3已冻结, 4已注销',
   note                 varchar(256),
   creator              varchar(32),
   create_time          datetime,
   editor               varchar(32),
   edit_time            datetime,
   access_key           varchar(64),
   primary key (id)
)
ENGINE = InnoDB
DEFAULT CHARSET = utf8;
create unique index idxuk_tenant_name on ac_tenant (name);

/*==============================================================*/
/* Table: ac_account                                            */
/*==============================================================*/
create table ac_account
(
   id                   bigint not null,
   login_name           varchar(32) not null,
   type                 tinyint comment '0子账号, 1主账号',
   password             varchar(108),
   display_name         varchar(128),
   real_name            varchar(128),
   tenant_id            varchar(32),
   mobile               varchar(32),
   phone                varchar(32),
   email                varchar(64),
   head_img             varchar(128),
   gender               tinyint comment '1男, 0女',
   status               tinyint comment '0已注册, 1已审核, 2已认证, 3已冻结, 4已注销',
   grade                tinyint comment '0初级, 1....',
   language             varchar(8),
   creator              varchar(32),
   create_time          datetime,
   editor               varchar(32),
   edit_time            datetime,
   primary key (id)
)
ENGINE = InnoDB
DEFAULT CHARSET = utf8;
create unique index idxuk_account_name on ac_account (login_name);

/*==============================================================*/
/* Table: ac_manager                                            */
/*==============================================================*/
create table ac_manager
(
   id                   int not null,
   login_name           varchar(32) not null,
   password             varchar(108) not null,
   display_name         varchar(32) not null,
   phone                varchar(32),
   email                varchar(64),
   language             varchar(8),
   status               tinyint,
   creator              varchar(32),
   create_time          datetime,
   editor               varchar(32),
   edit_time            datetime,
   password_time        datetime,
   primary key (id)
)
ENGINE = InnoDB
DEFAULT CHARSET = utf8;
create unique index idxuk_manager_name on ac_manager (login_name);

/*==============================================================*/
/* Table: ac_product                                            */
/*==============================================================*/
create table ac_product
(
   id                   bigint not null,
   name                 varchar(64) not null,
   code                 varchar(64),
   version              varchar(32),
   publish_time         datetime,
   product_manager      varchar(64),
   note                 varchar(256),
   status               tinyint comment '1正常，2下线停用，3升级后成了旧版，但仍然可以，4升级成后了旧版，不再可以',
   creator              varchar(32),
   create_time          datetime,
   editor               varchar(32),
   edit_time            datetime,
   primary key (id)
)
ENGINE = InnoDB
DEFAULT CHARSET = utf8;
create unique index idxuk_productr on ac_product (name, code, version);

/*==============================================================*/
/* Table: ac_charge                                             */
/*==============================================================*/
create table ac_charge
(
   id                   bigint not null,
   tenant_id            varchar(32) not null,
   product_id           varchar(32) not null,
   money                decimal(12,2),
   quota                int,
   expired_time         datetime,
   note                 varchar(256),
   status               tinyint comment '1 充值成功 2 取消充值',
   charge_manager       varchar(32),
   charge_time          datetime,
   editor               varchar(32),
   edit_time            datetime,
   primary key (id)
)
ENGINE = InnoDB
DEFAULT CHARSET = utf8;

/*==============================================================*/
/* Table: ac_module                                             */
/*==============================================================*/
create table ac_module
(
   id                   int not null,
   code                 varchar(32),
   name                 varchar(32),
   english_name         varchar(64),
   icon                 varchar(32),
   url                  varchar(128),
   order_no             smallint,
   status               tinyint,
   create_time          datetime,
   primary key (id)
)
ENGINE = InnoDB
DEFAULT CHARSET = utf8;

/*==============================================================*/
/* Table: ac_audit                                              */
/*==============================================================*/
create table ac_audit
(
   id                   bigint not null,
   action               varchar(64),
   module               varchar(32),
   content              text,
   ip                   bigint,
   operator             varchar(32),
   operate_time         datetime,
   primary key (id)
)
ENGINE = InnoDB
DEFAULT CHARSET = utf8;

/*==============================================================*/
/* Table: ac_consume                                            */
/*==============================================================*/
create table ac_consume
(
   id                   bigint not null,
   tenant_id            varchar(32),
   product_id           varchar(32),
   amount               int,
   money                decimal(12,2),
   occur_time           datetime,
   ip                   bigint,
   ip_str               varchar(15),
   primary key (id)
);
