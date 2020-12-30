create table t_base_fund
(
    FId         int auto_increment comment 'ID'
        primary key,
    FNumber     varchar(10) default ''                not null comment '基金代码',
    FName       varchar(32) default ''                not null comment '基金名称',
    FCreateTime timestamp   default CURRENT_TIMESTAMP not null comment '创建时间',
    FUpdateTime timestamp   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    FDeleteTime datetime                              null comment '删除时间',
    FCreateUser varchar(32) default ''                not null comment '创建者ID',
    FUpdateUser varchar(32) default ''                not null comment '更新者ID',
    FDeleteUser varchar(32) default ''                null comment '删除者ID',
    FIsDelete   tinyint     default 0                 not null comment '是否删除（1是；0否）'
)
    comment '基金信息表';

create table t_base_fundpositionentry
(
    FId         int auto_increment comment 'ID'
        primary key,
    FFundId     varchar(32)    default ''                not null comment '基金ID',
    FStockId    varchar(32)    default ''                not null comment '股票ID',
    FRate       decimal(20, 2) default 0.00              not null comment '持仓占比%',
    FCreateTime timestamp      default CURRENT_TIMESTAMP not null comment '创建时间',
    FUpdateTime timestamp      default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    FDeleteTime datetime                                 null comment '删除时间',
    FCreateUser varchar(32)    default ''                not null comment '创建者ID',
    FUpdateUser varchar(32)    default ''                not null comment '更新者ID',
    FDeleteUser varchar(32)    default ''                null comment '删除者ID',
    FIsDelete   tinyint        default 0                 not null comment '是否删除（1是；0否）'
)
    comment '基金持仓明细';

create table t_base_funduser
(
    FId         int auto_increment comment 'ID'
        primary key,
    FName       varchar(32) default ''                not null comment '基金经理姓名',
    FStartTime  datetime                              null comment '任期开始时间',
    FEndTime    datetime                              null comment '任期结束时间',
    FInOffer    tinyint     default 1                 not null comment '是否在任期内',
    FCreateTime timestamp   default CURRENT_TIMESTAMP not null comment '创建时间',
    FUpdateTime timestamp   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    FDeleteTime datetime                              null comment '删除时间',
    FCreateUser varchar(32) default ''                not null comment '创建者ID',
    FUpdateUser varchar(32) default ''                not null comment '更新者ID',
    FDeleteUser varchar(32) default ''                null comment '删除者ID',
    FIsDelete   tinyint     default 0                 not null comment '是否删除（1是；0否）'
)
    comment '基金经理信息表';

create table t_base_industry
(
    FId         int auto_increment comment 'ID'
        primary key,
    FName       varchar(32) default ''                not null comment '行业名称',
    FCreateTime timestamp   default CURRENT_TIMESTAMP not null comment '创建时间',
    FUpdateTime timestamp   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    FDeleteTime datetime                              null comment '删除时间',
    FCreateUser varchar(32) default ''                not null comment '创建者ID',
    FUpdateUser varchar(32) default ''                not null comment '更新者ID',
    FDeleteUser varchar(32) default ''                null comment '删除者ID',
    FIsDelete   tinyint     default 0                 not null comment '是否删除（1是；0否）'
)
    comment '股票行业表';

create table t_base_stock
(
    FId         int auto_increment comment 'ID'
        primary key,
    FName       varchar(32) default ''                not null comment '股票名称',
    FNumber     varchar(32) default ''                not null comment '股票代码',
    FIndustryId varchar(32) default ''                not null comment '行业ID',
    FCreateTime timestamp   default CURRENT_TIMESTAMP not null comment '创建时间',
    FUpdateTime timestamp   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    FDeleteTime datetime                              null comment '删除时间',
    FCreateUser varchar(32) default ''                not null comment '创建者ID',
    FUpdateUser varchar(32) default ''                not null comment '更新者ID',
    FDeleteUser varchar(32) default ''                null comment '删除者ID',
    FIsDelete   tinyint     default 0                 not null comment '是否删除（1是；0否）'
)
    comment '股票信息表';

create table t_base_user
(
    FId         int auto_increment
        primary key,
    FName       varchar(32) default ''                not null comment '姓名',
    FPassword   varchar(32) default ''                not null comment '密码',
    FSex        tinyint     default 0                 not null comment '性别（0：男  1：女  2：未知）',
    FAge        int         default 0                 not null comment '年龄',
    FOuterId    varchar(32) default ''                not null comment '外部ID',
    FCreateTime timestamp   default CURRENT_TIMESTAMP not null comment '创建时间',
    FUpdateTime timestamp   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    FDeleteTime datetime                              null comment '删除时间',
    FCreator    int         default 0                 not null comment '创建者ID',
    FUpdater    int         default 0                 not null comment '更新者ID',
    FDeleter    int                                   null comment '删除者ID',
    FIsDelete   tinyint     default 0                 not null comment '是否删除（1是；0否）'
);

create table t_ebuy_product
(
    FId    int auto_increment
        primary key,
    FName  varchar(32)    default ''   not null comment '商品名称',
    FPrice decimal(20, 2) default 0.00 not null comment '商品价格（单位：分）'
);

