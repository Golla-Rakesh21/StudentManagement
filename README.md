<div align="center">

# 🎓 Student Management System

### RESTful Backend Application with Spring Boot

![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-Backend-6DB33F?style=for-the-badge&logo=springboot)
![Spring Data JPA](https://img.shields.io/badge/Spring%20Data%20JPA-ORM-6DB33F?style=for-the-badge&logo=spring)
![Hibernate](https://img.shields.io/badge/Hibernate-ORM-59666C?style=for-the-badge&logo=hibernate)
![MySQL](https://img.shields.io/badge/MySQL-Database-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-Build-C71A36?style=for-the-badge&logo=apachemaven)
![Swagger](https://img.shields.io/badge/OpenAPI-Swagger-85EA2D?style=for-the-badge&logo=swagger&logoColor=black)

**A backend project focused on student record management, REST API design, and relational database operations.**

</div>

---

## 📖 About the Project

The **Student Management System** is a backend application developed with Java and Spring Boot to manage student records through RESTful APIs. It demonstrates how a layered Spring Boot application handles HTTP requests, applies application logic, and stores data in a relational database.

The project uses **Spring Web** to build REST endpoints, **Spring Data JPA and Hibernate** for persistence, and **MySQL** for database storage. API endpoints can be explored and tested using Swagger/OpenAPI documentation when configured in the application.

## 🧰 Technologies Used

| Technology | Purpose |
|---|---|
| Java | Core language used to implement application logic. |
| Spring Boot | Sets up the backend application and simplifies configuration. |
| Spring Web | Creates REST controllers and handles HTTP requests and responses. |
| Spring Data JPA | Provides repository abstractions for database operations. |
| Hibernate | Maps Java objects to relational database tables through ORM. |
| MySQL | Stores student information persistently. |
| Maven | Manages project dependencies and builds the application. |
| Swagger / OpenAPI | Documents API endpoints and supports interactive API testing. |
| Docker | Containerization, if included in the project configuration. |

## ✨ Key Features

| Feature | Description |
|---|---|
| Student record management | Create, retrieve, update, and delete student records through API operations. |
| RESTful API endpoints | Uses HTTP methods and JSON payloads for client-server communication. |
| Database integration | Persists and retrieves records using MySQL. |
| JPA repository layer | Reduces repetitive data-access code using Spring Data JPA. |
| Entity mapping | Maps Java entity classes to database tables using Hibernate. |
| Search and filtering | Supports student search/filtering functionality where implemented. |
| Pagination and sorting | Supports paginated or sorted retrieval where configured. |
| API documentation | Allows developers to inspect and test documented endpoints through Swagger UI. |

> Check the actual controller mappings and Swagger/OpenAPI definitions in the repository to confirm the exact endpoints and optional features implemented.

## 🏛️ Application Architecture

```text
Client / API Testing Tool
          |
          v
   Controller Layer
          |
          v
     Service Layer
          |
          v
   Repository Layer
   (Spring Data JPA)
          |
          v
    Hibernate ORM
          |
          v
     MySQL Database
```

| Layer | Responsibility |
|---|---|
| Controller | Receives HTTP requests, maps API routes, and returns responses. |
| Service | Contains or coordinates business logic. |
| Repository | Performs data access through Spring Data JPA. |
| Entity | Represents student data and its database mapping. |
| Database | Stores student records persistently. |

This separation keeps responsibilities organized and makes the application easier to maintain and extend.

## 🔌 Typical API Operations

The following are common operations in a student management REST API. Refer to the controller mappings in the source code for the exact URL paths and supported methods.

| Operation | Typical HTTP method | Purpose |
|---|---|---|
| Create student | `POST` | Adds a new student record. |
| Retrieve students | `GET` | Returns student records. |
| Retrieve by ID | `GET` | Finds one student using its identifier. |
| Update student | `PUT` / `PATCH` | Updates an existing record. |
| Delete student | `DELETE` | Removes a student record. |
| Search students | `GET` | Finds records using supported search criteria. |
| Paginated listing | `GET` | Returns a page of results with optional sorting, if implemented. |

### Example JSON Request

Illustrative example only. Update the fields to match your entity and API request schema.

```json
{
  "name": "Ananya Sharma",
  "age": 20,
  "course": "Computer Science",
  "department": "CSE"
}
```

## 🛠️ Skills Demonstrated

- **Backend development:** Building RESTful APIs with Java and Spring Boot.
- **REST principles:** Working with HTTP methods, status codes, request bodies, and JSON responses.
- **Layered architecture:** Separating controller, service, repository, and entity responsibilities.
- **Database management:** Designing and working with relational data in MySQL.
- **ORM and persistence:** Using Spring Data JPA and Hibernate to map entities and perform database operations.
- **API documentation and testing:** Exploring endpoint contracts with Swagger/OpenAPI.
- **Build and dependency management:** Managing libraries and builds with Maven.
- **Configuration and debugging:** Connecting application configuration to the database and troubleshooting backend behavior.

## ▶️ Run Locally

### Prerequisites

- JDK version configured by the project (for example, Java 21 if set in `pom.xml`)
- MySQL Server
- Maven, or the Maven Wrapper if included in the repository
- Git

### 1. Clone the repository

```bash
git clone https://github.com/Golla-Rakesh21/StudentManagement.git
cd StudentManagement
```

### 2. Create a database

Create a MySQL database using the name expected by your application configuration. Example:

```sql
CREATE DATABASE student_management;
```

### 3. Configure database properties

Update `src/main/resources/application.properties` to match your local MySQL setup. Example:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/student_management
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

Set the `DB_USERNAME` and `DB_PASSWORD` environment variables before starting the application, and align the database name and properties with your code. Do not commit passwords or secrets to source control.

### 4. Build and start the application

```bash
mvn clean package
mvn spring-boot:run
```

On Windows, use `mvnw.cmd` if the project includes the Maven Wrapper. On macOS/Linux, use `./mvnw`.

## 📂 Repository

**GitHub:** [Golla-Rakesh21/StudentManagement](https://github.com/Golla-Rakesh21/StudentManagement)

## 👨‍💻 Author

**Golla Venkata Rakesh**

Interested in Java, Spring Boot, REST API development, SQL, and backend engineering.

---

<div align="center">

**Built as a hands-on project to strengthen Spring Boot backend development skills.**

</div>
