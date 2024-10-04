package uk.gov.dwp.uc.pairtest.service;

import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;

public interface AmountCalculatorService {
    int getTotalAmountToPay(TicketTypeRequest... ticketTypeRequests);
}
