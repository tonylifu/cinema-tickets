package uk.gov.dwp.uc.pairtest.service.validation;

import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;

public class ValidateTickets implements ValidationService {

    @Override
    public boolean isAdultInTicketsRequest(TicketTypeRequest... ticketTypeRequests) {
        return false;
    }
}
