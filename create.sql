
    create table cargo (
       id bigint not null auto_increment,
        code varchar(255),
        details varchar(255),
        latitude double precision,
        longitude double precision,
        name varchar(255) not null,
        primary key (id)
    ) engine=InnoDB;

    create table clients (
       id bigint not null auto_increment,
        details varchar(255),
        latitude double precision,
        longitude double precision,
        name varchar(255) not null,
        primary key (id)
    ) engine=InnoDB;

    create table drinks (
       id bigint not null auto_increment,
        code varchar(255),
        name varchar(255) not null,
        quantity integer not null,
        unit_price double precision not null,
        primary key (id)
    ) engine=InnoDB;

    create table order_drink (
       id bigint not null auto_increment,
        quantity_ordered integer not null,
        drink_code varchar(255) not null,
        order_id bigint not null,
        primary key (id)
    ) engine=InnoDB;

    create table order_drink_receipt (
       id bigint not null auto_increment,
        drink_code varchar(255) not null,
        drink_id bigint not null,
        drink_name varchar(255) not null,
        drink_quantity integer not null,
        drink_unit_price double precision not null,
        receipt_id bigint not null,
        primary key (id)
    ) engine=InnoDB;

    create table orders (
       id bigint not null auto_increment,
        date datetime(6),
        details varchar(255),
        status varchar(255),
        cargo_code varchar(255) not null,
        client_id bigint,
        primary key (id)
    ) engine=InnoDB;

    create table receipts (
       id bigint not null auto_increment,
        cargo_code varchar(255) not null,
        cargo_details varchar(255),
        cargo_id bigint not null,
        cargo_latitude double precision,
        cargo_longitude double precision,
        cargo_name varchar(255) not null,
        client_details varchar(255),
        client_id bigint not null,
        client_latitude double precision,
        client_longitude double precision,
        order_date datetime(6),
        order_details varchar(255),
        order_id bigint not null,
        primary key (id)
    ) engine=InnoDB;

    alter table cargo 
       add constraint UK_oi4gc48b075q7e3lssbbm79im unique (code);

    alter table drinks 
       add constraint UK_adsdkdrispsmfojljxqj8r4sn unique (code);

    alter table order_drink 
       add constraint unique_order_and_drink unique (order_id, drink_code);

    alter table order_drink 
       add constraint FKc88c041vfs1w9pakljn6yl0vv 
       foreign key (drink_code) 
       references drinks (code);

    alter table order_drink 
       add constraint FKb43pejen3hjb6cyuljbxmlvu5 
       foreign key (order_id) 
       references orders (id);

    alter table order_drink_receipt 
       add constraint FKiav8c33t7o60443my7hleqs8y 
       foreign key (receipt_id) 
       references receipts (id);

    alter table orders 
       add constraint FKegxmy4vo2qik0yh9xq0wqb9yi 
       foreign key (cargo_code) 
       references cargo (code);

    alter table orders 
       add constraint FKm2dep9derpoaehshbkkatam3v 
       foreign key (client_id) 
       references clients (id);
