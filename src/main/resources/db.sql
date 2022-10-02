#--------------------------------------------------------
--  DDL for schema ESHOP
#--------------------------------------------------------
drop schema if exists eshop2;
create schema if not exists eshop2;

#--------------------------------------------------------
--  DDL for Table CATEGORY
#--------------------------------------------------------
drop table if exists eshop2.categories;
create table if not exists eshop2.categories
(
    id int not null auto_increment,
    name varchar(45) not null,
    image varchar(100) not null,
    primary key(id),
    unique index idx_categories_id_unique(id asc),
    unique index idx_categories_name_unique(name asc)
    );
#--------------------------------------------------------
#--  DDL for Table roles
#--------------------------------------------------------
drop table if exists eshop2.roles;
create table if not exists eshop2.roles
(
    id int,
    name varchar(200) not null,
    primary key(id));


#--------------------------------------------------------
#--  DDL for Table USER
#--------------------------------------------------------
drop table if exists eshop2.user;
create table if not exists eshop2.user
(
    id int not null auto_increment,
    name varchar(50) not null,
    surname varchar(100) not null,
    password varchar(200) not null,
    date_of_birthday date,
    role_id int,
    primary key (id),
    unique index idx_user_id_unique(id asc),
    constraint fk_user_role_id_roles_id
    foreign key(role_id)
    references eshop2.roles(id)
    );

#--------------------------------------------------------
#--  DDL for Table PRODUCTS
#--------------------------------------------------------
drop table if exists eshop2.products;
create table if not exists eshop2.products
(
    id int not null auto_increment,
    name varchar (50) not null,
    description varchar (300) not null,
    price int not null,
    category_id int not null,
    image_name varchar (50) not null,
    primary key(id),
    unique index idx_products_id_unique(id asc),
    constraint FK_products_category_id_categories_id
    foreign key(category_id)
    references eshop2.categories(id)
    on delete cascade
    on update cascade);

#--------------------------------------------------------
#--  DDL for Table ORDER
#--------------------------------------------------------
drop table if exists eshop2.orders;
create table if not exists eshop2.orders
(
    id int not null auto_increment,
    user_id int not null,
    price varchar(45) not null,
    date date not null,
    primary key(id),
    foreign key (user_id)
    references user (id)
    );

#--------------------------------------------------------
#--  DDL for Table ORDER_PRODUCT
#--------------------------------------------------------
drop table if exists eshop2.order_product;
create table if not exists eshop2.order_product
(
    order_id int not null,
    product_id int not null,
    primary key(order_id,product_id),
    constraint FK_ORDERS_PRODUCTS_ORDER_ID_ORDERS_ID
    foreign key(order_id)
    references eshop2.orders(id),
    constraint FK_ORDERS_PRODUCTS_PRODUCT_ID_PRODUCTS_ID
    foreign key(product_id)
    references eshop2.products(id)
    );

#-----------------------------------------------------------
--  DML for Table eshop2.roles
#-----------------------------------------------------------
insert into eshop2.roles (id,name)
values (1,'ROLE_ADMIN');
insert into eshop2.roles (id,name)
values (2,'ROLE_USER');

#---------------------------------------------------------
--  DML for Table eshop2.user
#----------------------------------------------------------
insert into eshop2.user (id,name, surname, password, date_of_birthday,role_id)
values (3,'andrey', 'andrey', '$2a$10$1A9raOkdl2scC3B3rhH6pOCHvffLWAquZMCH4bhhJWlcdbEFoAWJW', '2022-08-30',2);
insert into eshop2.user (id,name, surname, password, date_of_birthday,role_id)
values (4,'admin', 'admin', '$2a$10$prC8Lt9nmM2D7P4DUVfWv.0KWm5Sp5SDUeizg4W8Jl5jp0EhVNV36', '2022-08-12',1);


#--------------------------------------------------------
--  DML for Table eshop2.categories
#--------------------------------------------------------
insert into eshop2.categories (id, name, image)
values (1, 'Mobiles', 'mobile.jpg');
insert into eshop2.categories (id, name, image)
values (2, 'Laptops', 'laptop.jpg');
insert into eshop2.categories (id, name, image)
values (3, 'GPS', 'jps_nav.jpg');
insert into eshop2.categories (id, name, image)
values (4, 'Fridges', 'fridge.jpg');
insert into eshop2.categories (id, name, image)
values (5, 'Cars', 'car.jpg');
insert into eshop2.categories (id, name, image)
values (6, 'Cameras', 'camera.jpg');
insert into eshop2.categories (id, name, image)
values (7, 'Washing machines', 'washer.jpg');
insert into eshop2.categories (id, name, image)
values (8, 'Appliances', 'appliances.jpg');
insert into eshop2.categories (id, name, image)
values (9, 'TV', 'tv.jpg');

#--------------------------------------------------------
#--  DML for Table eshop.products
#--------------------------------------------------------

#--Mobiles
insert into eshop2.products (id, name, description, price, category_id, image_name)
values (1, 'Samsung_A51','Лучший мобильный телефон серии A',121,1,'samsung.jpg');
insert into eshop2.products (id, name, description, price, category_id, image_name)
values (2, 'Iphone_X','В качестве новинки улучшенная камера',123,1,'iphone.jpg');
insert into eshop2.products (id, name, description, price, category_id, image_name)
values (3, 'Xiaomi_Poco','Увеличенная емкость батареи',1412,1,'xiaomi.jpg');

#--Laptops
insert into eshop2.products (id, name, description, price, category_id, image_name)
values (4, 'Asus','Лучший ноутбук серии A',124,2,'asus.jpg');
insert into eshop2.products (id, name, description, price, category_id, image_name)
values (5, 'MacBook','В качестве новинки улучшенная камера',244,2,'macbook.jpg');
insert into eshop2.products (id, name, description, price, category_id, image_name)
values (6, 'Xiaomi','Увеличенная емкость батареи',54,2,'xiaomiLaptop.jpg');

#--GPS
insert into eshop2.products (id, name, description, price, category_id, image_name)
values (7, 'Samsung','Лучший навигатор серии A',43,3,'navitel.jpg');
insert into eshop2.products (id, name, description, price, category_id, image_name)
values (8, 'Panasonic','В качестве новинки улучшенная камера',434,3,'navitel.jpg');
insert into eshop2.products (id, name, description, price, category_id, image_name)
values (9, 'Xiaomi','Увеличенная емкость батареи',22,3,'navitel.jpg');

#--Fridges
insert into eshop2.products (id, name, description, price, category_id, image_name)
values (10, 'Fridge_1','Лучший холодильник серии A',145,4,'fridgeS.jpg');
insert into eshop2.products (id, name, description, price, category_id, image_name)
values (11, 'Fridge_2','В качестве новинки улучшенная морозильная камера',235,4,'fridgeS.jpg');
insert into eshop2.products (id, name, description, price, category_id, image_name)
values (12, 'Fridge_3','Увеличенная емкость батареи',543,4,'fridgeS.jpg');

#--Cars
insert into eshop2.products (id, name, description, price, category_id, image_name)
values (13, 'BMW','Лучший двигатель серии A',245,5,'bmwauto.jpg');
insert into eshop2.products (id, name, description, price, category_id, image_name)
values (14, 'Jaguar','В качестве новинки улучшенная коробка передач',453,5,'jaguarauto.jpg');
insert into eshop2.products (id, name, description, price, category_id, image_name)
values (15, 'Audi','Увеличенная емкость батареи',355,5,'audiauto.jpg');

#--Cameras
insert into eshop2.products (id, name, description, price, category_id, image_name)
values (16, 'Photo_1','Лучшая камера серии A',332,6,'photocanon.jpg');
insert into eshop2.products (id, name, description, price, category_id, image_name)
values (17, 'Photo_2','В качестве новинки улучшенная камера',211,6,'photocanon.jpg');
insert into eshop2.products (id, name, description, price, category_id, image_name)
values (18, 'Photo_3','Увеличенная емкость батареи',265,6,'photocanon.jpg');
insert into eshop2.products (id, name, description, price, category_id, image_name)
values (19, 'Photo_3','Увеличенная емкость батареи',233,6,'photocanon.jpg');
insert into eshop2.products (id, name, description, price, category_id, image_name)
values (20, 'Photo_3','Увеличенная емкость батареи',445,6,'photocanon.jpg');
insert into eshop2.products (id, name, description, price, category_id, image_name)
values (21, 'Photo_3','Увеличенная емкость батареи',1236,6,'photocanon.jpg');
insert into eshop2.products (id, name, description, price, category_id, image_name)
values (22, 'Photo_3','Увеличенная емкость батареи',665,6,'photocanon.jpg');
insert into eshop2.products (id, name, description, price, category_id, image_name)
values (23, 'Photo_3','Увеличенная емкость батареи',554,6,'photocanon.jpg');
insert into eshop2.products (id, name, description, price, category_id, image_name)
values (24, 'Photo_3','Увеличенная емкость батареи',555,6,'photocanon.jpg');
insert into eshop2.products (id, name, description, price, category_id, image_name)
values (25, 'Photo_3','Увеличенная емкость батареи',552,6,'photocanon.jpg');
insert into eshop2.products (id, name, description, price, category_id, image_name)
values (26, 'Photo_3','Увеличенная емкость батареи',443,6,'photocanon.jpg');
insert into eshop2.products (id, name, description, price, category_id, image_name)
values (27, 'Photo_3','Увеличенная емкость батареи',238,6,'photocanon.jpg');
insert into eshop2.products (id, name, description, price, category_id, image_name)
values (28, 'Photo_3','Увеличенная емкость батареи',776,6,'photocanon.jpg');

#--Washing machines
insert into eshop2.products (id, name, description, price, category_id, image_name)
values (29, 'SAMSUNG','Лучшая камера серии A',322,7,'washer_samsung.jpg');
insert into eshop2.products (id, name, description, price, category_id, image_name)
values (30, 'LG','В качестве новинки улучшенная камера',211,7,'washer_samsung.jpg');
insert into eshop2.products (id, name, description, price, category_id, image_name)
values (31, 'PHILIPS','Увеличенный обьем',323,7,'washer_samsung.jpg');
insert into eshop2.products (id, name, description, price, category_id, image_name)
values (32, 'ATLANT','Увеличенный обьем',435,7,'washer_samsung.jpg');
insert into eshop2.products (id, name, description, price, category_id, image_name)
values (33, 'CANDY','Увеличенный обьем',456,7,'washer_samsung.jpg');
insert into eshop2.products (id, name, description, price, category_id, image_name)
values (34, 'INDESIT','Увеличенный обьем',556,7,'washer_samsung.jpg');
insert into eshop2.products (id, name, description, price, category_id, image_name)
values (35, 'BOSCH','Увеличенный обьем',453,7,'washer_samsung.jpg');
insert into eshop2.products (id, name, description, price, category_id, image_name)
values (36, 'CANDY','Увеличенный обьем',235,7,'washer_samsung.jpg');
insert into eshop2.products (id, name, description, price, category_id, image_name)
values (37, 'SAMSUNG','Увеличенный обьем',357,7,'washer_samsung.jpg');
insert into eshop2.products (id, name, description, price, category_id, image_name)
values (38, 'SAMSUNG','Увеличенный обьем',354,7,'washer_samsung.jpg');
insert into eshop2.products (id, name, description, price, category_id, image_name)
values (39, 'SAMSUNG','Увеличенный обьем',445,7,'washer_samsung.jpg');
insert into eshop2.products (id, name, description, price, category_id, image_name)
values (40, 'SAMSUNG','Увеличенный обьем',456,7,'washer_samsung.jpg');
insert into eshop2.products (id, name, description, price, category_id, image_name)
values (41, 'SAMSUNG','Увеличенный обьем',433,7,'washer_samsung.jpg');
insert into eshop2.products (id, name, description, price, category_id, image_name)
values (42, 'SAMSUNG','Увеличенный обьем',1236,7,'washer_samsung.jpg');
insert into eshop2.products (id, name, description, price, category_id, image_name)
values (43, 'SAMSUNG','Увеличенный обьем',334,7,'washer_samsung.jpg');
insert into eshop2.products (id, name, description, price, category_id, image_name)
values (44, 'SAMSUNG','Увеличенный обьем',567,7,'washer_samsung.jpg');
insert into eshop2.products (id, name, description, price, category_id, image_name)
values (45, 'SAMSUNG','Увеличенный обьем',4456,7,'washer_samsung.jpg');
insert into eshop2.products (id, name, description, price, category_id, image_name)
values (46, 'SAMSUNG','Увеличенный обьем',332,7,'washer_samsung.jpg');
insert into eshop2.products (id, name, description, price, category_id, image_name)
values (47, 'SAMSUNG','Увеличенный обьем',776,7,'washer_samsung.jpg');
insert into eshop2.products (id, name, description, price, category_id, image_name)
values (48, 'SAMSUNG','Увеличенный обьем',445,7,'washer_samsung.jpg');

#--Appliances
insert into eshop2.products (id, name, description, price, category_id, image_name)
values (49, 'DEERMA','Лучшая камера серии A',232,8,'cleaner.jpg');
insert into eshop2.products (id, name, description, price, category_id, image_name)
values (50, 'DEERMA','В качестве новинки улучшенная камера',211,8,'cleaner.jpg');
insert into eshop2.products (id, name, description, price, category_id, image_name)
values (51, 'SAMSUNG','Увеличенный обьем',424,8,'cleaner.jpg');
insert into eshop2.products (id, name, description, price, category_id, image_name)
values (52, 'ELECTROLUX','Увеличенный обьем',245,8,'cleaner.jpg');
insert into eshop2.products (id, name, description, price, category_id, image_name)
values (53, 'ELECTROLUX','Увеличенный обьем',353,8,'cleaner.jpg');
insert into eshop2.products (id, name, description, price, category_id, image_name)
values (54, 'BOSCH','Увеличенный обьем',343,8,'cleaner.jpg');
insert into eshop2.products (id, name, description, price, category_id, image_name)
values (55, 'BOSCH','Увеличенный обьем',353,8,'cleaner.jpg');
insert into eshop2.products (id, name, description, price, category_id, image_name)
values (56, 'SAMSUNG','Увеличенный обьем',235,8,'cleaner.jpg');
insert into eshop2.products (id, name, description, price, category_id, image_name)
values (57, 'BOSCH','Увеличенный обьем',357,8,'cleaner.jpg');
insert into eshop2.products (id, name, description, price, category_id, image_name)
values (58, 'ELECTROLUX','Увеличенный обьем',354,8,'cleaner.jpg');
insert into eshop2.products (id, name, description, price, category_id, image_name)
values (59, 'BOSCH','Увеличенный обьем',435,8,'cleaner.jpg');
insert into eshop2.products (id, name, description, price, category_id, image_name)
values (60, 'SAMSUNG','Увеличенный обьем',556,8,'cleaner.jpg');
insert into eshop2.products (id, name, description, price, category_id, image_name)
values (61, 'SAMSUNG','Увеличенный обьем',789,8,'cleaner.jpg');
insert into eshop2.products (id, name, description, price, category_id, image_name)
values (62, 'BOSCH','Увеличенный обьем',765,8,'cleaner.jpg');
insert into eshop2.products (id, name, description, price, category_id, image_name)
values (63, 'BOSCH','Увеличенный обьем',445,8,'cleaner.jpg');
insert into eshop2.products (id, name, description, price, category_id, image_name)
values (64, 'SAMSUNG','Увеличенный обьем',445,8,'cleaner.jpg');
insert into eshop2.products (id, name, description, price, category_id, image_name)
values (65, 'BOSCH','Увеличенный обьем',1234,8,'cleaner.jpg');
insert into eshop2.products (id, name, description, price, category_id, image_name)
values (66, 'SAMSUNG','Увеличенный обьем',567,8,'cleaner.jpg');
insert into eshop2.products (id, name, description, price, category_id, image_name)
values (67, 'BOSCH','Увеличенный обьем',567,8,'cleaner.jpg');
insert into eshop2.products (id, name, description, price, category_id, image_name)
values (68, 'SAMSUNG','Увеличенный обьем',554,8,'cleaner.jpg');
insert into eshop2.products (id, name, description, price, category_id, image_name)
values (69, 'SAMSUNG','Увеличенный обьем',248,8,'cleaner.jpg');
insert into eshop2.products (id, name, description, price, category_id, image_name)
values (70, 'ELECTROLUX','Увеличенный обьем',248,8,'cleaner.jpg');


#--TV
insert into eshop2.products (id, name, description, price, category_id, image_name)
values (71, 'SAMSUNG','OLED экран',333,9,'tvsamsung.jpg');
insert into eshop2.products (id, name, description, price, category_id, image_name)
values (72, 'LG','OLED экран',353,9,'tvsamsung.jpg');
insert into eshop2.products (id, name, description, price, category_id, image_name)
values (73, 'XIAOMI','OLED экран',232,9,'tvsamsung.jpg');
insert into eshop2.products (id, name, description, price, category_id, image_name)
values (74, 'SAMSUNG','OLED экран',244,9,'tvsamsung.jpg');
insert into eshop2.products (id, name, description, price, category_id, image_name)
values (75, 'ARTEL','OLED экран',452,9,'tvsamsung.jpg');
insert into eshop2.products (id, name, description, price, category_id, image_name)
values (76, 'IFFALCON','OLED экран',225,9,'tvsamsung.jpg');
insert into eshop2.products (id, name, description, price, category_id, image_name)
values (77, 'SKYLINE','OLED экран',456,9,'tvsamsung.jpg');
insert into eshop2.products (id, name, description, price, category_id, image_name)
values (78, 'ARTEL','OLED экран',554,9,'tvsamsung.jpg');
insert into eshop2.products (id, name, description, price, category_id, image_name)
values (79, 'SAMSUNG','OLED экран',224,9,'tvsamsung.jpg');
insert into eshop2.products (id, name, description, price, category_id, image_name)
values (80, 'SAMSUNG','OLED экран',221,9,'tvsamsung.jpg');
insert into eshop2.products (id, name, description, price, category_id, image_name)
values (81, 'SAMSUNG','OLED экран',234,9,'tvsamsung.jpg');
insert into eshop2.products (id, name, description, price, category_id, image_name)
values (82, 'SONY','OLED экран',256,9,'tvsamsung.jpg');
insert into eshop2.products (id, name, description, price, category_id, image_name)
values (83, 'SAMSUNG','OLED экран',456,9,'tvsamsung.jpg');
insert into eshop2.products (id, name, description, price, category_id, image_name)
values (84, 'SAMSUNG','OLED экран',432,9,'tvsamsung.jpg');
insert into eshop2.products (id, name, description, price, category_id, image_name)
values (85, 'SAMSUNG','OLED экран',678,9,'tvsamsung.jpg');
insert into eshop2.products (id, name, description, price, category_id, image_name)
values (86, 'SONY','OLED экран',544,9,'tvsamsung.jpg');
insert into eshop2.products (id, name, description, price, category_id, image_name)
values (87, 'SAMSUNG','OLED экран',789,9,'tvsamsung.jpg');
insert into eshop2.products (id, name, description, price, category_id, image_name)
values (88, 'SONY','OLED экран',655,9,'tvsamsung.jpg');
insert into eshop2.products (id, name, description, price, category_id, image_name)
values (89, 'SAMSUNG','OLED экран',567,9,'tvsamsung.jpg');
insert into eshop2.products (id, name, description, price, category_id, image_name)
values (90, 'XIAOMI','OLED экран',543,9,'tvsamsung.jpg');
insert into eshop2.products (id, name, description, price, category_id, image_name)
values (91, 'SONY','OLED экран',455,9,'tvsamsung.jpg');
insert into eshop2.products (id, name, description, price, category_id, image_name)
values (92, 'XIAOMI','OLED экран',5432,9,'tvsamsung.jpg');
insert into eshop2.products (id, name, description, price, category_id, image_name)
values (93, 'SONY','OLED экран',2123,9,'tvsamsung.jpg');
insert into eshop2.products (id, name, description, price, category_id, image_name)
values (94, 'SONY','OLED экран',445,9,'tvsamsung.jpg');
insert into eshop2.products (id, name, description, price, category_id, image_name)
values (95, 'SAMSUNG','OLED экран',456,9,'tvsamsung.jpg');
insert into eshop2.products (id, name, description, price, category_id, image_name)
values (96, 'SONY','OLED экран',890,9,'tvsamsung.jpg');