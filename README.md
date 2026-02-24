# Library API

A RESTful API for library management with JWT security, built with Spring Boot and PostgreSQL.

## Tech Stack
* Java 21 & Spring Boot 3.4
* Spring Security (JWT)
* PostgreSQL & Docker

---

## Security
This API uses **JWT** for authentication.
1. **Login:** Use `/api/v1/auth/authenticate` to get a token.
2. **Usage:** Include the token in the request header:  
   `Authorization: Bearer <your_token>`

---

## API Endpoints

| Method | Endpoint | Description | Access |
| :--- | :--- | :--- | :--- |
| POST | `/api/v1/auth/register` | Create user | Public |
| POST | `/api/v1/auth/authenticate` | Get token | Public |
| GET | `/book/list` | List books | Public |
| GET | `/book/search` | Search books | Public |
| GET | `/book/best` | Top books | Public |
| GET | `/author/list` | List authors | Authenticated |
| GET | `/author/detail` | Author details | Authenticated |
| POST | `/author/save` | Add author | Authenticated |
| DELETE | `/author/delete` | Delete author | Authenticated |
| POST | `/book/addbook` | Add book | Authenticated |
| PUT | `/book/update` | Update book | Authenticated |
| DELETE | `/book/delete` | Delete book | Authenticated |

---

## Setup & Database
1. **Run DB:** `docker-compose up -d`
2. **Run App:** `./mvnw spring-boot:run`

**Database Info:**
* Host: `localhost:5433` | DB: `library_db`
* User/Pass: `admin` / `admin123`
