create table Taco (
    id serial primary key,
    name varchar(50) not null,
    ingredients integer[]
);

create table Ingredient (
    id serial primary key,
    slug varchar(4) not null,
    name varchar(25) not null,
    type varchar(10) not null
);

create table Orders (
    id serial primary key,
    delivery_name varchar(50) not null,
    delivery_street varchar(50) not null,
    delivery_city varchar(50) not null,
    delivery_state varchar(2) not null,
    delivery_zip varchar(10) not null,
    cc_number varchar(16) not null,
    cc_expiration varchar(5) not null,
    cc_cvv varchar(3) not null,
    tacos integer[]
);

insert into Taco (id, name, ingredients) values (123456789, 'Taco 1', ARRAY[1, 2, 3]);
insert into Taco (id, name, ingredients) values (321654987, 'Taco 2', ARRAY[1, 2, 3]);
insert into Taco (id, name, ingredients) values (654789123, 'Taco 3', ARRAY[1, 2, 3]);
insert into Taco (id, name, ingredients) values (654444455, 'Taco 4', ARRAY[1, 2, 3]);
insert into Taco (id, name, ingredients) values (333355588, 'Taco 5', ARRAY[1, 2, 3]);
insert into Taco (id, name, ingredients) values (666444888, 'Taco 6', ARRAY[1, 2, 3]);
insert into Taco (id, name, ingredients) values (332221115, 'Taco 7', ARRAY[1, 2, 3]);
insert into Taco (id, name, ingredients) values (789998788, 'Taco 8', ARRAY[1, 2, 3]);
insert into Taco (id, name, ingredients) values (333215558, 'Taco 9', ARRAY[1, 2, 3]);
insert into Taco (id, name, ingredients) values (996655554, 'Taco 10', ARRAY[1, 2, 3]);
insert into Taco (id, name, ingredients) values (666333222, 'Taco 11', ARRAY[1, 2, 3]);
insert into Taco (id, name, ingredients) values (444887878, 'Taco 12', ARRAY[1, 2, 3]);
insert into Taco (id, name, ingredients) values (345654822, 'Taco 13', ARRAY[1, 2, 3]);
insert into Taco (id, name, ingredients) values (198753587, 'Taco 14', ARRAY[1, 2, 3]);

