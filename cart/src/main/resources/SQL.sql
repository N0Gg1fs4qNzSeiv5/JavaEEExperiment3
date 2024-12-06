create table goods
(
    id    int auto_increment
        primary key,
    name  varchar(128) not null,
    price double       not null,
    img   varchar(64)  not null
);

create table user
(
    id       int auto_increment
        primary key,
    username varchar(64) not null,
    password varchar(16) not null,
    constraint user_pk_2
        unique (username)
);

create table cart
(
    id       int auto_increment
        primary key,
    count    int not null,
    goods_id int not null,
    user_id  int not null,
    constraint cart_goods_id_fk
        foreign key (goods_id) references goods (id),
    constraint cart_user_id_fk
        foreign key (user_id) references user (id)
);

create table `order`
(
    id        int auto_increment
        primary key,
    no        varchar(36)                        not null,
    create_at datetime default CURRENT_TIMESTAMP not null,
    pay       double                             not null,
    address   varchar(4096)                      not null,
    user_id   int                                not null,
    constraint order_user_id_fk
        foreign key (user_id) references user (id)
);

create table goods_order
(
    id       int auto_increment
        primary key,
    goods_id int not null,
    order_id int not null,
    count    int not null,
    constraint goods_order_goods_id_fk
        foreign key (goods_id) references goods (id),
    constraint goods_order_order_id_fk
        foreign key (order_id) references `order` (id)
);

