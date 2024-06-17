CREATE TABLE IF NOT EXISTS report (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    `Tour Code` VARCHAR(255),
    `Amount Tourist` BIGINT,
    `Total Price` DECIMAL(19, 2),
    `created_date` datetime(6) DEFAULT NULL
);