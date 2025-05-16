# Reward Points Calculation API
 
This Spring Boot application calculates reward points for customers based on their transaction history. Reward points are computed according to a tiered structure, and results are grouped by customer and by month (limited to the past 3 months).
 
## Features
 
- Accepts a list of customer transactions.
- Filters transactions from the past 3 months.
- Calculates reward points using a tiered formula:
  - 2 points for every dollar spent over $100
  - 1 point for every dollar spent over $50 up to $100
- Aggregates monthly and total reward points per customer.
- Input validation and global exception handling.
- Includes unit and integration tests.
- Dummy data setup for quick testing.
 
## Technologies Used
 
- Java 21
- Spring Boot
- Maven
- JUnit 5 & Mockito
 
---

## API Endpoint
 
### `POST /api/rewards`
 
**Request Body:**
 
```json
[
  {
    "customerId": "CUST001",
    "amount": 120.0,
    "transactionDate": "2024-04-20"
  },
  {
    "customerId": "CUST001",
    "amount": 90.0,
    "transactionDate": "2024-03-10"
  }
]
```
 
**Response:**
 
```json
[
  {
    "customerId": "CUST001",
    "monthlyPoints": {
      "2024-04": 90,
      "2024-03": 40
    },
    "totalPoints": 130
  }
]
```
 
---
 
## How to Run
 
### Prerequisites
 
- Java 21
- Maven 3.6+
 
### Steps
 
1. Clone the repository:
 
```bash
git clone https://github.com/your-repo/reward-points-api.git
cd reward-points-api
```
 
2. Build and run:
 
```bash
mvn clean install
mvn spring-boot:run
```
 
3. Test the API with Postman:
 
- **POST** `http://localhost:8080/api/rewards`
- Body: raw JSON as shown above

- - **GET** `http://localhost:8080/api/rewards/dummy`
---
 
## Testing
 
### Unit Tests
 
```bash
mvn test
```
