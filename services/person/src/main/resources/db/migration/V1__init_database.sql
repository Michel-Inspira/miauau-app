CREATE TABLE IF NOT EXISTS address (
    id UUID PRIMARY KEY NOT NULL,
    zip_code VARCHAR(20),
    street VARCHAR(255),
    number VARCHAR(50),
    complement VARCHAR(255),
    neighborhood VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS person (
    id UUID PRIMARY KEY NOT NULL,
    name VARCHAR(255) NOT NULL,
    is_volunteer BOOLEAN,
    role VARCHAR(255),
    email VARCHAR(255) NOT NULL,
    phone VARCHAR(50),
    address_id UUID,
    CONSTRAINT fk_address
        FOREIGN KEY(address_id)
        REFERENCES address(id)
);

