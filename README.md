# Lab Management System

A full-stack web application for managing a research laboratory — members, events, publications, and tools.

## Tech Stack

**Frontend:** Angular 16, Angular Material, Firebase Auth  
**Backend:** Spring Boot 3 (4 microservices + Eureka discovery), MySQL  
**Service Discovery:** Netflix Eureka  
**Mock Backend:** json-server (for local development without MySQL)

---

## Project Structure

```
Lab-Management-System/
├── frontend/                 # Angular 16 app
└── backend/
    ├── Eureka-Server/        # Port 8761 — Service Discovery
    ├── Membre-Service/       # Port 8081
    ├── Evenement-Service/    # Port 8082
    ├── Publication-Service/  # Port 8083
    └── Outil-Service/        # Port 8084
```

---

## Features

- **Authentication** — Google Sign-In via Firebase
- **Members** — Add, edit, delete lab members (students & teachers)
- **Events** — Manage lab events with date ranges
- **Publications** — Track journal articles, conferences, and more
- **Tools** — Manage lab tools and resources
- **Dashboard** — Overview of members, events, and publications count

---

## Getting Started

### Prerequisites

- Node.js v18+
- Java 21
- MySQL (for Spring Boot services)

---

### Run the Frontend (with mock backend)

```bash
cd frontend
npm install --legacy-peer-deps

# Start json-server (mock backend on port 3000)
npx json-server --watch src/assets/_db.json --port 3000

# In a separate terminal, start Angular
npx ng serve
```

App runs at **http://localhost:4200**

---

## Eureka Service Discovery

All microservices auto-register with the Eureka Server on startup.

- **Eureka Dashboard:** http://localhost:8761
- Each service registers using its `spring.application.name`
- Configuration in each service's `application.properties`:

```properties
eureka.client.service-url.defaultZone=http://localhost:8761/eureka/
eureka.instance.prefer-ip-address=true
```

| Service | App Name | Port |
|---|---|---|
| Eureka-Server | Eureka-Server | 8761 |
| Membre-Service | Membre-Service | 8081 |
| Evenement-Service | Evenement-Service | 8082 |
| Publication-Service | Publication-Service | 8083 |
| Outil-Service | Outil-Service | 8084 |

---

## API Endpoints

| Service | Port | Base URL |
|---|---|---|
| Membre-Service | 8081 | `/membres` |
| Evenement-Service | 8082 | `/evenements` |
| Publication-Service | 8083 | `/publications` |
| Outil-Service | 8084 | `/outils` |

Each service exposes full CRUD: `GET /`, `GET /{id}`, `POST /`, `PUT /{id}`, `DELETE /{id}`
