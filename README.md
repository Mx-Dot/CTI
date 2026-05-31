# CTI

CTI is a Spring Boot API for managing corrugating orders. The current implementation focuses on capturing order details, board specifications, material grades, layer construction, and order length in meters.

## Current Features

- Create corrugating orders through `POST /orders`.
- Retrieve all orders through `GET /orders`.
- Store orders using Spring Data JPA.
- Use an in-memory H2 database for local development.
- Model board specifications as structured data:
  - board material specification
  - ordered layer stack
  - layer type, such as `LINER`, `FLUTE`, or `BACKLINER`
  - material grade per layer
  - optional flute profile, such as `B` or `C`
- Use DTOs for API input and output instead of exposing JPA entities directly.
- Map between DTOs and entities through `OrderMapper`.
- Validate incoming orders before persistence so invalid requests return `400 Bad Request`.
- Allow cross-origin requests through the configured CORS setup.

## Tech Stack

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- H2 Database
- Gradle
- Lombok

## API

### Create Order

```http
POST /orders
```

Example request:

```json
{
  "orderId": 1001,
  "meters": 5000,
  "boardSpec": {
    "materialSpec": {
      "gsm": 125,
      "gradeCode": 1
    },
    "layers": [
      {
        "type": "LINER",
        "grade": {
          "gsm": 125,
          "gradeCode": 1
        },
        "fluteProfile": null
      },
      {
        "type": "FLUTE",
        "grade": {
          "gsm": 110,
          "gradeCode": 2
        },
        "fluteProfile": "B"
      },
      {
        "type": "BACKLINER",
        "grade": {
          "gsm": 125,
          "gradeCode": 1
        },
        "fluteProfile": null
      }
    ]
  }
}
```

### Get Orders

```http
GET /orders
```

Returns all stored orders as `OrderResponse` DTOs.

## Local Development

Run the application:

```bash
./gradlew bootRun
```

Run tests:

```bash
./gradlew test
```

The local H2 database is configured in `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.h2.console.enabled=true
```

## Planned Features

### Customer Data

- Add a `Customer` entity with relational links to orders.
- Store customer names, delivery addresses, contact details, account references, and delivery requirements.
- Support customer-specific board specifications and recurring product templates.
- Track customer order history.

### Order Lookup and Search

- Lookup orders by internal database ID.
- Lookup orders by `orderId`.
- Lookup orders by customer.
- Search orders by customer reference, delivery date, board grade, flute profile, material GSM, and order status.
- Add paginated and filtered order lists for large datasets.

### Corrugating Production Workflow

- Add order statuses, such as `DRAFT`, `CONFIRMED`, `SCHEDULED`, `IN_PRODUCTION`, `COMPLETED`, and `CANCELLED`.
- Add planned production dates and delivery dates.
- Track machine or corrugator line assignment.
- Store planned meters, completed meters, waste meters, and remaining meters.
- Support splitting large orders across multiple production runs.

### Materials and Stock

- Add relational data for paper grades, reels, suppliers, and stock levels.
- Link order layers to real material records instead of only simple grade values.
- Track material consumption per order.
- Warn when stock is too low for scheduled production.
- Support alternate material grades where substitutions are allowed.

### Board Specification Management

- Store reusable board specifications as named templates.
- Support more flute profiles and multi-wall board constructions.
- Validate layer combinations based on corrugating rules.
- Calculate estimated board weight from GSM, layers, and meters.

### Quality and Traceability

- Record quality checks against orders or production runs.
- Track defects, waste reasons, and rejected material.
- Store batch, reel, and supplier traceability data.
- Add notes and audit history for order changes.

### API and Application Structure

- Move validation into DTO validation annotations and service-level business rules.
- Add service classes between controllers and repositories.
- Add update and delete endpoints where appropriate.
- Add structured error responses.
- Add integration tests for order creation, validation, and lookup.
- Add authentication and role-based authorization for production use.

## Development Notes

The current API intentionally uses DTOs for request and response models. This keeps the public API separate from the database entities and makes it easier to evolve the persistence model without breaking clients.

`OrderMapper` owns the conversion between API DTOs and JPA entities, including nested board specification data.
