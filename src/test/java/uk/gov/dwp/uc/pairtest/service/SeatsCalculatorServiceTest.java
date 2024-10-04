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
        //Number of tickets request - Empty
        TicketTypeRequest[] requests = {};
        int seats = seatsCalculatorService.getNumberOfSeatsToAllocate(requests);
        assertEquals(0, seats, "Empty request should result in 0 seats.");
    }

    @Test
    void getNumberOfSeatsToAllocate_whenTicketTypeRequestIsSingleAdult() {
        //Number of tickets request - Single Adult
        TicketTypeRequest request = new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 1);
        int seats = seatsCalculatorService.getNumberOfSeatsToAllocate(request);
        assertEquals(1, seats, "One adult ticket request should require 1 seat.");
    }
}