
# User Posts App

A Java Spring Boot web application that consumes user and post data from
[JSONPlaceholder](https://jsonplaceholder.typicode.com), stores it in a H2 local
SQL database, and displays it through a simple web interface.

## Tech Stack

- Java 21 (Spring Boot 4.x)
- Spring Web, Spring Data JPA
- Thymeleaf (Frontend rendering)
- H2 Database (in-memory, SQL-compatible)

## How It Works

1. On first visit to `/users`, the app checks whether the local database is
   empty.
2. If empty, it fetches all users and posts from JSONPlaceholder's REST API
   and stores them in the local H2 database.
3. Subsequent requests are served directly from the local database.

## Prerequisites

- JDK 17 or higher installed
- No need to install Maven separately — the project includes the Maven Wrapper (`mvnw`)

## How to Run
Clone the repository, then from the project root:

```
bash
./mvnw spring-boot:run
```

```
Once started, open your browser at:
http://localhost:8080/users
```

## Project Structure
src/main/java/com/yaochen/user_posts_app/
Entity/        # JPA entities (User, Post)
Repository/     # Spring Data JPA repositories
DTO/            # DTOs matching JSONPlaceholder's JSON structure
Service/        # Business logic (API sync)
Controller/      # Web controllers
Config/          # RestTemplate bean configuration


## Design Notes

- **H2 in-memory database** was chosen to keep the project self-contained and runnable with a single command, without requiring the reviewer to set up an external database.
In a production environment, PostgreSQL or MySQL would be more appropriate.

- **DTOs are structured to mirror JSONPlaceholder's nested JSON** (e.g. `address`, `company`), while the JPA entities store this data in a flattened form for simplicity, since this data is only used for display.
- Data sync is triggered lazily on first page load.

## Note on AI Usage
AI tools were used for research and understanding concepts (e.g. Spring Data JPA relationships, Thymeleaf syntax) during development, but not for generating the appication code itself.