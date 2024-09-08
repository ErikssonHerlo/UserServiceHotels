# User Service

The **User Service** is a core microservice within the "Comer y Dormir" project, responsible for managing user information, including clients, employees, and administrators. This service is designed using Hexagonal Architecture with Vertical Slicing to ensure high modularity, scalability, and maintainability.

## Table of Contents

- [Overview](#overview)
- [Architecture](#architecture)
    - [Hexagonal Architecture](#hexagonal-architecture)
    - [Vertical Slicing](#vertical-slicing)
- [Domain Scope](#domain-scope)
    - [Clients](#clients)
    - [Employees](#employees)
    - [Administrators](#administrators)
- [Project Structure](#project-structure)
- [Installation](#installation)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## Overview

The **User Service** handles all user-related operations, including registration, authentication, and role-based access management. It manages three primary types of users:

- **Clients**: Customers who book rooms or dine at restaurants.
- **Employees**: Staff working at various hotels or restaurants.
- **Administrators**: Users with elevated privileges to manage system configurations and monitor operations.

## Architecture

### Hexagonal Architecture

Hexagonal Architecture, also known as **Ports and Adapters Architecture**, is used to keep the business logic independent from external systems such as databases, messaging systems, or user interfaces. The core idea is to make the application adaptable to changes and extendable with new features without altering the core business logic.

**Key Components**:
- **Domain Layer**: Contains the business logic and domain entities (`User`, `Role`, etc.).
- **Application Layer**: Contains the use cases (`RegisterUser`, `LoginUser`, etc.) that orchestrate the flow between the domain and external services.
- **Adapters Layer**: Divided into input adapters (controllers) and output adapters (repositories) to handle the interaction with external systems (e.g., HTTP requests, database access).

### Vertical Slicing

Vertical Slicing is a design principle that encourages breaking down the application into smaller, more manageable slices, each representing a complete feature or functionality. Each slice contains all necessary layers (UI, business logic, data access) for that feature.

In the **User Service**, we apply vertical slicing by organizing the codebase around user-related functionalities:
- **User Registration and Authentication**
- **User Profile Management**
- **Role and Permission Management**

## Domain Scope

The domain scope of the **User Service** includes three main categories of users:

### Clients

Clients are users who interact with the system to make reservations or place orders in restaurants. They can:
- Register and manage their profiles.
- View and update their reservations and orders.
- Provide feedback on services.

### Employees

Employees are users who work at the hotels or restaurants. They have additional capabilities:
- Manage client reservations and orders.
- Generate reports and manage inventories.
- Access specific operational features based on their positions**** (e.g., Receptionist, Chef, Waiter).

### Administrators

Administrators are users with elevated privileges to manage and monitor the entire system:
- Manage user accounts and roles.
- Oversee hotel and restaurant operations.
- Configure system-wide settings and monitor logs.

## Project Structure

The project follows a modular structure to support Hexagonal Architecture and Vertical Slicing:

```
/src
|-- /main
|   |-- /java
|   |   |-- /com
|   |   |   |-- /erikssonherlo
|   |   |   |   |-- /users-service                # Microservice: Users Service
|   |   |   |   |   |-- /application              # Application Layer
|   |   |   |   |   |   |-- /usecases             # Use cases specific to 'users-service'
|   |   |   |   |   |   |   |-- RegisterUserUseCase.java
|   |   |   |   |   |   |   |-- LoginUserUseCase.java
|   |   |   |   |   |   |-- /dto                  # Data Transfer Objects
|   |   |   |   |   |   |   |-- UserDTO.java
|   |   |   |   |   |   |   |-- LoginRequestDTO.java
|   |   |   |   |   |   |-- /ports                # Ports (interfaces for input/output)
|   |   |   |   |   |   |   |-- RegisterUserInputPort.java
|   |   |   |   |   |   |   |-- LoginUserInputPort.java
|   |   |   |   |   |   |   |-- UserRepositoryPort.java
|   |   |   |   |-- /domain                       # Domain Layer
|   |   |   |   |   |-- /model                    # Domain Entities
|   |   |   |   |   |   |-- User.java
|   |   |   |   |-- /infrastructure               # Infrastructure Layer
|   |   |   |   |   |-- /adapter
|   |   |   |   |   |   |-- /input                # Input Adapters (Controllers, Filters)
|   |   |   |   |   |   |   |-- UserController.java
|   |   |   |   |   |   |   |-- JWTAuthenticationFilter.java
|   |   |   |   |   |   |-- /output               # Output Adapters (Persistence)
|   |   |   |   |   |   |   |-- UserJpaRepositoryAdapter.java
|   |   |   |   |   |-- /config                   # Configuration Classes
|   |   |   |   |   |   |-- SecurityConfig.java
|   |   |   |   |   |   |-- SwaggerConfiguration.java
|   |   |   |   |   |   |-- ApplicationConfiguration.java
|   |   |   |   |   |   |-- SchedulingConfiguration.java
|   |   |   |   |   |-- /cron                     # Cron Jobs and Scheduled Tasks
|   |   |   |   |   |   |-- ReservationScheduler.java
|   |   |   |   |   |-- /migrations               # Database Migrations (Flyway or Liquibase)
|   |   |   |   |   |   |-- V1__Create_Users_Table.sql
|   |   |   |   |-- /common                       # Common Utilities and Shared Services
|   |   |   |   |   |-- /security
|   |   |   |   |   |   |-- JWTService.java
|   |   |   |   |   |-- /exceptions               # Custom Exceptions
|   |   |   |   |   |   |-- UserNotFoundException.java
|   |-- /resources                                # Resources Directory
|   |   |-- /db
|   |   |   |-- /migrations                       # SQL Migration Files
|   |   |   |   |-- V1__Create_Users_Table.sql
|   |   |-- application.properties                # Spring Boot Application Properties
|   |   |-- application-dev.properties            # Development Configuration Properties
|   |   |-- application-prod.properties           # Production Configuration Properties
```

## Installation

1. **Clone the repository**:

   ```bash
   git clone https://github.com/erikssonherlo/users-service.git
   cd users-service
   ```

2. **Build the project**:

   ```bash
   mvn clean install
   ```

3. **Run the application**:

   ```bash
   mvn spring-boot:run
   ```

## Usage

- **API Documentation**: Accessible via Swagger UI at `http://localhost:8080/swagger-ui.html`.
- **Health Check**: Verify the service status by accessing `http://localhost:8080/actuator/health`.

## Contributing

We welcome contributions to the User Service. To contribute:

1. Fork the repository.
2. Create a new feature branch (`git checkout -b feature/new-feature`).
3. Commit your changes (`git commit -am 'Add new feature'`).
4. Push to the branch (`git push origin feature/new-feature`).
5. Open a Pull Request.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

This README provides an overview of the **User Service**, explains the architectural decisions, and guides you through installation and usage. Feel free to modify and expand it as your project evolves!