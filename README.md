# 🏦 Enterprise Banking Portal - Capstone Project

## 📌 Overview
This is a full-stack Enterprise Java application implementing a secure Banking Portal. The project demonstrates the integration of a Spring Boot REST backend with a responsive Vanilla JS frontend, focusing on security, stateless authentication, and professional error handling.

## 🛠️ Tech Stack
- **Backend**: Java 17, Spring Boot 3.2, Spring Security 6, Spring Data JPA, H2 Database.
- **Security**: JWT (JSON Web Tokens), BCrypt Password Encryption.
- **Frontend**: HTML5, CSS3 (Bootstrap 5), Vanilla JavaScript.
- **Documentation**: OpenAPI / Swagger UI.

## 🚀 Key Features
- **Secure Authentication**: Stateless JWT Bearer token authentication flow.
- **Account Management**: Create bank accounts and manage balances.
- **Financial Transactions**: Atomic fund transfers between accounts using `@Transactional`.
- **Enterprise Exception Handling**: Centralized `@ControllerAdvice` returning standardized JSON error responses.
- **Interactive API Docs**: Fully documented endpoints via Swagger UI.

## 🏗️ Architecture
`Frontend (Static HTML/JS)` $\rightarrow$ `JWT Filter` $\rightarrow$ `Spring Security Context` $\rightarrow$ `REST Controller` $\rightarrow$ `Service Layer` $\rightarrow$ `JPA Repository` $\rightarrow$ `H2 Database`

## 📖 Setup & Installation

### Prerequisites
- JDK 17+
- Maven 3.8+

### Running the Application
1. Clone the repository.
2. Run the application using Maven:
   ```bash
   mvn spring-boot:run
   ```
3. Access the frontend: `http://localhost:8080/index.html`
4. Access API Docs: `http://localhost:8080/swagger-ui.html`

## 🔌 API Endpoints
| Method | Endpoint | Description | Auth Required |
| :--- | :--- | :--- | :---: |
| `POST` | `/api/auth/signup` | Register a new user | No |
| `POST` | `/api/auth/login` | Authenticate and get JWT | No |
| `POST` | `/api/accounts` | Create a bank account | Yes |
| `GET` | `/api/accounts/balance/{acc}` | Check account balance | Yes |
| `POST` | `/api/accounts/transfer` | Transfer funds | Yes |

## 🛡️ Security Flow
1. User logs in via `/api/auth/login`.
2. Server validates credentials and returns a signed JWT.
3. Frontend stores JWT in `localStorage`.
4. Every subsequent request includes the token in the `Authorization: Bearer <token>` header.
5. `JwtAuthenticationFilter` validates the token and populates the `SecurityContext`.
