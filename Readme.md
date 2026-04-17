# 🏥 Hospital Management System

A robust Spring Boot backend application designed to manage hospital operations efficiently with secure JWT authentication and role-based access control.

---

## ✨ Features

- JWT-based authentication and authorization
- Role-based access control (ADMIN, DOCTOR, RECEPTIONIST, PATIENT)
- Patient registration and profile management
- Doctor scheduling with day-wise availability and time slots
- Smart appointment booking with slot validation and double-booking prevention
- Medical record creation after appointment completion
- Insurance policy assignment to patients
- Global exception handling for clean error responses
- Interactive API documentation using Swagger UI

---

## 🏗️ Architecture

![Architecture](docs/images/architecture.png)

The application follows a clean layered architecture:

Controller → Service → Repository → Database

---

## 🗄️ Entity Relationship Diagram

![ER Diagram 1](docs/images/er-diagram-1.png)
![ER Diagram 2](docs/images/er-diagram-2.png)

Core entities include User, Patient, Doctor, Appointment, MedicalRecord, Insurance, DoctorSchedule, and Department with proper JPA relationships.

---

## 🔐 JWT Authentication Flow

![JWT Flow](docs/images/jwt-flow.png)

---

## 🛠️ Tech Stack

- Backend: Java 17+, Spring Boot 3.x
- Security: Spring Security + JWT
- ORM: JPA / Hibernate
- Database: PostgreSQL
- Documentation: Springdoc OpenAPI (Swagger)
- Build Tool: Maven

---

## 📁 Project Structure

```
hospitalManagement/
├── src/main/java/com/example/hospitalManagement/
│   ├── HospitalManagementApplication.java
│   ├── config/
│   ├── controller/
│   ├── dto/
│   ├── entity/
│   ├── exceptions/
│   ├── filters/
│   ├── mapper/
│   ├── repository/
│   ├── service/
│   │   ├── services/
│   │   └── implementations/
│   └── util/
├── src/main/resources/
│   └── application.yml
└── pom.xml
```

---

## 🚀 Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.8+
- PostgreSQL

### Setup Instructions

Clone the repository:

```bash
git clone <your-repository-url>
cd hospitalManagement
```

Create database:

```sql
CREATE DATABASE hospital_db;
```

Update `application.yml`:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/hospital_db
    username: postgres
    password: yourpassword

  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
```

Run the application:

```bash
mvn spring-boot:run
```

Application will be available at:

```
http://localhost:8080
```

---

## 🔑 Default Credentials

```
Username: admin
Password: admin123
```

---

## 📚 API Documentation

```
http://localhost:8080/swagger-ui.html
```

All protected endpoints require:

```
Authorization: Bearer <JWT_TOKEN>
```

---

## 👨‍💻 About This Project

This project was built from scratch and demonstrates strong understanding of Spring Boot architecture, secure REST API development using JWT, and handling of complex business logic such as appointment slot validation and role-based access.

It follows clean coding practices using DTOs, mappers, and global exception handling, along with well-structured JPA entity relationships.

---

## 🔮 Future Enhancements

- Unit and integration testing
- Pagination and advanced search
- Email notifications
- Docker support
- MapStruct integration
- Complete Department module

---

## 📄 License

This project is intended for learning and portfolio purposes.

---

## ⭐ Support

If you found this project useful, consider giving it a star.

---

## 👤 Author

Rohan Khodade