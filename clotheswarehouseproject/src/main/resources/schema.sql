-- schema.sql

CREATE TABLE IF NOT EXISTS items (
    id BIGINT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    brand VARCHAR(255) NOT NULL,
    year_of_creation INT,
    price DECIMAL(10, 2),
    created_at DATE,
    quantity INT
);
