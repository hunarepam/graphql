create table order_state(
id INTEGER PRIMARY KEY AUTO_INCREMENT,
name varchar(50)
);

CREATE TABLE orders(
id INTEGER PRIMARY KEY AUTO_INCREMENT,
name varchar(25),
id_state int not null,
placed_at datetime,
CONSTRAINT FK_ORDERS_ORDER_STATE foreign key (id_state) references order_state
);

create table product_category(
id INTEGER PRIMARY KEY AUTO_INCREMENT,
name varchar(50)
);

create table product(
id INTEGER PRIMARY KEY AUTO_INCREMENT,
id_category int,
name varchar(50),
price float,
CONSTRAINT FK_product_product_category foreign key (id_category) references product_category
);

CREATE TABLE products_orders(
id INTEGER PRIMARY KEY AUTO_INCREMENT,
id_order int,
id_product int,
CONSTRAINT FK_product_orders foreign key (id_order) references orders,
CONSTRAINT FK_orders_product foreign key (id_product) references product
);