package uk.gov.dwp.uc.pairtest.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;

import static org.junit.jupiter.api.Assertions.*;

class SeatsCalculatorServiceTest {
    private SeatsCalculatorService seatsCalculatorService;

    @BeforeEach
    void setUp() {
        seatsCalculatorService = new NumberOfSeatsCalculator();
    }

    @Test
    void getNumberOfSeatsToAllocate_whenTicketTypeRequestIsEmpty() {
        //Number of tickets request
        TicketTypeRequest[] requests = {};
        int seats = seatsCalculatorService.getNumberOfSeatsToAllocate(requests);
        assertEquals(0, seats, "Empty request should result in 0 seats.");
    }
}