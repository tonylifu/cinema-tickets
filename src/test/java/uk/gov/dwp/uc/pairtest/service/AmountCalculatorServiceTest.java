package uk.gov.dwp.uc.pairtest.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;

import static org.junit.jupiter.api.Assertions.*;
import static uk.gov.dwp.uc.pairtest.exception.ExceptionMessages.*;

public class AmountCalculatorServiceTest {
    private AmountCalculatorService amountCalculatorService;

    @BeforeEach
    void setUp() {
        amountCalculatorService = new PayableAmountCalculator();
    }

    @Test
    public void getTotalAmountToPay_whenTicketTypeRequestIsEmpty() {
        // Test case when no tickets are requested
        TicketTypeRequest[] requests = {};
        int totalAmount = amountCalculatorService.getTotalAmountToPay(requests);
        assertEquals(0, totalAmount, "Empty request should result in 0 total amount.");
    }

    @Test
    public void getTotalAmountToPay_whenSingleAdultTicketRequested() {
        // Test case for a single adult ticket
        TicketTypeRequest request = new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 1);
        int totalAmount = amountCalculatorService.getTotalAmountToPay(request);
        assertEquals(25, totalAmount, "One adult ticket should cost 25 units.");
    }

    @Test
    public void getTotalAmountToPay_whenMultipleAdultTicketsRequested() {
        // Test case for multiple adult tickets
        TicketTypeRequest request = new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 5);
        int totalAmount = amountCalculatorService.getTotalAmountToPay(request);
        assertEquals(125, totalAmount, "Five adult tickets should cost 125 units.");
    }

    @Test
    public void getTotalAmountToPay_whenSingleChildTicketRequested() {
        // Test case for a single child ticket
        TicketTypeRequest request = new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 1);
        int totalAmount = amountCalculatorService.getTotalAmountToPay(request);
        assertEquals(15, totalAmount, "One child ticket should cost 15 units.");
    }

    @Test
    public void getTotalAmountToPay_whenMultipleChildTicketsRequested() {
        // Test case for multiple child tickets
        TicketTypeRequest request = new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 4);
        int totalAmount = amountCalculatorService.getTotalAmountToPay(request);
        assertEquals(60, totalAmount, "Four child tickets should cost 60 units.");
    }

    @Test
    public void getTotalAmountToPay_whenSingleInfantTicketRequested() {
        // Test case for a single infant ticket
        TicketTypeRequest request = new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 1);
        int totalAmount = amountCalculatorService.getTotalAmountToPay(request);
        assertEquals(0, totalAmount, "Infant tickets should cost 0 units.");
    }

    @Test
    public void getTotalAmountToPay_whenMultipleInfantTicketsRequested() {
        // Test case for multiple infant tickets
        TicketTypeRequest request = new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 13);
        int totalAmount = amountCalculatorService.getTotalAmountToPay(request);
        assertEquals(0, totalAmount, "Multiple infant tickets should still cost 0 units.");
    }

    @Test
    public void getTotalAmountToPay_whenMixedTicketsRequested() {
        // Test case for a mix of ticket types (adults, children, and infants)
        TicketTypeRequest adultRequest = new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 2);
        TicketTypeRequest childRequest = new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 4);
        TicketTypeRequest infantRequest = new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 1);
        int totalAmount = amountCalculatorService.getTotalAmountToPay(adultRequest, childRequest, infantRequest);
        assertEquals(110, totalAmount, "Two adults and four children should cost 110 units. Infants are free.");
    }

    @Test
    public void getTotalAmountToPay_whenNoAdultsAndMultipleChildrenAndInfants() {
        // Test case with no adults but multiple children and infants
        TicketTypeRequest childRequest = new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 4);
        TicketTypeRequest infantRequest = new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 2);
        int totalAmount = amountCalculatorService.getTotalAmountToPay(childRequest, infantRequest);
        assertEquals(60, totalAmount, "Four children and two infants should cost 60 units. Infants are free.");
    }

    @Test
    public void getTotalAmountToPay_whenAllTicketTypesRequestedInLargeQuantities() {
        // Test case with large numbers of all ticket types
        TicketTypeRequest adultRequest = new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 50);
        TicketTypeRequest childRequest = new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 30);
        TicketTypeRequest infantRequest = new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 10);
        int totalAmount = amountCalculatorService.getTotalAmountToPay(adultRequest, childRequest, infantRequest);
        assertEquals(1700, totalAmount, "50 adults, 30 children, and 10 infants should cost 1700 units. Infants are free.");
    }

    @Test
    public void getTotalAmountToPay_whenTicketTypeRequestIsNull() {
        // Test case for a null request (invalid scenario)
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            amountCalculatorService.getTotalAmountToPay((TicketTypeRequest[]) null);
        });
        assertEquals(NULL_TICKET_REQUEST_MESSAGE, exception.getMessage());
    }

    @Test
    public void getTotalAmountToPay_whenRequestContainsNullTicketType() {
        // Test case where a request contains a null ticket type
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            TicketTypeRequest nullRequest = new TicketTypeRequest(null, 1);
            amountCalculatorService.getTotalAmountToPay(nullRequest);
        });
        assertEquals(NULL_TYPE_MESSAGE, exception.getMessage());
    }

    @Test
    public void getTotalAmountToPay_whenNegativeNumberOfTicketsRequested() {
        // Test case with a negative number of tickets (invalid scenario)
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new TicketTypeRequest(TicketTypeRequest.Type.ADULT, -1);
        });
        assertEquals(NEGATIVE_NO_OF_TICKET_MESSAGE, exception.getMessage());
    }
}