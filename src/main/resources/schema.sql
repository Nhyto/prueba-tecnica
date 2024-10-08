CREATE TABLE IF NOT EXISTS BRANDS (
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    NAME VARCHAR(13) NOT NULL
);

CREATE TABLE IF NOT EXISTS PRODUCTS (
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    NAME VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS PRICES (
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    BRAND_ID BIGINT NOT NULL,
    START_DATE TIMESTAMP NOT NULL,
    END_DATE TIMESTAMP NOT NULL,
    PRICE_LIST INT NOT NULL,
    PRODUCT_ID BIGINT NOT NULL,
    PRIORITY INT NOT NULL,
    PRICE DECIMAL(10, 2) NOT NULL,
    CURR VARCHAR(3) NOT NULL,
    
    CONSTRAINT FK_BRAND FOREIGN KEY (BRAND_ID) REFERENCES BRANDS(ID),
    CONSTRAINT FK_PRODUCT FOREIGN KEY (PRODUCT_ID) REFERENCES PRODUCTS(ID)
);
