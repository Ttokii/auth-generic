create table sys_user(
id bigint not null auto_increment comment "用户id",
user_name varchar(64) comment "用户名",
user_password varchar(64) COMMENT "密码",
user_email varchar(64) comment "邮箱",
user_info text comment "用户信息",
head_img blob comment "用户头像",
create_time datetime comment "创建时间",
PRIMARY KEY (id)
);
alter table sys_user comment "用户表";

create table sys_role(
id BIGINT not null auto_increment comment "角色ID",
role_name VARCHAR(64) comment "角色名",
enabled int comment "是否启用",
create_by BIGINT comment "创建人",
create_time datetime comment "创建时间",
PRIMARY KEY (id)
);
alter table sys_role comment "角色表";

create table sys_privilege (
id BIGINT not null auto_increment comment "权限ID",
privilege_name varchar(64) comment "权限名称",
privilege_url varchar(512) comment "权限路径",
PRIMARY KEY (id)
);
alter table sys_privilege comment "权限表";

create table sys_user_role (
user_id BIGINT comment "用户id",
role_id BIGINT comment "角色id"
);
alter table sys_user_role comment "用户角色表";

create table sys_role_privilege (
role_id BIGINT comment "角色id",
privilege_id BIGINT comment "权限id"
);
alter table sys_role_privilege comment "角色权限表";