# Resenha Sociocultural API
Backend API (Spring Boot, JPA) for the "Resenha Sociocultural" social project. Manages youth participants, discussion meetings, attendance tracking, behavior strikes, and outing eligibility rules.

## Features

* Manages registration and information of youth participants (12-16yo).
* Tracks discussion meetings (date, theme, snacks, costs).
* Records attendance status (Present, Excused Absence, Unexcused Absence) for each youth per meeting.
* Manages behavior "Strikes" with accumulation and monthly reduction logic.
* Determines youth eligibility for monthly outings based on attendance and active strikes.
* (Planned) Secure endpoints using JWT authentication.

## Technologies Used

* **Language:** Java 21
* **Framework:** Spring Boot 3.x.x
* **Data Persistence:** Spring Data JPA, Hibernate
* **Database:** PostgreSQL
* **Build Tool:** Maven
* **Utils:** Lombok
* **API Documentation:** Springdoc OpenAPI (Swagger UI)
* **(Planned):** Docker, JWT

## Project Status

* Currently under initial development.
* Core entities and repository structure are being defined.
* Detailed setup and running instructions, including Docker configuration, will be added soon.
