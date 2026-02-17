# Central Auth Server

## Description
This project is a dedicated **Authorization Server** based on **Spring Boot 3** and **Spring Authorization Server**. It serves as a central Identity Provider (IdP) to issue, manage, and validate OAuth2 tokens across a distributed microservices ecosystem.

## Key Features
* **OAuth2 Compliance**: Implements modern authorization flows.
* **M2M Communication**: Optimized for `client_credentials` grant type.
* **JWT Issuance**: Signed tokens using RSA (RS256).
* **JWKS Endpoint**: Public key exposure for seamless resource server validation.
* **PostgreSQL Persistence**: Production-ready client and token management.

## Tech Stack
* **Java 17/21**
* **Spring Boot 3.x**
* **Spring Security & OAuth2**
* **Spring Data JPA**
* **PostgreSQL**
* **Docker** (Future support)

## Architecture Overview
The server acts as the source of truth for security, allowing different backend services to authenticate using unique Client IDs and Secrets to obtain scoped Access Tokens.

---
*Developed by Julio Chacon - Senior Backend Developer*