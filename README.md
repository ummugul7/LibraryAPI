# 📚 Library API

A RESTful API for library management with robust security features, built using Spring Boot 3.4 and JWT.

## 🚀 Tech Stack

* **Java 21** & **Spring Boot 3.4**
* **Spring Security & JWT** (Stateless Authentication)
* **Spring Data JPA** & **PostgreSQL**
* **Lombok** (For cleaner code)
* **Docker** (Database containerization)

---

## 🔐 Security Mechanism (JWT)

This project implements **JWT (JSON Web Token)** for stateless authentication.

1.  **Registration/Login:** Use the `/api/v1/auth` endpoints to create a user and receive a JWT token.
2.  **Authorization:** For endpoints marked as **Authenticated**, you must include your token in the HTTP Header as follows:
    `Authorization: Bearer <your_jwt_token>`



---

## 📡 API Endpoints

### 🔑 Authentication (Auth)
| Method | Endpoint | Description | Access |
| :--- | :--- | :--- | :--- |
| POST | `/api/v1/auth/register` | Create a new user account | Public |
| POST | `/api/v1/auth/authenticate` | Login and receive JWT Token | Public |

### ✍️ Author Operations
| Method | Endpoint | Description | Access |
| :--- | :--- | :--- | :--- |
| GET | `/author/list` | List all authors | Public |
| GET | `/author/detail` | Get author details and books | **Authenticated** |
| POST | `/author/save` | Add a new author | **Authenticated** |
| DELETE | `/author/delete` | Delete an author | **Authenticated (Admin)** |

### 📖 Book Operations
| Method | Endpoint | Description | Access |
| :--- | :--- | :--- | :--- |
| GET | `/book/list` | List all books | Public |
| GET | `/book/search?name=` | Search books by name | Public |
| GET | `/book/best` | List top-rated books | Public |
| POST | `/book/addbook` | Add a new book | **Authenticated** |
| PUT | `/book/update` | Update book details | **Authenticated** |
| DELETE | `/book/delete` | Remove a book from system | **Authenticated** |

---

## 🛠️ Setup & Installation

1.  **Start Database (Docker):**
    ```bash
    docker-compose up -d
    ```
2.  **Run the Application:**
    ```bash
    ./mvnw spring-boot:run
    ```

### 🗄️ Database Configuration
* **Host:** `localhost:5433`
* **DB Name:** `library_db`
* **Credentials:** `admin` / `admin123`

---s
