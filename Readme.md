# 🏥 Hospital Management System

A robust Spring Boot backend application designed to manage hospital operations efficiently with secure JWT authentication and role-based access control.

![Java](https://img.shields.io/badge/Java-17+-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring%20Security-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=json-web-tokens&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-4169E1?style=for-the-badge&logo=postgresql&logoColor=white)![Swagger](https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=swagger&logoColor=black)
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

![Architecture](docs/images/hms_application_architecture.svg)

The application follows a clean layered architecture:

Controller → Service → Repository → Database

Every request passes through the JWT filter chain before reaching
controllers. The service layer handles business logic and the
repository layer communicates with PostgreSQL via Spring Data JPA.
---

## 🗄️ Entity Relationship Diagram

![ER Diagram 1](docs/images/HMS_ERD.png)
Core entities include User, Patient, Doctor, Appointment, MedicalRecord, Insurance, DoctorSchedule, and Department with proper JPA relationships.

---

## 🔐 JWT Authentication Flow

![JWT Flow](docs/images/jwt_authentication_flow.svg)

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
git clone https://github.com/RohanKhodade/Hospital-Management.git
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
## 📡 API Endpoints

| Method | Endpoint | Role | Description |
|--------|----------|------|-------------|
| POST | /auth/login | Public | Login and get JWT |
| POST | /doctor/create | ADMIN | Create a doctor |
| POST | /appointment/create/{patientId}/{doctorId} | RECEPTIONIST | Book appointment |
| POST | /doctor/complete/appointment/{id} | DOCTOR | Complete appointment |
| GET | /patient/MedicalRecords/{id} | PATIENT, DOCTOR | View medical records |

---
## 📚 API Documentation

```
http://localhost:8080/swagger-ui.html
```

All protected endpoints require:

```
Authorization: Bearer JWT_TOKEN
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