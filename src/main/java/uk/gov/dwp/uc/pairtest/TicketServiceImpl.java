package uk.gov.dwp.uc.pairtest;

import lombok.RequiredArgsConstructor;
import thirdparty.paymentgateway.TicketPaymentService;
import thirdparty.seatbooking.SeatReservationService;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;
import uk.gov.dwp.uc.pairtest.service.AmountCalculatorService;
import uk.gov.dwp.uc.pairtest.service.SeatsCalculatorService;
import uk.gov.dwp.uc.pairtest.service.validation.ValidationService;
import static uk.gov.dwp.uc.pairtest.data.TicketsData.MAX_NO_TICKET_AT_A_TIME;

@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {
    private final TicketPaymentService ticketPaymentService;
    private final SeatReservationService seatReservationService;
    private final ValidationService validationService;
    private final SeatsCalculatorService seatsCalculatorService;
    private final AmountCalculatorService amountCalculatorService;
    /**
     * Should only have private methods other than the one below.
     */

    @Override
    public void purchaseTickets(Long accountId, TicketTypeRequest... ticketTypeRequests) throws InvalidPurchaseException {
        if (ticketTypeRequests == null || ticketTypeRequests.length == 0) {
            throw new InvalidPurchaseException("Invalid ticket request. Ticket cannot be null or empty.");
        }

        for (TicketTypeRequest request : ticketTypeRequests) {
            if (request == null) {
                throw new InvalidPurchaseException("Invalid ticket request. Ticket cannot contain null or empty.");
            }
        }

        if (accountId == null || accountId < 1L) {
            throw new InvalidPurchaseException("Insufficient funds.");
        }

        boolean adultInTicketsRequest = validationService.isAdultInTicketsRequest(ticketTypeRequests);
        if (!adultInTicketsRequest) {
            throw new InvalidPurchaseException("At least one adult ticket is required.");
        }

        //Assumption: infant tickets request do not count toward maximum seats allowed
        int seats = seatsCalculatorService.getNumberOfSeatsToAllocate(ticketTypeRequests);
        if (seats > MAX_NO_TICKET_AT_A_TIME) {
            throw new InvalidPurchaseException("Maximum allowed number of seats exceeded.");
        }

        int totalAmount = amountCalculatorService.getTotalAmountToPay(ticketTypeRequests);

        //payment
        ticketPaymentService.makePayment(accountId, totalAmount);

        //reserve seats
        seatReservationService.reserveSeat(accountId, seats);
    }

}
