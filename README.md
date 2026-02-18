# 🚀 Central Auth Server

## 📝 Description

This project is a dedicated **Authorization Server** based on **Spring Boot 3** and **Spring Authorization Server**. It serves as a central Identity Provider (IdP) to issue, manage, and validate OAuth2 tokens across a distributed microservices ecosystem.

## ✨ Key Features

*  **OAuth2 Compliance**: Implements modern authorization flows (Client Credentials).


* **M2M Communication**: Optimized for machine-to-machine security.


* **JWT Issuance**: Signed tokens using RSA (RS256).


* **JWKS Endpoint**: Public key exposure for seamless resource server validation.


* **PostgreSQL Persistence**: Production-ready client and token management.



## 🛠️ Tech Stack

* **Language**: Java 17/21 ☕ 


* **Framework**: Spring Boot 3.x & Spring Security OAuth2 🍃 


* **Database**: PostgreSQL 15 🐘 


* **Containerization**: Docker & Docker Compose 🐳 


* **Orchestration**: Kubernetes (Kind) ☸️ 


* **Package Management**: Helm 3 🏗️ 



## 🐳 Dockerization

The project is fully containerized. To spin up the development environment with the database:

```bash
# Build and start with Docker Compose
docker-compose up --build -d

# View application logs
docker logs -f central-auth-server

```

## ☸️ Kubernetes & Helm Deployment

We have elevated the infrastructure using **Helm** to orchestrate deployments on a **Kind** cluster.

### 🔧 Useful Infrastructure Commands

**1. Cluster & Helm Setup:**

```bash
# Create Kind cluster
kind create cluster --name auth-cluster --config kind-config.yaml

# Load local image into the cluster
kind load docker-image jchacon/central-auth-server:latest --name auth-cluster

# Lint the Helm Chart
helm lint charts/central-auth-server

```

**2. Deployment Workflow:**

```bash
# Clean Install
helm install central-auth-server ./charts/central-auth-server

# Force pod restart (to refresh configuration)
kubectl rollout restart deployment central-auth-server

```

**3. Troubleshooting & Database:**

```bash
# Access DB within the cluster
kubectl exec -it auth-server-db-0 -- psql -U user_chacon -d auth_server_db

# View startup logs (Seed Data verification)
kubectl logs -f deployment/central-auth-server

```

## 🔑 Token Request Test

Once deployed, you can test the `client_credentials` flow using the `service-one` client automatically created by the `ClientLoader`: 

```bash
# 1. Create a tunnel to the service
kubectl port-forward svc/central-auth-server 9000:9000

# 2. Request Token
curl -X POST http://localhost:9000/oauth2/token \
     -u "service-one:secret1" \
     -d "grant_type=client_credentials" \
     -d "scope=read:data"

```

---

## 📄 License

**MIT License**

Copyright (c) 2026 Julio Chacon

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

---


*Developed by **Julio Chacon** - Senior Backend Developer* 👨‍💻