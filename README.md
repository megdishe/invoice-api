# epicraft-pdp

Monorepo for invoice management with:
- `invoice-api`: Spring Boot + MongoDB backend
- `invoice-front`: Angular 20 frontend

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
- Frontend (includes `/api` reverse proxy to backend): `http://localhost:4200`
- API (direct access): `http://localhost:8080`
- MongoDB: `mongodb://localhost:27017`

## Development notes

