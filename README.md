# Library API

REST API for library management — CRUD operations for authors and books.

## Tech Stack

Java 21 · Spring Boot 3.4 · JPA · PostgreSQL · Docker · Lombok

## Setup

```bash
docker-compose up -d
./mvnw spring-boot:run
```

Runs at `http://localhost:8080`

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/author/save` | Add author |
| GET | `/author/list` | List authors |
| GET | `/author/detail?firstname=&lastname=` | Author detail with books |
| DELETE | `/author/delete` | Delete author |
| POST | `/book/addbook` | Add book |
| GET | `/book/list` | List books |
| GET | `/book/search?name=` | Search by name |
| GET | `/book/best` | Top-rated books |
| PUT | `/book/update?name=` | Update book |
| DELETE | `/book/delete?name=` | Delete book |

## DB

`localhost:5433` · library_db · admin / admin123
