create table user_cantact_info(
    user_id int unsigned not null,
    phone_no varchar(32) comment '手机号码',
    email varchar(128) comment '邮箱',
    qq varchar(32) comment 'qq号',
    primary key(user_id)
)engine=innodb default charset=utf8;