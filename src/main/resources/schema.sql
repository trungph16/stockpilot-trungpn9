CREATE TABLE products(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    sku VARCHAR(20),
    name VARCHAR(100),
    category VARCHAR(50),
    price DECIMAL(15,2),
    stock_quantity INT
);