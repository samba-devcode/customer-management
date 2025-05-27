CREATE TABLE customer (
    id UUID PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255),
    annual_spend DECIMAL(12,2),
    last_purchase_date DATE
);