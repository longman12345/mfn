create table user_info (
  user_id bigint unsigned auto_increment not null comment '用户ID',
  username varchar(64) not null comment '用户名',
  password varchar(64) not null comment '密码',
  primary key(user_id),
  unique key(username)
)engine=innodb default charset=utf8;


