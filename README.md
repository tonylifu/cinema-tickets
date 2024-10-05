# Cinema Tickets Purchase API
### Overview
This project provides a ticket purchasing service through the TicketService interface, which allows users to purchase tickets by passing their account ID and ticket requests. The service ensures that all business logic is adhered to, including validation of ticket types, seat allocation, and payment processing. The project also contains services for calculating seats and the total amount payable, as well as ensuring that all ticket requests are valid before processing.

---

## Table of Contents
* [Overview](#overview)
* [Features](#features)
* [Project Structure](#project-structure)
* [Usage](#usage)
  * [Ticket Service](#1-ticket-service)
  * [Seats Calculator](#2-seats-calculator)
  * [Amount Calculator](#3-amount-calculator)
  * [Validation Service](#4-validation-service)
* [Exception Handling](#exception-handling)
* [Testing](#testing)
  * [Cloning and Testing](#cloning-and-testing)
  * [Installation](#installation)

---

## Features
* Ticket Purchase Service: Allows users to purchase tickets for different types, such as adults and children.
* Seat Calculation: Calculates the number of seats required based on the ticket type requests.
* Amount Calculation: Calculates the total payment required based on the ticket requests.
* Validation: Ensures the ticket request is valid, including checking for at least one adult ticket.
* Exception Handling: Proper exception management with custom exceptions.
* Dependency Injection: Implements @RequiredArgsConstructor for dependency injection, simplifying object initialization.

---

## Project Structure
The project is divided into the following main components:
1. TicketService: The main entry point for purchasing tickets.
2. SeatsCalculatorService: Service responsible for calculating the number of seats required.
3. AmountCalculatorService: Service responsible for calculating the total amount to be paid.
4. ValidationService: Service responsible for validating ticket requests.
5. Custom Exceptions: Handles specific validation errors with InvalidPurchaseException.
6. Tests: Unit tests using JUnit and Mockito to ensure functionality.

---
## Usage
### 1. Ticket Service
The TicketService interface is the entry point for purchasing tickets. It provides a single method, purchaseTickets(), which takes an account ID and a list of ticket requests and processes the purchase by performing validation, seat calculation, payment, and reservation.
```java
public interface TicketService {

    void purchaseTickets(Long accountId, TicketTypeRequest... ticketTypeRequests) throws InvalidPurchaseException;
}

```
TicketService implementation:
```java
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {
    private final TicketPaymentService ticketPaymentService;
    private final SeatReservationService seatReservationService;
    private final ValidationService validationService;
    private final SeatsCalculatorService seatsCalculatorService;
    private final AmountCalculatorService amountCalculatorService;

    @Override
    public void purchaseTickets(Long accountId, TicketTypeRequest... ticketTypeRequests) throws InvalidPurchaseException {
        // Validations, payment processing, and seat reservation
    }
}

```
---

### 2. Seats Calculator
The SeatsCalculatorService calculates the number of seats required based on the ticket type requests. It ensures that only valid ticket types (e.g., adult, child) contribute to seat allocation.
```java
/**
 * The {@code SeatsCalculatorService} interface provides a method to calculate
 * the number of seats required for the given ticket type requests.
 */
public interface SeatsCalculatorService {
    
    int getNumberOfSeatsToAllocate(TicketTypeRequest... ticketTypeRequests);
}

```
Usage:
```java
int seats = seatsCalculatorService.getNumberOfSeatsToAllocate(ticketTypeRequests);

```
---

### 3. Amount Calculator
The AmountCalculatorService calculates the total payment required based on the ticket types and quantities provided.
```java
/**
 * The {@code AmountCalculatorService} interface provides a method to calculate
 * the total amount to pay based on the provided ticket type requests.
 */
public interface AmountCalculatorService {

    int getTotalAmountToPay(TicketTypeRequest... ticketTypeRequests);
}

```
Usage:
```java
int totalAmount = amountCalculatorService.getTotalAmountToPay(ticketTypeRequests);

```
---

### 4. Validation Service
The ValidationService ensures that at least one adult ticket is present in the ticket type requests, as per business rules.
```java
/**
 * The {@code ValidationService} interface provides a method to validate whether an
 * adult ticket is present in the provided ticket type requests.
 */
public interface ValidationService {

    boolean isAdultInTicketsRequest(TicketTypeRequest... ticketTypeRequests);
}

```
Usage:
```java
boolean adultInTicketsRequest = validationService.isAdultInTicketsRequest(ticketTypeRequests);

```
---

## Exception Handling
### Custom Exceptions
The project uses custom exceptions to handle invalid purchase scenarios. The InvalidPurchaseException is used to signal that a purchase request does not meet the required business rules.
```java
public class InvalidPurchaseException extends RuntimeException {

    public InvalidPurchaseException(String message) {
        super(message);
    }

    public InvalidPurchaseException(String message, Throwable cause) {
        super(message, cause);
    }
}

```
Common scenarios that can throw an InvalidPurchaseException include:
* Null or empty ticket requests.
* Invalid account IDs.
* No adult tickets in the request.
* Exceeding the maximum number of tickets.

---

## Testing
Unit tests are implemented using JUnit 5 and Mockito to ensure the correctness of the logic and business rules. All core functionalities, including ticket purchase, seat allocation, amount calculation, and validation, are covered by tests.

### Cloning and Testing
### Prerequisites
* Java 21
* Maven
* JUnit 5 and Mockito for testing
### Installation
1. Clone the repository:
```shell
git clone https://github.com/tonylifu/cinema-tickets.git
cd cinema-tickets
```
2. Build the project with Maven:
```shell
mvn clean install

```
3. Run the tests:
```shell
mvn test

```
