package uk.gov.dwp.uc.pairtest.service.validation;

import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;

import java.util.Arrays;

public class ValidateTickets implements ValidationService {

    @Override
    public boolean isAdultInTicketsRequest(TicketTypeRequest... ticketTypeRequests) {
        if (ticketTypeRequests == null) {
            throw new IllegalArgumentException("Ticket requests cannot be null");
        }

        return Arrays.stream(ticketTypeRequests)
                .anyMatch(request -> TicketTypeRequest.Type.ADULT == request.getTicketType());
    }
}
