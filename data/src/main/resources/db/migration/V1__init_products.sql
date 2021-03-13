create table categories (
    id bigserial primary key,
    name varchar(255)
);
insert into categories (name) values
('Foodstuffs'),
('Alcohol'),
('Сomputers_equipments'),
('Household_products'),
('Phones'),
('School_supplies');

create table products (
    id bigserial primary key,
    title varchar(255),
    price int,
    category_id bigint,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp,
    FOREIGN KEY (category_id) REFERENCES categories (id)
);
insert into products (title, price, category_id) values
('Meat', 80, 1),
('Apple', 50, 1),
('Orange', 60, 1),
('Chocolate', 100, 1),
('Mineral water', 35, 1),
('Bread', 35, 1),
('Parsley', 70, 1),
('Kefir', 70, 1),
('Monitor', 23000, 3),
('Computer', 53000, 3),
('Mouse', 2000, 3),
('Keyboard', 3000, 3),
('Table', 6000, 4),
('Phone', 30000, 5),
('Window', 5500, 3),
('Pen', 300, 6),
('Scissors', 400, 6),
('Tea', 90, 1),
('Milk', 60, 1),
('Jack daniels', 900, 2);

