CREATE TABLE Product (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    creation_datetime TIMESTAMP NOT NULL
);
CREATE TABLE Product_category (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    product_id INT NOT NULL,
    FOREIGN KEY (product_id) REFERENCES Product(id) ON DELETE CASCADE
);

INSERT INTO Product (name, price, creation_datetime) VALUES
('Laptop Dell XPS', 4500.00, '2024-01-15 09:30:00'),
('iPhone 13', 5200.00, '2024-02-01 14:10:00'),
('Casque Sony WH1000', 890.50, '2024-02-10 16:45:00'),
('Clavier Logitech', 180.00, '2024-03-05 11:20:00'),
('Ecran Samsung 27"', 1200.00, '2024-03-18 08:00:00');

INSERT INTO Product_category (name, product_id) VALUES
('Informatique', 1),
('Téléphonie', 2),
('Audio', 3),
('Accessoires', 4),
('Informatique', 5),
('Bureau', 5),
('Mobile', 2);

SELECT p.*, pc.name as category
FROM Product p
JOIN Product_category pc ON p.id = pc.product_id
WHERE p.name ILIKE '%lap%' 
  AND pc.name ILIKE '%info%' 
ORDER BY p.creation_datetime DESC
LIMIT 10 OFFSET 0;

SELECT * FROM Product
WHERE name ILIKE '%phone%'
LIMIT 10 OFFSET 20; 

SELECT p.*, pc.name as category
FROM Product p
JOIN Product_category pc ON p.id = pc.product_id 
WHERE p.price > 100
ORDER BY p.price DESC
LIMIT 5;

SELECT p.*, pc.name as category
FROM Product p
JOIN Product_category pc ON p.id = pc.product_id
WHERE p.name ILIKE '%lap%' 
  AND pc.name ILIKE '%ordi%'  
ORDER BY p.creation_datetime DESC
LIMIT 10 OFFSET 0;
