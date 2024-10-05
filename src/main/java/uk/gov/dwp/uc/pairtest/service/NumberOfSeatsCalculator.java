package uk.gov.dwp.uc.pairtest.service;

import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import java.util.stream.Stream;

import static uk.gov.dwp.uc.pairtest.exception.ExceptionMessages.NULL_TICKET_REQUEST_MESSAGE;

public class NumberOfSeatsCalculator implements SeatsCalculatorService {
    @Override
    public int getNumberOfSeatsToAllocate(TicketTypeRequest... ticketTypeRequests) {
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
        return Stream.of(ticketTypeRequests)
                .filter(request -> request.getTicketType() == TicketTypeRequest.Type.ADULT
                        || request.getTicketType() == TicketTypeRequest.Type.CHILD)
                .mapToInt(TicketTypeRequest::getNoOfTickets)
                .sum();
    }
}
