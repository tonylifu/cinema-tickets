package uk.gov.dwp.uc.pairtest.service.validation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;

import static org.junit.jupiter.api.Assertions.*;
import static uk.gov.dwp.uc.pairtest.exception.ExceptionMessages.NULL_TICKET_REQUEST_MESSAGE;
import static uk.gov.dwp.uc.pairtest.exception.ExceptionMessages.NULL_TYPE_MESSAGE;

public class ValidationServiceTest {
    private ValidationService validationService;

    @BeforeEach
    void setUp() {
        validationService = new ValidateTickets();
    }

    @Test
    public void isAdultInTicketsRequest_whenRequestContainsAdult_thenReturnsTrue() {
        // Given an array with an ADULT request
        TicketTypeRequest adultRequest = new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 1);

        // When
        boolean result = validationService.isAdultInTicketsRequest(adultRequest);

        // Then
        assertTrue(result, "Should return true when ADULT ticket is present.");
    }

    @Test
    void isAdultInTicketsRequest_whenRequestContainsMultipleTypesIncludingAdult_thenReturnsTrue() {
        // Given a mix of ticket types, including ADULT
        TicketTypeRequest childRequest = new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 2);
        TicketTypeRequest adultRequest = new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 1);
        TicketTypeRequest infantRequest = new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 1);

        // When
        boolean result = validationService.isAdultInTicketsRequest(childRequest, adultRequest, infantRequest);

        // Then
        assertTrue(result, "Should return true when ADULT is present among multiple ticket types.");
    }

    @Test
    void isAdultInTicketsRequest_whenRequestContainsMultipleTypesExcludingAdult_thenReturnsFalse() {
        // Given a mix of ticket types, including ADULT
        TicketTypeRequest childRequest = new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 5);
        TicketTypeRequest infantRequest = new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 3);

        // When
        boolean result = validationService.isAdultInTicketsRequest(childRequest, infantRequest);

        // Then
        assertFalse(result, "Should return false when ADULT is not present among multiple ticket types.");
    }

    @Test
    void isAdultInTicketsRequest_whenRequestContainsOnlyChild_thenReturnsFalse() {
        // Given only CHILD ticket request
        TicketTypeRequest childRequest = new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 2);

        // When
        boolean result = validationService.isAdultInTicketsRequest(childRequest);

        // Then
        assertFalse(result, "Should return false when only CHILD ticket is present.");
    }

    @Test
    void isAdultInTicketsRequest_whenRequestContainsOnlyInfant_thenReturnsFalse() {
        // Given only INFANT ticket request
        TicketTypeRequest infantRequest = new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 1);

        // When
        boolean result = validationService.isAdultInTicketsRequest(infantRequest);

        // Then
        assertFalse(result, "Should return false when only INFANT ticket is present.");
    }

    @Test
    void isAdultInTicketsRequest_whenRequestIsEmpty_thenReturnsFalse() {
        // Given an empty request array
        TicketTypeRequest[] emptyRequests = {};

        // When
        boolean result = validationService.isAdultInTicketsRequest(emptyRequests);

        // Then
        assertFalse(result, "Should return false for empty request array.");
    }

    @Test
    void isAdultInTicketsRequest_whenRequestIsNull_thenThrowsIllegalArgumentException() {
        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            validationService.isAdultInTicketsRequest(null);
        });
        assertEquals(NULL_TICKET_REQUEST_MESSAGE, exception.getMessage());
    }
}