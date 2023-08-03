
create table customer (
    customer_id serial primary key,
    first_name varchar not null,
    last_name varchar not null,
    username varchar unique not null,
    password varchar unique not null,
    email varchar unique not null,
    role varchar not null
);
