INSERT INTO cart (id, user_id, created_at, pay, cart_type) VALUES (1, 1, '2025-02-15', false, 'VIP');
INSERT INTO cart (id, user_id, created_at, pay, cart_type) VALUES (2, 2, '2025-02-15', false, 'PROMOTIONAL');
INSERT INTO cart (id, user_id, created_at, pay, cart_type) VALUES (3, 3, '2025-02-15', false, 'VIP');


INSERT INTO cart_product (cart_id, product_id, quantity) VALUES (1, 1, 2);
INSERT INTO cart_product (cart_id, product_id, quantity) VALUES (1, 2, 1);
INSERT INTO cart_product (cart_id, product_id, quantity) VALUES (1, 3, 1);
INSERT INTO cart_product (cart_id, product_id, quantity) VALUES (1, 4, 1);

INSERT INTO cart_product (cart_id, product_id, quantity) VALUES (2, 4, 2);
INSERT INTO cart_product (cart_id, product_id, quantity) VALUES (2, 5, 9);

INSERT INTO cart_product (cart_id, product_id, quantity) VALUES (3, 1, 1);
INSERT INTO cart_product (cart_id, product_id, quantity) VALUES (3, 3, 2);
INSERT INTO cart_product (cart_id, product_id, quantity) VALUES (3, 5, 8);