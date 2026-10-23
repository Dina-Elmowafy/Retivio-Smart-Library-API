# Retivio Smart Library API

Spring Boot backend for a digital library. This repository contains the API service; it does not include the Angular client mentioned in the repository description.

## Features

- List books and retrieve a book by ISBN
- Create users and reviews, and read users, reviews, and borrowing history
- Request book recommendations from a companion Python service
- Store books, users, reviews, borrowing history, and search logs in JSON files under `src/main/resources/data`

## API

| Method | Endpoint | Description |
| --- | --- | --- |
| GET | `/api/books` | List books |
| GET | `/api/books/{isbn}` | Find a book by ISBN |
| GET / POST | `/api/users` | List or create users |
| GET | `/api/users/{id}` | Find a user |
| GET / POST | `/api/reviews` | List or create reviews |
| GET / POST | `/api/borrow_history` | List or add borrowing records |
| GET | `/api/recommendations/{userId}` | Request recommendations; optional `searchText` and `localIsbns` query parameters |

The recommendation endpoint calls a Python service at `http://localhost:8001`. Start that service separately before using recommendations. The other API endpoints use the bundled JSON-backed repositories.

## Run locally

Requirements: JDK 17.

```bash
./mvnw spring-boot:run
```

The Springdoc UI is available at `http://localhost:8080/swagger-ui/index.html`.

Run the existing tests with:

```bash
./mvnw test
```

## Project layout

- `src/main/java/com/example/demo/retivio/controller`: REST controllers
- `src/main/java/com/example/demo/retivio/service`: library and recommendation services
- `src/main/java/com/example/demo/retivio/repository`: JSON-backed repositories
- `src/main/resources/data`: sample JSON data
