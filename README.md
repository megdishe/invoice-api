# epicraft-pdp

Monorepo for invoice management with:
- `invoice-api`: Spring Boot + MongoDB backend
- `invoice-front`: Angular frontend (git submodule)

## Project structure

- `invoice-api/` backend REST API for companies, customers, and invoices.
- `invoice-front/` frontend app that consumes `invoice-api`.
- `docker-compose.yml` local multi-service stack.

## Run with Docker Compose

```bash
git submodule update --init --recursive
docker compose up --build
```

Services:
- API: `http://localhost:8080`
- Frontend: `http://localhost:4200`
- MongoDB: `mongodb://localhost:27017`

## Development notes

When cloning the repository, initialize submodules before working with the frontend:

```bash
git submodule update --init --recursive
```
