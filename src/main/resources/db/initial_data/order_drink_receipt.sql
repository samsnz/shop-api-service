/*
 SELECT * FROM order_drink_receipt
 */
INSERT
    IGNORE INTO `order_drink_receipt` (
        `id`,
        `drink_code`,
        `drink_id`,
        `drink_name`,
        `drink_quantity`,
        `drink_unit_price`,
        `receipt_id`
    )
VALUES
    (1, 'M01', 4, 'Vanilla Milkshake', 7, 3200, 1);

INSERT
    IGNORE INTO `order_drink_receipt` (
        `id`,
        `drink_code`,
        `drink_id`,
        `drink_name`,
        `drink_quantity`,
        `drink_unit_price`,
        `receipt_id`
    )
VALUES
    (2, 'T01', 8, 'Chocolate Tea', 46, 2000, 1);

INSERT
    IGNORE INTO `order_drink_receipt` (
        `id`,
        `drink_code`,
        `drink_id`,
        `drink_name`,
        `drink_quantity`,
        `drink_unit_price`,
        `receipt_id`
    )
VALUES
    (3, 'M01', 4, 'Vanilla Milkshake', 3, 3200, 2);

INSERT
    IGNORE INTO `order_drink_receipt` (
        `id`,
        `drink_code`,
        `drink_id`,
        `drink_name`,
        `drink_quantity`,
        `drink_unit_price`,
        `receipt_id`
    )
VALUES
    (4, 'T01', 8, 'Chocolate Tea', 5, 2000, 2);
