

-- Creación de la tabla 'brands'
CREATE TABLE BRANDS (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,     -- Identificador único para cada registros de marca
    name VARCHAR(10) NOT NULL                 -- Corresponde el nombre de la marca de grupo Inditex
);

-- Creación de la tabla 'products'
CREATE TABLE PRODUCTS (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,     -- Indentificador único para cada registro de producto
    name VARCHAR(255) NOT NULL				  -- Corresponde al nombre del producto
);

-- Creación de la tabla 'PRICES'
CREATE TABLE PRICES (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,     -- Identificador único para cada registro de precio
    brandId BIGINT NOT NULL,                 -- Identificador único de la marca (por ejemplo, ZARA)
    startDate TIMESTAMP NOT NULL,            -- Fecha de inicio de la validez del precio
    endDate TIMESTAMP NOT NULL,              -- Fecha de finalización de la validez del precio
    priceList INT NOT NULL,                  -- Identificador de la tarifa aplicada
    productId BIGINT NOT NULL,               -- Identificador único del producto
    priority INT NOT NULL,                    -- Prioridad para resolver conflictos de tarifas
    price DECIMAL(10, 2) NOT NULL,            -- Precio del producto
    curr VARCHAR(3) NOT NULL                  -- Moneda en formato ISO (ej: EUR, USD)
);