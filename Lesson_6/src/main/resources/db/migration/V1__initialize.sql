drop table if exists categories cascade;
create table categories (id bigserial, title varchar(255), primary key(id));
insert into categories
(title) values
('FOOD'), ('DEVICES');

drop table if exists items cascade;
create table items (id bigserial, title varchar(255), category_id bigint, description varchar(5000), price int, primary key(id), constraint fk_cat_id foreign key (category_id) references categories (id));
insert into items
(title, category_id, description, price) values
('Milk', 1, 'Fresh Milk', 80),
('Bread', 1, 'Fresh Bread', 30),
('NoteBook ASUS X1000', 2, 'Model: ASUS X1000, CPU: Xeon N700, RAM: 128 Gb, SSD: 1Tb', 25000);
