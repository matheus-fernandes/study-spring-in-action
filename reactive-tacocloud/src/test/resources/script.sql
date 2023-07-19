create table Taco (
    id varchar(50) primary key,
    name varchar(50) not null,
    ingredients integer[]
);

insert into Taco (id, name, ingredients) values ('123456789', 'Taco 1', ARRAY[1, 2, 3]);
insert into Taco (id, name, ingredients) values ('321654987', 'Taco 2', ARRAY[1, 2, 3]);
insert into Taco (id, name, ingredients) values ('654789123', 'Taco 3', ARRAY[1, 2, 3]);
insert into Taco (id, name, ingredients) values ('654444455', 'Taco 4', ARRAY[1, 2, 3]);
insert into Taco (id, name, ingredients) values ('333355588', 'Taco 5', ARRAY[1, 2, 3]);
insert into Taco (id, name, ingredients) values ('666444888', 'Taco 6', ARRAY[1, 2, 3]);
insert into Taco (id, name, ingredients) values ('332221115', 'Taco 7', ARRAY[1, 2, 3]);
insert into Taco (id, name, ingredients) values ('789998788', 'Taco 8', ARRAY[1, 2, 3]);
insert into Taco (id, name, ingredients) values ('333215558', 'Taco 9', ARRAY[1, 2, 3]);
insert into Taco (id, name, ingredients) values ('996655554', 'Taco 10', ARRAY[1, 2, 3]);
insert into Taco (id, name, ingredients) values ('666333222', 'Taco 11', ARRAY[1, 2, 3]);
insert into Taco (id, name, ingredients) values ('444887878', 'Taco 12', ARRAY[1, 2, 3]);
insert into Taco (id, name, ingredients) values ('345654822', 'Taco 13', ARRAY[1, 2, 3]);
insert into Taco (id, name, ingredients) values ('198753587', 'Taco 14', ARRAY[1, 2, 3]);

