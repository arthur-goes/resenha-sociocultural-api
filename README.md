# Resenha Sociocultural API

[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-green.svg)](https://spring.io/projects/spring-boot)
[![Docker](https://img.shields.io/badge/Docker-Enabled-blue.svg)](https://www.docker.com/)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

> 🇧🇷 **Para a versão em Português deste documento, [clique aqui](README.pt-br.md).**

## 📖 Overview

**Resenha Sociocultural** is a social project dedicated to welcoming young people from a low-income community into a discussion group focused on developing their maturity.

This API is a comprehensive backend solution designed to manage the Resenha Sociocultural project. It streamlines the administration of youth participants, event scheduling, attendance tracking, and behavior tracking (strikes and participation points).

The project was built with a **Contract-First** approach, ensuring a robust and well-documented API specification before implementation. It leverages a modern Java stack, including a custom OAuth2 Authorization Server and rigorous integration testing with Testcontainers.

## 🚀 Key Features

*   **Youth Management:** Complete lifecycle management for project participants (registration, updates, emergency contacts).
*   **Event & Attendance:** Create meetings and track youth attendance with automated status updates.
*   **Disciplinary System:** "Strike" system to manage behavioral infractions and participation points.
*   **Contract-First Design:** API interfaces generated automatically from an OpenAPI specification.
*   **OAuth2 Authorization Server:** Unlike simple JWT implementations, this project implements a full **Authorization Server** using `spring-boot-starter-oauth2-authorization-server`.
*   **Role-Based Access Control (RBAC):** Granular permissions (Admin, Manager, Coordinator, User) secured by OAuth2.
*   **Integration Tests with Testcontainers:** No mocked database is used. An ephemeral PostgreSQL container is started to ensure the code runs against a real database environment.

## 🔮 Roadmap & Future Improvements

This project is currently in the **active development phase**. The following features are planned for the next release cycle to prepare the application for a production environment:

*   **Admin Management Endpoints:** Develop endpoints for granular management of entities and user access control.
*   **Reports:** Implement a reporting module to visualize attendance and behavioral data.
*   **Database Versioning:** Implementation of **Flyway** to manage schema migrations and replace the current `ddl-auto` mechanism used for rapid prototyping.
*   **CI/CD Pipeline:** Automation of the deployment process to a cloud provider (AWS or Render) using GitHub Actions.
*   **External Identity Provider:** Transition from the embedded Custom Authorization Server to an external provider like **Keycloak** or **Auth0** for enhanced security and scalability.
*   **Observability:** Integration with **Prometheus** and **Grafana** for real-time metrics and monitoring.

## 🛠️ Tech Stack & Architecture

This project follows a **Feature-Based Packaging** structure to ensure modularity and maintainability.

*   **Core:** Java 17, Spring Boot 3
*   **Database:** PostgreSQL (Dockerized), H2 (Test)
*   **ORM:** Spring Data JPA / Hibernate
*   **Security:** Spring Security, OAuth2 Authorization Server (JWT)
*   **API Specification:** OpenAPI 3.0, Swagger UI, SpringDoc
*   **Testing:** JUnit 5, Mockito, **Testcontainers** (Integration Tests)
*   **Tools:** Maven, Docker, Docker Compose, MapStruct, Lombok

## 🏗️ Architecture Highlights

### Contract-First Approach
Unlike traditional code-first development, this API uses an `openapi.yaml` definition as the single source of truth. The `openapi-generator-maven-plugin` generates the Java interfaces, ensuring the implementation strictly adheres to the API contract.

### Security
The application implements a self-contained **OAuth2 Authorization Server**. It issues JWTs signed with RSA keys, handling the complete flow of authentication and authorization.

## ⚙️ Getting Started

### Prerequisites
*   Java 17+
*   Docker & Docker Compose
*   Maven

### Installation & Running

1.  **Clone the repository:**
    ```bash
    git clone https://github.com/arthur-goes/resenha-sociocultural-api.git
    cd resenha-sociocultural-api
    ```

2.  **Run with Docker Compose (Recommended):**
    The project is designed to run out-of-the-box with default settings. This will start the API and the PostgreSQL database.
    ```bash
    docker-compose up -d --build
    ```

    > **💡 Optional Configuration:** The application uses default credentials for a seamless development experience. If you wish to customize database settings or security keys, you can create a `.env` file based on `.env.example` before running the command above.

3.  **Access the API Documentation:**
    Once running, the Swagger UI is available at:
    *   http://localhost:8080/swagger-ui.html

    **To authenticate via OAuth2 (Swagger UI):**
    1.  Click the **Authorize** button.
    2.  **client_id:** `swagger-ui` (Leave client_secret empty).
    3.  **Scopes:** Check the `api` box.
    4.  Click **Authorize**. You will be redirected to the login page.
    5.  **Credentials:** Use user `admin` and password `admin`.

### Running Tests
The project uses **Testcontainers** to spin up ephemeral PostgreSQL instances for integration testing, ensuring environment parity.

```bash
./mvnw verify

```

### 📝 License
Distributed under the MIT License. See LICENSE for more information.