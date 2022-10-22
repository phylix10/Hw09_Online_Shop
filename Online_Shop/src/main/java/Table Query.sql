create database online_shop;

create table users
(
    id            serial primary key,
    username      varchar unique,
    password      text,
    national_code varchar(10) unique,
    role          varchar
);

create table categories
(
    id           serial primary key,
    category     varchar,
    sub_category varchar
);

create table product
(
    id          serial primary key,
    name        varchar,
    category_id int,
    stock       int,
    price       int,
    constraint fk_category_id
        foreign key (category_id)
            references categories (id)
);

create table cart
(
    id            serial primary key,
    user_id       int,
    product_id    int,
    product_count int,
    total_price   int,
    final_approval bool default false,
    constraint fk_user_id
        foreign key (user_id)
            references users (id),
    constraint fk_product_id
        foreign key (product_id)
            references product (id)
);
