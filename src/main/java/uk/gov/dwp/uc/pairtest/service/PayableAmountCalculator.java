package uk.gov.dwp.uc.pairtest.service;

import uk.gov.dwp.uc.pairtest.data.TicketsData;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;

import java.util.Arrays;
import java.util.Map;

import static uk.gov.dwp.uc.pairtest.exception.ExceptionMessages.NULL_TICKET_REQUEST_MESSAGE;

public class PayableAmountCalculator implements AmountCalculatorService {
    private final Map<String, Integer> ticketPrices = TicketsData.getTicketPrices();

    @Override
    public int getTotalAmountToPay(TicketTypeRequest... ticketTypeRequests) {
        if (ticketTypeRequests == null) {
            throw new IllegalArgumentException(NULL_TICKET_REQUEST_MESSAGE);
        }
        for (TicketTypeRequest request : ticketTypeRequests) {
            if (request == null) {
                throw new IllegalArgumentException(NULL_TICKET_REQUEST_MESSAGE);
            }
        }
        if (ticketTypeRequests.length == 0) {
            return 0;
        }
        return Arrays.stream(ticketTypeRequests)
                .filter(request -> request.getNoOfTickets() > 0) // Only consider requests with tickets
                .mapToInt(this::calculateAmount) // Map each request to its total amount
                .sum(); // Sum all amounts
    }

    private int calculateAmount(TicketTypeRequest request) {
        return ticketPrices.getOrDefault(request.getTicketType().name(), 0) * request.getNoOfTickets();
    }
}
