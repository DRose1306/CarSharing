DROP TABLE IF EXISTS user_info_role;
DROP TABLE IF EXISTS role_authority;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS authorities;
DROP TABLE IF EXISTS trips;
DROP TABLE IF EXISTS payments;
DROP TABLE IF EXISTS reservations;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS user_info;
DROP TABLE IF EXISTS addresses;
DROP TABLE IF EXISTS cars;





CREATE TABLE IF NOT EXISTS roles (
    role_id BINARY(16) PRIMARY KEY,
    role_name ENUM('ADMIN', 'MANAGER', 'USER', 'GUEST') DEFAULT 'GUEST'
);

CREATE TABLE IF NOT EXISTS authorities (
    auth_id BINARY(16) PRIMARY KEY,
    authority ENUM('CREATE', 'READ', 'UPDATE', 'DELETE')
);

CREATE TABLE IF NOT EXISTS addresses (
    address_id BINARY(16) PRIMARY KEY,
    street VARCHAR(60) NOT NULL,
    house_number VARCHAR(10) NOT NULL,
    city VARCHAR(60) NOT NULL,
    zip_code VARCHAR(10) NOT NULL,
    country VARCHAR(30) NOT NULL
);

CREATE TABLE IF NOT EXISTS user_info (
    user_info_id BINARY(16) PRIMARY KEY,
    date_of_birth DATE,
    phone_number VARCHAR(20) UNIQUE,
    email VARCHAR(60) UNIQUE,
    user_login VARCHAR(60) NOT NULL,
    user_password VARCHAR(128) NOT NULL,
    driver_license ENUM('A', 'B', 'C', 'D', 'E'),
    driver_licence_id VARCHAR(30),
    user_id binary(16)  UNIQUE,
    address_id binary(16) NOT NULL,
    FOREIGN KEY (address_id)
        REFERENCES addresses(address_id)
);


CREATE TABLE IF NOT EXISTS cars (
    car_id BINARY(16) PRIMARY KEY,
    year_of_release VARCHAR(10) NOT NULL,
    license_plate VARCHAR(20) NOT NULL,
    car_status ENUM('AVAILABLE', 'RESERVED', 'IN_USE', 'FAULTY', 'UNDER_REPAIR', 'RETURNED', 'BLOCKED', 'UNDER_MAINTENANCE'),
    car_brand ENUM('AUDI', 'CITROEN', 'FORD', 'HONDA', 'HYUNDAI', 'KIA', 'LEXUS', 'MAZDA', 'MERCEDES_BENZ', 'SKODA', 'TESLA', 'TOYOTA', 'VOLKSWAGEN', 'VOLVO')
);

CREATE TABLE IF NOT EXISTS users (
    user_id BINARY(16) PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    user_info_id BINARY(16) NOT NULL,
    FOREIGN KEY (user_info_id)
        REFERENCES user_info (user_info_id)
);


CREATE TABLE IF NOT EXISTS trips (
    trip_id BINARY(16) PRIMARY KEY,
    start_time DATETIME DEFAULT NULL,
    end_time DATETIME DEFAULT NULL,
    distance DOUBLE DEFAULT 0.0,
    cost DECIMAL(10 , 2 ) NOT NULL,
    user_id BINARY(16) NOT NULL,
    car_id BINARY(16) NOT NULL,
    FOREIGN KEY (user_id)
        REFERENCES users (user_id),
    FOREIGN KEY (car_id)
        REFERENCES cars (car_id)
);

CREATE TABLE IF NOT EXISTS payments (
    payment_id BINARY(16) PRIMARY KEY,
    amount DECIMAL(10 , 2 ) NOT NULL,
    payment_date DATETIME DEFAULT NULL,
    payment_method ENUM('VISA', 'MASTERCARD', 'AMERICAN_EXPRESS', 'SEPA_DIRECT_DEBIT', 'PAYPAL', 'APPLE_PAY', 'GOOGLE_PAY'),
    payment_status BOOLEAN,
    user_id BINARY(16) NOT NULL,
    FOREIGN KEY (user_id)
        REFERENCES users (user_id)
);


CREATE TABLE IF NOT EXISTS reservations (
    reservation_id BINARY(16) PRIMARY KEY,
    start_time DATETIME DEFAULT NULL,
    end_time DATETIME DEFAULT NULL,
    car_id BINARY(16) NOT NULL,
    user_id BINARY(16) NOT NULL,
    FOREIGN KEY (car_id)
        REFERENCES cars (car_id),
    FOREIGN KEY (user_id)
        REFERENCES users (user_id)
);


CREATE TABLE IF NOT EXISTS user_info_role (
    user_info_id BINARY(16) NOT NULL,
    role_id BINARY(16) NOT NULL,
    PRIMARY KEY (user_info_id , role_id),
    FOREIGN KEY (user_info_id)
        REFERENCES user_info (user_info_id),
    FOREIGN KEY (role_id)
        REFERENCES roles (role_id)
);

CREATE TABLE IF NOT EXISTS role_authority (
    role_id BINARY(16) NOT NULL,
    auth_id BINARY(16) NOT NULL,
    PRIMARY KEY (role_id , auth_id),
    FOREIGN KEY (role_id)
        REFERENCES roles (role_id),
    FOREIGN KEY (auth_id)
        REFERENCES authorities (auth_id)
);






