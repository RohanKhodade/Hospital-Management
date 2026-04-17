# 🏥 Hospital Management System

A robust **Spring Boot** backend application for managing hospital operations with secure JWT authentication and role-based access control.

![Java](https://img.shields.io/badge/Java-17+-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring%20Security-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=json-web-tokens&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![Swagger](https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=swagger&logoColor=black)

---

## ✨ Features

- Secure JWT-based authentication and authorization
- Role-based access control (**ADMIN**, **DOCTOR**, **RECEPTIONIST**, **PATIENT**)
- Patient registration and profile management
- Doctor management with day-wise scheduling and time slots
- Smart appointment booking with slot validation and double-booking prevention
- Medical record creation when doctor completes an appointment
- Insurance policy assignment to patients
- Global exception handling for clean error responses
- Interactive API documentation using Swagger UI

---

## 🏗️ System Architecture

![Spring Boot Layered Architecture](https://i.imgur.com/2fK8vL9.png)

The application follows a clean **three-layer architecture**:
**Controller → Service → Repository → Database**

---

## 🗄️ Entity Relationship Diagram

![Hospital ER Diagram 1](https://i.imgur.com/5vN8pQm.png)
![Hospital ER Diagram 2](https://i.imgur.com/8XjK2Lm.png)

Key entities include `User`, `Patient`, `Doctor`, `Appointment`, `MedicalRecord`, `Insurance`, `DoctorSchedule`, and `Department` with proper JPA relationships.

---

## 🔐 JWT Authentication Flow

![JWT Authentication Flow](https://i.imgur.com/7pL9vXt.png)

---

## 🛠️ Tech Stack

- **Backend**: Java 17+, Spring Boot 3.x
- **Security**: Spring Security + JWT
- **ORM**: JPA / Hibernate
- **Database**: MySQL
- **Mapping**: Custom DTO Mappers
- **Documentation**: Springdoc OpenAPI (Swagger)
- **Build Tool**: Maven

---

## 📁 Project Structure

```bash
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
│   │   ├── services/           # Service Interfaces
│   │   └── implementations/    # Service Implementations
│   └── util/
├── src/main/resources/
│   └── application.yml
└── pom.xml

🚀 Getting Started
Prerequisites

Java 17 or higher
Maven 3.8+
MySQL Server

Steps to Run

Clone the repositoryBashgit clone <your-repository-url>
cd hospitalManagement
Configure DatabaseCreate a database named hospital_db and update src/main/resources/application.yml:YAMLspring:
  datasource:
    url: jdbc:mysql://localhost:3306/hospital_db?createDatabaseIfNotExist=true
    username: root
    password: yourpassword
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
Run the applicationBashmvn spring-boot:run

Application will be available at http://localhost:8080

🔑 Default Credentials

Username: admin
Password: admin123


📚 API Documentation
Swagger UI: http://localhost:8080/swagger-ui.html
All protected endpoints require a valid JWT token in the Authorization header as Bearer <token>.

👨‍💻 About This Project
This entire project was developed from scratch by me, line by line. It showcases:

Strong understanding of Spring Boot and layered architecture
Secure REST API development with JWT
Complex business logic (appointment slot validation, role-based access)
Proper JPA entity relationships and mappings
Clean code practices with DTOs, Mappers, and Global Exception Handling


🔮 Future Enhancements

Unit and Integration Tests
Pagination and advanced search
Email notifications for appointments
Docker support
Migration to MapStruct
Complete Department module


📄 License
This project is built for learning and portfolio purposes.

Made with ❤️ using Spring Boot
⭐ If you like this project, feel free to star the repository!

Developed by Rohan