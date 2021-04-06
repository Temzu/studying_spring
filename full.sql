BEGIN;

DROP TABLE IF EXISTS products CASCADE;
CREATE TABLE products (id bigserial PRIMARY KEY, title VARCHAR(255), cost int);
INSERT INTO products (title, cost) VALUES
('box', 10),
('sphere', 20),
('maul', 100),
('door', 50),
('camera', 500);

COMMIT;