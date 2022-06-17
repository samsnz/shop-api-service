/*
 SELECT * FROM receipts
 */
INSERT
    IGNORE INTO `receipts` (
        `id`,
        `cargo_code`,
        `cargo_details`,
        `cargo_id`,
        `cargo_latitude`,
        `cargo_longitude`,
        `cargo_name`,
        `client_details`,
        `client_id`,
        `client_latitude`,
        `client_longitude`,
        `order_date`,
        `order_details`,
        `order_id`
    )
VALUES
    (
        1,
        'GIS01',
        'ULK/KIU Gisozi Cargo',
        2,
        30.056211017428165,
        -1.9186086443349217,
        'ULK/KIU Gisozi Cargo',
        'Camellia KBC',
        2,
        30.09168177318636,
        -1.951905789713007,
        '2022-06-16 09:05:01.996000',
        'My first order',
        3
    );

INSERT
    IGNORE INTO `receipts` (
        `id`,
        `cargo_code`,
        `cargo_details`,
        `cargo_id`,
        `cargo_latitude`,
        `cargo_longitude`,
        `cargo_name`,
        `client_details`,
        `client_id`,
        `client_latitude`,
        `client_longitude`,
        `order_date`,
        `order_details`,
        `order_id`
    )
VALUES
    (
        2,
        'GIS01',
        'ULK/KIU Gisozi Cargo',
        2,
        30.056211017428165,
        -1.9186086443349217,
        'ULK/KIU Gisozi Cargo',
        'Camellia KBC',
        2,
        30.09168177318636,
        -1.951905789713007,
        '2022-06-16 09:05:01.996000',
        'My first order',
        3
    );
