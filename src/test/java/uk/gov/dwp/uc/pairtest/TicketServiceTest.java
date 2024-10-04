package uk.gov.dwp.uc.pairtest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import thirdparty.paymentgateway.TicketPaymentService;
import thirdparty.seatbooking.SeatReservationService;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;
import uk.gov.dwp.uc.pairtest.service.AmountCalculatorService;
import uk.gov.dwp.uc.pairtest.service.NumberOfSeatsCalculator;
import uk.gov.dwp.uc.pairtest.service.PayableAmountCalculator;
import uk.gov.dwp.uc.pairtest.service.SeatsCalculatorService;
import uk.gov.dwp.uc.pairtest.service.validation.ValidateTickets;
import uk.gov.dwp.uc.pairtest.service.validation.ValidationService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TicketServiceTest {
    private TicketPaymentService ticketPaymentService;
    private SeatReservationService seatReservationService;
    private ValidationService validationService;
    private SeatsCalculatorService seatsCalculatorService;
    private AmountCalculatorService amountCalculatorService;
    private TicketService ticketService;

    @BeforeEach
    void setUp() {
        ticketPaymentService = mock(TicketPaymentService.class);
        seatReservationService = mock(SeatReservationService.class);
        validationService = new ValidateTickets();
        seatsCalculatorService = new NumberOfSeatsCalculator();
        amountCalculatorService = new PayableAmountCalculator();
        ticketService = new TicketServiceImpl(
                ticketPaymentService,
                seatReservationService,
                validationService,
                seatsCalculatorService,
                amountCalculatorService
        );
    }

    @Test
    public void purchaseTickets_whenValidAdultAndChild_thenMakesPaymentAndReservesSeats() throws InvalidPurchaseException {
        // Given
        TicketTypeRequest adultRequest = new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 2);
        TicketTypeRequest childRequest = new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 1);

        // When
        ticketService.purchaseTickets(1L, adultRequest, childRequest);

        // Then
        verify(ticketPaymentService, times(1)).makePayment(1L, 65);  // 2 adults (£25 each) + 1 child (£15) = £65
        verify(seatReservationService, times(1)).reserveSeat(1L, 3);  // 2 adults + 1 child = 3 seats
    }

    @Test
    void purchaseTickets_whenOnlyInfantRequest_thenThrowsInvalidPurchaseException() {
        // Given
        TicketTypeRequest infantRequest = new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 1);

        // When & Then
        InvalidPurchaseException exception = assertThrows(InvalidPurchaseException.class,
                () -> ticketService.purchaseTickets(1L, infantRequest));
        assertEquals("At least one adult ticket is required.", exception.getMessage());
    }

    @Test
    void purchaseTickets_whenMoreInfantsThanAdults_thenThrowsInvalidPurchaseException() {
        // Given
        TicketTypeRequest adultRequest = new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 1);
        TicketTypeRequest infantRequest = new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 2);

        // When & Then
        InvalidPurchaseException exception = assertThrows(InvalidPurchaseException.class,
                () -> ticketService.purchaseTickets(1L, adultRequest, infantRequest));
        assertEquals("Each infant must be accompanied by an adult.", exception.getMessage());
    }

    @Test
    void purchaseTickets_whenAccountIdIsInvalid_thenThrowsInvalidPurchaseException() {
        // Given
        TicketTypeRequest adultRequest = new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 1);

        // When & Then
        InvalidPurchaseException exception = assertThrows(InvalidPurchaseException.class,
                () -> ticketService.purchaseTickets(0L, adultRequest));
        assertEquals("Invalid account ID.", exception.getMessage());
    }

    @Test
    void purchaseTickets_whenTicketRequestIsEmpty_thenThrowsInvalidPurchaseException() {
        // When & Then
        InvalidPurchaseException exception = assertThrows(InvalidPurchaseException.class,
                () -> ticketService.purchaseTickets(1L));
        assertEquals("Ticket request cannot be null or empty.", exception.getMessage());
    }

    @Test
    void purchaseTickets_whenTicketRequestContainsNull_thenThrowsInvalidPurchaseException() {
        // Given
        TicketTypeRequest adultRequest = new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 1);

        // When & Then
        InvalidPurchaseException exception = assertThrows(InvalidPurchaseException.class,
                () -> ticketService.purchaseTickets(1L, adultRequest, null));
        assertEquals("Invalid ticket request.", exception.getMessage());
    }

    @Test
    void purchaseTickets_whenAccountHasSufficientFunds_thenMakesPaymentAndReservesSeats() throws InvalidPurchaseException {
        // Given
        TicketTypeRequest adultRequest = new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 1);
        TicketTypeRequest childRequest = new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 1);

        // When
        ticketService.purchaseTickets(1L, adultRequest, childRequest);

        // Then
        verify(ticketPaymentService).makePayment(1L, 40);  // 1 adult (£25) + 1 child (£15) = £40
        verify(seatReservationService).reserveSeat(1L, 2);  // 1 adult + 1 child = 2 seats
    }

    @Test
    void purchaseTickets_whenRequestHasNegativeTickets_thenThrowsInvalidPurchaseException() {
        // Given
        TicketTypeRequest invalidRequest = new TicketTypeRequest(TicketTypeRequest.Type.ADULT, -1);

        // When & Then
        InvalidPurchaseException exception = assertThrows(InvalidPurchaseException.class,
                () -> ticketService.purchaseTickets(1L, invalidRequest));
        assertEquals("Invalid ticket request.", exception.getMessage());
    }
}