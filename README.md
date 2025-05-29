# Poseidon Capital Solutions Tickets

Poseidon Capital Solutions is an application that centralizes several financial tools to facilitate transaction management.

## Technical

- **Spring Boot 3.4.6** - Application Framework
- **Spring Data JPA** - Data Backup
- **Spring Security** - Authentication and Authorization
- **Maven** - Dependency Management
- **Java 17** - Programming Language
- **Thymeleaf** - Dynamic Web Page Generation
- **Bootstrap v.4.3.1** - CSS Framework for Design
- **MySQL** - Database
- **JaCoCo** - Java Code Coverage
- **JUnit** - Code Testing Tool
- **MapStruct** - Easily Converts Java Objects
- **Javadoc** - Automatic Documentation Generation for Java Code

## Features

1. **User Operations**

   - User access and security
   - Creating, updating, and deleting accounts
   - Role-based permissions (ADMIN, USER)

2. **Bid List**

   - Bid management and monitoring
   - Full lifecycle handling: Create, Read, Update, Delete

3. **Curve Points**

   - Managing financial curve points
   - Tracking and analyzing data trends

4. **Ratings**

   - Management of ratings
   - Financial ranking tracking

5. **Transactions**

   - Recording and monitoring transactions
   - Detailed financial data management

6. **Rules**

   - Creating and managing business rules
   - Flexible custom configurations

7. **Trades**
   - Trade execution and tracking
   - Complete trade lifecycle: Create, Read, Update, Delete

## Project Architecture

```plaintext
├───src
│ ├───main
│ │ ├───java
│ │ │ └───com
│ │ │ └───nnk
│ │ │ └───springboot
│ │ │ ├───configuration   # Security configuration
│ │ │ ├───controller      # Receives requests and sends responses
│ │ │ ├───dto             # Data Transfert Objects
│ │ │ ├───mapper          # MapStruct
│ │ │ ├───model           # Financial Entities
│ │ │ ├───repositories    # Spring Data Repositories
│ │ │ └───service         # Business logic
│ │ └───resources
│ │ ├───static            # Frontend (CSS, Bootstrap)
│ │ │ └───css
│ │ └───templates         # Dynamic HTML views (Thymeleaf)
│ │ ├───bidList
│ │ ├───curvePoint
│ │ ├───rating
│ │ ├───ruleName
│ │ ├───trade
│ │ └───user
│ └───test                # Unit and integration tests
```

## Security

- Authentication with Spring Security

- Managing roles and permissions

- Secure password hashing

- Access control and security measures\*

## Documentation

**Javadoc**:

Javadoc documentation helps to better understand Java code and make it clearer

Consult the Javadoc which is in **target/site/apidocs**

## System requirements

- Java 17
- MySql
- Maven

## Installation

- **1** Clone the GitHub repository.
- **2** Add MySQL information to 'application.properties'
- **3** Build the project: Run

```bash
mvn clean package
```

- **4** Launch the application: Run

```bash
mvn spring-boot:run
```

- **5** Access the application via the address: [http://localhost:8080](http://localhost:8080)

## Tests

Unit Tests: The project includes unit tests to cover all layers of the application.

Run the tests with:

```bash
mvn test
```

Generate the code coverage report (Jacoco) with:

```bash
mvn jacoco:report
```

Generate the unit test display report (Surefire) with:

```bash
mvn surefire-report:report
```

Check the reports that are in **target/site/jacoco/** and **target/site/**

## Default identification

A default user with full rights to test the application

- username: admin
- password: password123
- role: ADMIN
