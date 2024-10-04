package uk.gov.dwp.uc.pairtest.service.validation;

import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;

public interface ValidationService {
    boolean isAdultInTicketsRequest(TicketTypeRequest... ticketTypeRequests);
}
