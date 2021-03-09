drop database ecommerce_db;
drop user ecommerce_admin;
create user ecommerce_admin with password 'ecommerce_admin';
create database ecommerce_db with owner=ecommerce_admin ENCODING = 'UTF8';
\connect ecommerce_db;
alter default privileges grant all on tables to ecommerce_admin;
alter default privileges grant all on sequences to ecommerce_admin;

create table ec_users(
    id serial primary key not null,
    firstName varchar(255) not null,
    lastName varchar(255) not null,
    email varchar(255) not null,
    password text not null,
    role varchar(255) not null DEFAULT 'user'
);

create table ec_categories(
    id serial primary key not null,
    parentCategoryId integer DEFAULT null,
    name varchar(255) not null,
    description varchar(255) not null
);

create table ec_products(
    id serial primary key not null,
    name varchar(255) not null,
    quantity int not null,
    price decimal(12,2) not null,
    imageUrl text not null,
    categoryId int not null
);


alter table ec_categories add constraint categories_parentcategory_fk
foreign key (parentCategoryId) references ec_categories(id) ON DELETE CASCADE ON UPDATE CASCADE;

alter table ec_products add constraint product_category_fk
foreign key (categoryId) references ec_categories(id) ON DELETE CASCADE ON UPDATE CASCADE;

INSERT INTO ec_categories(name, description) VALUES ('Telefon', 'Telefon Kategorisi');
INSERT INTO ec_categories(name, description) VALUES ('Bilgisayar', 'Bilgisayar Kategorisi');

INSERT INTO ec_categories(parentCategoryId, name, description) VALUES (1, 'Cep Telefonu', 'Cep Telefonu Kategorisi');
INSERT INTO ec_categories(parentCategoryId, name, description) VALUES (1, 'Kapak & Kılıf', 'Kapak & Kılıf Kategorisi');
INSERT INTO ec_categories(parentCategoryId, name, description) VALUES (2, 'Laptop', 'Bilgisayar Kategorisi');
INSERT INTO ec_categories(parentCategoryId, name, description) VALUES (2, 'Gaming Bilgisayar', 'Gaming Bilgisayar Kategorisi');
INSERT INTO ec_categories(parentCategoryId, name, description) VALUES (2, 'Monitör', 'Monitör Kategorisi');



INSERT INTO ec_products(name, quantity, price, imageUrl, categoryId) VALUES ('Samsung Galaxy M51 (Çift SIM) 128GB Siyah (Samsung Türkiye Garantili) SM-M515FZKETUR', 2, 3.499, 'https://cdn.dsmcdn.com//ty12/product/media/images/20200911/14/10400775/85981671/1/1_org.jpg',3);
INSERT INTO ec_products(name, quantity, price, imageUrl, categoryId) VALUES ('Xiaomi Poco X3 NFC 128GB Siyah Cep Telefonu (Xiaomi Türkiye Garantili) X3 128GB', 5, 3.128, 'https://cdn.dsmcdn.com//ty18/product/media/images/20201027/9/19862743/97945402/1/1_org.jpg',3);
INSERT INTO ec_products(name, quantity, price, imageUrl, categoryId) VALUES ('Apple iPhone 11 64GB Beyaz Cep Telefonu (Apple Türkiye Garantili) Aksesuarsız Kutu AP-IPHO11-2020', 5, 7.458, 'https://cdn.dsmcdn.com//ty27/product/media/images/20201126/16/32765474/113335703/1/1_org.jpg',3);

INSERT INTO ec_products(name, quantity, price, imageUrl, categoryId) VALUES ('SUPPO Iphone 7/8 Uyumlu Plus Logolu Lansman Kılıf+kablo Koruyucu 7PKILIFKORUYUCU', 115, 29.50, 'https://cdn.dsmcdn.com//ty65/product/media/images/20210206/14/60473472/138329074/1/1_org.jpg',4);
INSERT INTO ec_products(name, quantity, price, imageUrl, categoryId) VALUES ('LuffyCase Iphone 7/8 Plus Uyumlu Lila Silikon Kılıf MQH12ZMA', 96, 21.50, 'https://cdn.dsmcdn.com//ty42/product/media/images/20201230/11/43530391/124855245/1/1_org.jpg',4);


INSERT INTO ec_products(name, quantity, price, imageUrl, categoryId) VALUES ('Apple Macbook Pro Mvvk2tu/a T.bar I9 2.3ghz 16gb 1tb 8 Core 16" S.g T05397', 5, 29.399, 'https://cdn.dsmcdn.com//ty26/product/media/images/20201125/20/32544467/61865485/1/1_org.jpg',5);
INSERT INTO ec_products(name, quantity, price, imageUrl, categoryId) VALUES ('Apple Macbook Air 13'''' M1 8 Gb 512 Gb Ssd Gümüş - Mgna3tu/a MGNA3TU/A', 7, 13.549, 'https://cdn.dsmcdn.com//ty2/product/media/images/20200416/14/1956717/68834432/1/1_org.jpg',5);

INSERT INTO ec_products(name, quantity, price, imageUrl, categoryId) VALUES ('MSI GF65 Thin 10SDR-638XTR Intel Core i5 10300H 8GB 512GB SSD GTX1660Ti Freedos 15,6" FHD', 17, 10.699, 'https://cdn.dsmcdn.com//ty11/product/media/images/20201014/8/15628154/74129861/1/1_org.jpg',6);
INSERT INTO ec_products(name, quantity, price, imageUrl, categoryId) VALUES ('Monster Tulpar T5 V19.2 I7-10750h, 16gb, 250gb, 6gb Rtx2060, 15'''',144hz Full Hd, Freedos MONSTER TULPAR T5 V19.2', 27, 10.499, 'https://cdn.dsmcdn.com//ty45/product/media/images/20210104/17/45616911/68766164/1/1_org.jpg',6);
INSERT INTO ec_products(name, quantity, price, imageUrl, categoryId) VALUES ('ASUS Rog Strix G17 G712LV-H7065 i7-10750H 16GB 512GB SSD 6GB RTX2060 17.3" FHD 120 Hz', 37, 13.999, 'https://cdn.dsmcdn.com//ty2/product/media/images/20201013/19/15572435/76118043/1/1_org.jpg',6);

