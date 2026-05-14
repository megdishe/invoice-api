# invoice-api

Spring Boot REST API for invoice generation and management.

## Main endpoints

- `GET /api/companies`
- `POST /api/companies`
- `GET /api/customers`
- `POST /api/customers`
- `GET /api/invoices`
- `POST /api/invoices`

## Local run (without Docker)

```bash
cd invoice-api
./mvnw spring-boot:run
```

The service expects MongoDB running on `mongodb://localhost:27017/invoice_api` unless overridden with `MONGODB_URI`.

## Docker

From repository root:

```bash
docker compose up --build invoice-api mongodb
```
