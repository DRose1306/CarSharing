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

### Class Diagram EmployeeOffice

![PhotoDependencyClasses](https://github.com/DRose1306/CarSharing/blob/master/Diagram.jpg)

### Database Structure Diagram

![PhotoDependencyClasses](https://github.com/DRose1306/CarSharing/blob/master/Database_Diagram.png)

# Database structure

The database includes:

### Table Users (Service users)

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

### Table Trips (Trips table)

### Table Payments (Payments table)

### Table Reservation (Reservations table)

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