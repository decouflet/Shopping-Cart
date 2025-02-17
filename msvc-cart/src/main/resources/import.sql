INSERT INTO cart (id, user_id, created_at) VALUES (1, 1, '2025-02-15 10:00:00');
INSERT INTO cart (id, user_id, created_at) VALUES (2, 2, '2025-02-15 11:00:00');
INSERT INTO cart (id, user_id, created_at) VALUES (3, 3, '2025-02-15 12:00:00');


INSERT INTO cart_product (cart_id, product_id, quantity) VALUES (1, 1, 2);
INSERT INTO cart_product (cart_id, product_id, quantity) VALUES (1, 2, 1);
INSERT INTO cart_product (cart_id, product_id, quantity) VALUES (1, 3, 1);

INSERT INTO cart_product (cart_id, product_id, quantity) VALUES (2, 4, 1);
INSERT INTO cart_product (cart_id, product_id, quantity) VALUES (2, 5, 2);

INSERT INTO cart_product (cart_id, product_id, quantity) VALUES (3, 1, 1);
INSERT INTO cart_product (cart_id, product_id, quantity) VALUES (3, 3, 2);
INSERT INTO cart_product (cart_id, product_id, quantity) VALUES (3, 5, 1);