package uk.gov.dwp.uc.pairtest.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;

import static org.junit.jupiter.api.Assertions.*;
import static uk.gov.dwp.uc.pairtest.exception.ExceptionMessages.NEGATIVE_NO_OF_TICKET_MESSAGE;
import static uk.gov.dwp.uc.pairtest.exception.ExceptionMessages.NULL_TYPE_MESSAGE;

public class SeatsCalculatorServiceTest {
    private SeatsCalculatorService seatsCalculatorService;

    @BeforeEach
    void setUp() {
        seatsCalculatorService = new NumberOfSeatsCalculator();
    }

    @Test
    public void getNumberOfSeatsToAllocate_whenTicketTypeRequestIsEmpty() {
        //Number of tickets request - Empty
        TicketTypeRequest[] requests = {};
        int seats = seatsCalculatorService.getNumberOfSeatsToAllocate(requests);
        assertEquals(0, seats, "Empty request should result in 0 seats.");
    }

    @Test
    public void getNumberOfSeatsToAllocate_whenTicketTypeRequestIsSingleAdult() {
        //Number of tickets request - Single Adult
        TicketTypeRequest request = new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 1);
        int seats = seatsCalculatorService.getNumberOfSeatsToAllocate(request);
        assertEquals(1, seats, "One adult ticket request should require 1 seat.");
    }

    @Test
    public void getNumberOfSeatsToAllocate_whenTicketTypeRequestIsMultipleAdult() {
        //Number of tickets request - Single Adult
        TicketTypeRequest request = new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 9);
        int seats = seatsCalculatorService.getNumberOfSeatsToAllocate(request);
        assertEquals(9, seats, "Nine adult tickets request should require 9 seats.");
    }

    @Test
    public void getNumberOfSeatsToAllocate_whenTicketTypeRequestIsSingleChild() {
        // One child ticket
        TicketTypeRequest request = new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 1);
        int seats = seatsCalculatorService.getNumberOfSeatsToAllocate(request);
        assertEquals(1, seats, "One child ticket should require 1 seat.");
    }

    @Test
    public void getNumberOfSeatsToAllocate_whenTicketTypeRequestIsMultipleChild() {
        // Multiple child tickets
        TicketTypeRequest request = new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 5);
        int seats = seatsCalculatorService.getNumberOfSeatsToAllocate(request);
        assertEquals(5, seats, "Five child tickets should require 5 seats.");
    }

    @Test
    public void getNumberOfSeatsToAllocate_whenTicketTypeRequestIsSingleInfant() {
        // One infant ticket
        TicketTypeRequest request = new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 1);
        int seats = seatsCalculatorService.getNumberOfSeatsToAllocate(request);
        assertEquals(0, seats, "Infant tickets should not require any seats.");
    }

    @Test
    public void getNumberOfSeatsToAllocate_whenTicketTypeRequestIsMultipleInfant() {
        // Multiple infant tickets
        TicketTypeRequest request = new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 7);
        int seats = seatsCalculatorService.getNumberOfSeatsToAllocate(request);
        assertEquals(0, seats, "Infant tickets should not require any seats, even with multiple tickets.");
    }

    @Test
    public void getNumberOfSeatsToAllocate_whenTicketTypeRequestIsMultipleMixed() {
        // A mix of ticket types
        TicketTypeRequest adultRequest = new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 2);
        TicketTypeRequest childRequest = new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 5);
        TicketTypeRequest infantRequest = new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 2);
        int seats = seatsCalculatorService.getNumberOfSeatsToAllocate(adultRequest, childRequest, infantRequest);
        assertEquals(7, seats, "Two adults and five children should require 7 seats. Infants require 0.");
    }

    @Test
    public void getNumberOfSeatsToAllocate_whenNumberOfTicketsIsNegative() {
        // Negative number of tickets (invalid scenario)
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new TicketTypeRequest(TicketTypeRequest.Type.ADULT, -1);
        });
        assertEquals(NEGATIVE_NO_OF_TICKET_MESSAGE, exception.getMessage());
    }

    @Test
    public void getNumberOfSeatsToAllocate_whenTicketTypeRequestIsNull() {
        // Null type should throw IllegalArgumentException
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new TicketTypeRequest(null, 1);
        });
        assertEquals(NULL_TYPE_MESSAGE, exception.getMessage());
    }
}