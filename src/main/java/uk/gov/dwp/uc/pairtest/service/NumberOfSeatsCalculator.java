package uk.gov.dwp.uc.pairtest.service;

import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;

public class NumberOfSeatsCalculator implements SeatsCalculatorService {
    @Override
    public int getNumberOfSeatsToAllocate(TicketTypeRequest... ticketTypeRequests) {
        return 0;
    }
}
