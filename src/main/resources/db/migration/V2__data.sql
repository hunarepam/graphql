insert into order_state(name) values('placed'), ('processed'), ('delivered');

insert into product_category(name) values('home'), ('sports'), ('food'), ('kids');

insert into product(id_category, name, price) values
(1, 'chair', 17.0),
(1, 'table', 35.6),
(2, 'ball', 7.5),
(2, 'sneakers', 14.2),
(3, 'potatoes', 3.0),
(3, 'burger', 4.0),
(4, 'dipers', 6.5);

insert into orders(name, id_state, placed_at) values
('Tadevush order', 3, '2021-09-17 18:47:52.69'),
('Kalinouski order', 3, '2021-11-10 18:27:52.69');

insert into products_orders(id_order, id_product) values
(1, 3),
(1, 2),
(1, 1),
(2, 2),
(2, 4),
(2, 6),
(2, 5);
