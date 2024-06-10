# CarSharing Service [Backend]

This project is an educational application for a carsharing service, developed as part of a graduation project. The
primary goal of the application is to provide users with the ability to rent cars from the available fleet of vehicles.

Project Concept
The project aims to create a multifunctional web application that allows users to perform the following actions:

- View available vehicles.
- Reserve and rent selected vehicles.
- Manage vehicle data: add, modify, and delete information.

Users and Functionality

- Users: Any user with a valid payment card and a driver's license can rent a vehicle.
- Service Administrators: Users registered in the system with administrator rights can manage the fleet of vehicles,
  making them available for rent.

Project Goal
The primary goal of this educational project is to demonstrate the skills in developing a multifunctional web
application in Java, including the use of modern architectural approaches and interaction with external APIs.

# Installation

git clone https://github.com/DRose1306/CarSharing/tree/main/src/main/java/com/example/carsharing

cd carsharing

### Class Diagram CarSharing

![PhotoDependencyClasses](https://github.com/DRose1306/CarSharing/blob/readme/Diagram.jpg)

### Database Structure Diagram

![PhotoDependencyClasses](https://github.com/DRose1306/CarSharing/blob/readme/Database_Diagram.png)

# Database structure

The database includes:

### Table Users (Service users)

| Column name  | Type        | Description                                |
|--------------|-------------|--------------------------------------------|
| user_id      | binary(16)  | Primary key Unique user ID                 |
| first_name   | varchar(50) | First name of the user                     |
| last_name    | varchar(50) | Last name of the user                      |
| created_at   | timestamp   | Timestamp of user creation                 |
| user_info_id | binary(16)  | FOREIGN KEY (user_info_id) REFERENCES Personal_Info(personal_info_id)                     |

### Table Roles (Service users roles)

| Column name | Type                                                           | Description                   |
|-------------|----------------------------------------------------------------|-------------------------------|
| role_id     | BINARY(16)                                                     | PRIMARY KEY Unique role ID    |
| role_name   | ENUM ('ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_USER', 'ROLE_GUEST') | Role name from enum RolesName |

### Table Authorities (Service authorities)

| Column name | Type                                        | Description                                    |
|-------------|---------------------------------------------|------------------------------------------------|
| auth_id     | BINARY(16)                                  | PRIMARY KEY Unique identifier of the authority |
| authority   | ENUM ('CREATE', 'READ', 'UPDATE', 'DELETE') | Name of authority from enum AuthorityName      |

### Table User Info (Service users personal information)

| Column name       | Type         | Description                                      |
|-------------------|--------------|--------------------------------------------------|
| user_info_id      | BINARY(16)   | PRIMARY KEY Unique identifier of user information |
| date_of_birth     | DATE         | Date of birth of the user                        |
| phone_number      | VARCHAR(20)  | Phone number of the user                         |
| email             | VARCHAR(60)  | Email address of the user                        |
| user_login        | VARCHAR(60)  | Login of the User                                |
| user_password     | VARCHAR(128) | User's password                                  |
| card_number       | VARCHAR(60)  | User's card number                               |
| driver_license    | ENUM         | Driver license class                             |
| driver_licence_id | VARCHAR(30)  | Driver license number                            |
| user_id           | BINARY(16)   | User(user_id), to the User table                 |

### Table Address (Address information)

| Column name  | Type                  | Description                                                                   |
|--------------|-----------------------|-------------------------------------------------------------------------------|
| address_id   | BINARY(16)            | PRIMARY KEY Unique address identifier                                         |
| street       | VARCHAR(60)           | Street                                                                        |
| house_number | VARCHAR(10)           | House number                                                                  |
| city         | VARCHAR(60)           | City                                                                          |
| zip_code     | VARCHAR(10)           | Zip code                                                                      |
| country      | VARCHAR(100)          | Country                                                                       |

### Table Cars (Service cars table)

| Column name     | Type        | Description                          |
|-----------------|-------------|--------------------------------------|
| car_id          | BINARY(16)  | PRIMARY KEY Unique identifier of car |
| year_of_release | VARCHAR(10) | Release date                         |
| license_plate   | VARCHAR(20) | License plate of car                 |
| car_status      | ENUM        | Car status                           |
| car_brand       | ENUM        | Car brand                            |
| created_at      | TIMESTAMP   | Timestamp of car creation            |

### Table Trips (Trips table)

| db name     | db type        | Description                        |
|-------------|----------------|------------------------------------|
| trip_id     | BINARY(16)     | PRIMARY KEY Unique identifier of trip |
| start_time  | DATETIME       | Start of trip                      |
| end_time    | DATETIME       | End of trip                        |
| distance    | DOUBLE         | Distance of trip                   |
| cost        | DECIMAL(10, 2) | Cost of trip                       |
| user_id     | BINARY(16)     | FOREIGN KEY Unique user identifier |
| car_id      | BINARY(16)     | FOREIGN KEY Unique car identifier  |

### Table Payments (Payments table)

| Column name    | Type            | Description                             |
|----------------|-----------------|-----------------------------------------|
| payment_id     | BINARY(16)      | PRIMARY KEY Unique identifier of payment|
| amount         | DECIMAL(10, 2)  | Amount                                  |
| payment_date   | DATETIME        | Payment date                            |
| payment_method | ENUM            | Payment method                          |
| payment_status | BOOLEAN         | Status of payment                       |
| user_id        | BINARY(16)      | Unique user identifier (FOREIGN KEY)    |

### Table Reservation (Reservations table)

| Column name   | db_type     | Description                               |
|---------------|-------------|-------------------------------------------|
| reservation_id| BINARY(16)  | Unique identifier of reservation (PRIMARY KEY) |
| start_time    | DATETIME    | Start of reservation                      |
| end_time      | DATETIME    | End of reservation                        |
| car_id        | BINARY(16)  | Unique car identifier (FOREIGN KEY)       |
| user_id       | BINARY(16)  | Unique user identifier (FOREIGN KEY)      |

### Link Table User_info<->Role

| Column name  | Type       | Description |
|--------------|------------|-------------|
| user_info_id | BINARY(16) |             |
| role_id      | BINARY(16) |             |

### Link Table Role<->Authority

| Column name | Type       | Description |
|-------------|------------|-------------|
| role_id     | BINARY(16) |             |
| auth_id     | BINARY(16) |             |

### Project structure

- `src/main/java/com/example/carsharing` — source
- `src/main/resources` — configuration resources

### Used Technologies:

- Programming Language: Java
Frameworks and Libraries:
- Spring Framework:
- Spring Boot (including spring-boot-starter-web and spring-boot-starter-data-jpa)
- Spring Security (for securing the application, managing authentication and authorization)
- Hibernate (via Spring Data JPA)
- SpringDoc OpenAPI (including springdoc-openapi-starter-webmvc-ui and springdoc-openapi-ui)
- Lombok (for simplifying code)
- MapStruct (for object mapping)
- Swagger (via swagger-annotations)
- jBCrypt (for password hashing)
- Liquibase (for database migrations management)
- JavaFaker (for generating test data)
- Database: MySQL (main database), H2 (for testing)
- Build and Testing Tools:
- Maven
- Jacoco (for code coverage analysis)
- API: RESTful API for client-server interaction

### Validators

- `@UuidFormatChecker` — custom annotation for checking UUID format
- `@PhoneNumberChecker` — custom annotation for checking phone number format
- `@EmailChecker` — custom annotation for checking email format

## Contacts

If you have questions or suggestions, please contact me:

- Name: Urmat Bolotbek Uulu
- Email: urmar9797@gmail.com
- GitHub: [DRose1306](https://github.com/DRose1306)
