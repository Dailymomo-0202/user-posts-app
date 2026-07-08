# Development Notes

This document shows the reasoning and process behind this project.

## Approach

I broke the task down into layers, building and verifying each one before moving to the next:

1. Project scaffold (Spring Initializr) → verified it runs before writing any code
2. Entity design (User, Post) → decided on a flattened schema rather than mirroring the nested JSON structure from the  
   API, because the frontend just render the simple information not complex component
3. Repository layer (Spring Data JPA) → minimal, relies on Spring's auto-generated implementations
4. DTOs to match JSONPlaceholder's actual JSON shape (including nested `address`/`company` objects)
5. Service layer to fetch, transform ("flatten"), and persist the data
6. Controller + Thymeleaf views
7. Unit tests for the sync logic using Mockito
8. Final verification: cloned the repo into a clean directory and confirmed it runs with a single command, with no leftover local configuration

## Key Design Decisions

**Flattened Entity vs nested DTO**
JSONPlaceholder returns nested `address` and `company` objects. Rather than mirroring this nesting in the database (which would mean extra tables and joins for data that's purely for display), I kept the DTOs nested to match the API response exactly, and flattened the data into simple columns when mapping to the `User` entity in the service layer. This keeps the database schema independent of the external API's shape — if the data source ever changed, only the DTO and mapping logic would need to change.

**Lazy sync instead of CommandLineRunner**
I initially considered using a `CommandLineRunner` to fetch and store data on application startup. I switched to triggering the sync lazily on the first request to `/users` (checking if the local table is empty first) instead, since the controller already needed this logic and adding a separate runner class felt like unnecessary structure for this scope.

**H2 in-memory database**
Chosen to keep the project fully self-contained — no external database setup is required to run the app. In a production system I'd use PostgreSQL, but for a project focused on demonstrating a working pipeline end-to-end, H2 removes a layer of setup friction without compromising on using a real SQL-compatible database.

## Notable Issues Encountered

- **`user` is a reserved SQL keyword** in H2, which caused table creation to fail. Resolved with `@Table(name = "users")`.
- **Thymeleaf field mismatches**: a couple of template errors were caused by referencing the DTO's nested structure (e.g. `user.company.name`) from a template that was actually rendering the flattened `User` entity (`user.companyName`). This highlighted the importance of keeping clear which object (DTO vs Entity) is in scope at each layer.

## What I'd Improve With More Time

- Add validation and more defensive null-handling in the sync logic
- Add pagination for the user list
- Expand test coverage to include the Post sync path and Controller layer
- Consider PostgreSQL + Docker Compose for a more production-like setup
