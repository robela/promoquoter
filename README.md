# PromoQuoter Microservice

A Spring Boot REST service for cart pricing and inventory reservation with pluggable promotion rules.

## Features
- Product and Promotion management
- Cart price quoting with composable promotion rules
- Inventory reservation with idempotency and concurrency safety
- H2 in-memory database
- Swagger UI for API documentation

## Requirements
- Java 17+
- Spring Boot 3.x

## How to Run
1. Build and start the application:
   ```sh
   ./gradlew bootRun
   ```
2. Access Swagger UI:
   [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
3. Access H2 Console:
   [http://localhost:8080/h2-console](http://localhost:8080/h2-console)

## Example Requests

### Create Products
```json
[
  { "name": "Widget", "category": "GADGET", "price": 19.99, "stock": 100 },
  { "name": "Gizmo", "category": "GADGET", "price": 29.99, "stock": 50 }
]
```
POST to `/products`

### Create Promotions
```json
[
  { "type": "PERCENT_OFF_CATEGORY", "category": "GADGET", "percentOff": 10 },
  { "type": "BUY_X_GET_Y", "productId": 1, "buyX": 2, "getY": 1 }
]
```
POST to `/promotions`

### Cart Quote
```json
{
  "items": [ { "productId": 1, "qty": 3 }, { "productId": 2, "qty": 2 } ],
  "customerSegment": "REGULAR"
}
```
POST to `/cart/quote`

### Cart Confirm
Use same payload as quote. Optionally add header:
`Idempotency-Key: some-unique-key`
POST to `/cart/confirm`

## Assumptions
- Promotions are applied in the order they are defined in the DB
- Stock is reserved atomically using optimistic locking
- Idempotency is handled via the Idempotency-Key header
- No UI is provided

## Testing
Run all tests:
```sh
./gradlew test
```

## Code Coverage
Generate coverage report:
```sh
./gradlew jacocoTestReport
```

## Contact
For questions, contact Robel Alazar +251911457978 robelaaa@gmail.com.
