package uk.gov.dwp.uc.pairtest.service.validation;

import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;

import java.util.Arrays;

import static uk.gov.dwp.uc.pairtest.exception.ExceptionMessages.NULL_TICKET_REQUEST_MESSAGE;

public class ValidateTickets implements ValidationService {

    @Override
    public boolean isAdultInTicketsRequest(TicketTypeRequest... ticketTypeRequests) {
        if (ticketTypeRequests == null) {
            throw new IllegalArgumentException(NULL_TICKET_REQUEST_MESSAGE);
        }
        for (TicketTypeRequest request : ticketTypeRequests) {
            if (request == null) {
                throw new IllegalArgumentException(NULL_TICKET_REQUEST_MESSAGE);
            }
        }
        if (ticketTypeRequests.length == 0) {
            return false;
        }

        return Arrays.stream(ticketTypeRequests)
                .anyMatch(request -> TicketTypeRequest.Type.ADULT == request.getTicketType());
    }
}
