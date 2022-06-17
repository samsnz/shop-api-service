CREATE TABLE cargo (
    id BIGINT NOT NULL AUTO_INCREMENT,
    code VARCHAR(255),
    details VARCHAR(255),
    latitude DOUBLE PRECISION,
    longitude DOUBLE PRECISION,
    name VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
) ENGINE = INNODB;

CREATE TABLE clients (
    id BIGINT NOT NULL AUTO_INCREMENT,
    details VARCHAR(255),
    latitude DOUBLE PRECISION,
    longitude DOUBLE PRECISION,
    name VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
) ENGINE = INNODB;

CREATE TABLE drinks (
    id BIGINT NOT NULL AUTO_INCREMENT,
    code VARCHAR(255),
    name VARCHAR(255) NOT NULL,
    quantity INTEGER NOT NULL,
    unit_price DOUBLE PRECISION NOT NULL,
    PRIMARY KEY (id)
) ENGINE = INNODB;

CREATE TABLE order_drink (
    id BIGINT NOT NULL AUTO_INCREMENT,
    quantity_ordered INTEGER NOT NULL,
    drink_code VARCHAR(255) NOT NULL,
    order_id BIGINT NOT NULL,
    PRIMARY KEY (id)
) ENGINE = INNODB;

CREATE TABLE order_drink_receipt (
    id BIGINT NOT NULL AUTO_INCREMENT,
    drink_code VARCHAR(255) NOT NULL,
    drink_id BIGINT NOT NULL,
    drink_name VARCHAR(255) NOT NULL,
    drink_quantity INTEGER NOT NULL,
    drink_unit_price DOUBLE PRECISION NOT NULL,
    receipt_id BIGINT NOT NULL,
    PRIMARY KEY (id)
) ENGINE = INNODB;

CREATE TABLE orders (
    id BIGINT NOT NULL AUTO_INCREMENT,
    date DATETIME(6),
    details VARCHAR(255),
    status VARCHAR(255),
    cargo_code VARCHAR(255) NOT NULL,
    client_id BIGINT,
    PRIMARY KEY (id)
) ENGINE = INNODB;

CREATE TABLE receipts (
    id BIGINT NOT NULL AUTO_INCREMENT,
    cargo_code VARCHAR(255) NOT NULL,
    cargo_details VARCHAR(255),
    cargo_id BIGINT NOT NULL,
    cargo_latitude DOUBLE PRECISION,
    cargo_longitude DOUBLE PRECISION,
    cargo_name VARCHAR(255) NOT NULL,
    client_details VARCHAR(255),
    client_id BIGINT NOT NULL,
    client_latitude DOUBLE PRECISION,
    client_longitude DOUBLE PRECISION,
    order_date DATETIME(6),
    order_details VARCHAR(255),
    order_id BIGINT NOT NULL,
    PRIMARY KEY (id)
) ENGINE = INNODB;

ALTER TABLE
    cargo
ADD
    CONSTRAINT UK_oi4gc48b075q7e3lssbbm79im UNIQUE (code);

ALTER TABLE
    drinks
ADD
    CONSTRAINT UK_adsdkdrispsmfojljxqj8r4sn UNIQUE (code);

ALTER TABLE
    order_drink
ADD
    CONSTRAINT unique_order_and_drink UNIQUE (order_id, drink_code);

ALTER TABLE
    order_drink
ADD
    CONSTRAINT FKc88c041vfs1w9pakljn6yl0vv FOREIGN KEY (drink_code) REFERENCES drinks (code);

ALTER TABLE
    order_drink
ADD
    CONSTRAINT FKb43pejen3hjb6cyuljbxmlvu5 FOREIGN KEY (order_id) REFERENCES orders (id);

ALTER TABLE
    order_drink_receipt
ADD
    CONSTRAINT FKiav8c33t7o60443my7hleqs8y FOREIGN KEY (receipt_id) REFERENCES receipts (id);

ALTER TABLE
    orders
ADD
    CONSTRAINT FKegxmy4vo2qik0yh9xq0wqb9yi FOREIGN KEY (cargo_code) REFERENCES cargo (code);

ALTER TABLE
    orders
ADD
    CONSTRAINT FKm2dep9derpoaehshbkkatam3v FOREIGN KEY (client_id) REFERENCES clients (id);
