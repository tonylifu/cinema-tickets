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
import static uk.gov.dwp.uc.pairtest.exception.ExceptionMessages.*;

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
            throw new InvalidPurchaseException(INVALID_PURCHASE_NULL_EMPTY_TICKET_MESSAGE);
        }

        for (TicketTypeRequest request : ticketTypeRequests) {
            if (request == null) {
                throw new InvalidPurchaseException(INVALID_PURCHASE_TICKET_CONTAINS_EMPTY_MESSAGE);
            }
        }

        if (accountId == null || accountId < 1L) {
            throw new InvalidPurchaseException(INVALID_PURCHASE_INSUFFICIENT_FUNDS_MESSAGE);
        }

        boolean adultInTicketsRequest = validationService.isAdultInTicketsRequest(ticketTypeRequests);
        if (!adultInTicketsRequest) {
            throw new InvalidPurchaseException(INVALID_PURCHASE_AT_LEAST_ONE_ADULT_MESSAGE);
        }

        //Assumption: infant tickets request do not count toward maximum seats allowed
        int seats = seatsCalculatorService.getNumberOfSeatsToAllocate(ticketTypeRequests);
        if (seats > MAX_NO_TICKET_AT_A_TIME) {
            throw new InvalidPurchaseException(INVALID_PURCHASE_MAX_NO_OF_TICKETS_MESSAGE);
        }

        int totalAmount = amountCalculatorService.getTotalAmountToPay(ticketTypeRequests);

        //payment
        ticketPaymentService.makePayment(accountId, totalAmount);

        //reserve seats
        seatReservationService.reserveSeat(accountId, seats);
    }

}
